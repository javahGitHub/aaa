package com.fazliddin.fullyme.service;

import com.fazliddin.fullyme.common.MessageService;
import com.fazliddin.fullyme.controller.SmsService;
import com.fazliddin.fullyme.entity.Role;
import com.fazliddin.fullyme.entity.User;
import com.fazliddin.fullyme.entity.VerificationCode;
import com.fazliddin.fullyme.enums.RoleTypeEnum;
import com.fazliddin.fullyme.exception.RestException;
import com.fazliddin.fullyme.payload.ApiResult;
import com.fazliddin.fullyme.payload.CheckUserRegisterDto;
import com.fazliddin.fullyme.payload.UserPrincipal;
import com.fazliddin.fullyme.payload.req.*;
import com.fazliddin.fullyme.repository.RoleRepository;
import com.fazliddin.fullyme.repository.UserRepository;
import com.fazliddin.fullyme.repository.VerificationCodeRepository;
import com.fazliddin.fullyme.security.JwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Value("${verification-code.expire-time}")
    private Long verificationExpireTime;

    @Value("${verification-code.limit}")
    private Integer limit;

    private final UserRepository userRepository;
    private final VerificationCodeRepository verificationCodeRepository;
    private final JwtProvider jwtProvider;
    private final SmsService smsService;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;

    @Lazy
    @Autowired
    public AuthServiceImpl(UserRepository userRepository, VerificationCodeRepository verificationCodeRepository, JwtProvider jwtProvider, SmsService smsService, RoleRepository roleRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.verificationCodeRepository = verificationCodeRepository;
        this.jwtProvider = jwtProvider;
        this.smsService = smsService;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    @Lazy
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(RestException::forbidden);
        return new UserPrincipal(user);
    }

    public ApiResult<?> checkPhone(CheckPhoneDto dto) throws RestException {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis() - verificationExpireTime);
        List<VerificationCode> verificationCodes = verificationCodeRepository.findAllByCreatedAtAfterOrderByCreatedAt(timestamp);

        if (verificationCodes.size() >= limit) {
            throw RestException.restThrow(MessageService.getMessage("MESSAGE_NOT_ENOUGH_LIMIT"), HttpStatus.BAD_REQUEST);
        }

        if (!verificationCodes.isEmpty()) {
            VerificationCode lastVerificationCode = verificationCodes.get(0);
            if (!lastVerificationCode.isUsed() && lastVerificationCode.getExpireTime().after(new Timestamp(System.currentTimeMillis()))) {
                throw RestException.restThrow(MessageService.getMessage("LAST_VERIFICATION_CODE_NOT_EXPIRED"), HttpStatus.BAD_REQUEST);
            }
        }

        String verificationCode = generateCode();
        Thread thread = new Thread();
        thread.start();
        smsService.sendMessage(dto.getPhoneNumber(), verificationCode);
        thread.interrupt();

        verificationCodeRepository.save(new VerificationCode(dto.getPhoneNumber(), verificationCode));
        return ApiResult.successResponse(MessageService.getMessage("SMS_SENT"));
    }

    public ApiResult<?> verify(PhoneVerifyDto dto) {
        VerificationCode verificationCode = verificationCodeRepository.checkVerificationCode(dto.getPhoneNumber(), dto.getVerificationCode(), new Timestamp(System.currentTimeMillis()))
                .orElseThrow(() -> RestException.restThrow(MessageService.getMessage("VERIFICATION_CODE_NOT_AVAILABLE"), HttpStatus.BAD_REQUEST));

        Optional<User> optionalUser = userRepository.findByUsername(dto.getPhoneNumber());
        if (optionalUser.isPresent()) {
            String accessToken = jwtProvider.generateToken(dto.getPhoneNumber(), true);
            String refreshToken = jwtProvider.generateToken(dto.getPhoneNumber(), false);
            verificationCode.setUsed(true);
            verificationCodeRepository.save(verificationCode);
            return ApiResult.successResponse(new CheckUserRegisterDto(accessToken, refreshToken));
        } else {

            return ApiResult.successResponse(new CheckUserRegisterDto());
        }
    }

    @Override
    public ApiResult<?> signUp(RegisterDto dto) {
        VerificationCode verificationCode = verificationCodeRepository.checkVerificationCode(dto.getPhoneNumber(), dto.getVerificationCode(), new Timestamp(System.currentTimeMillis()))
                .orElseThrow(() -> RestException.restThrow(MessageService.getMessage("VERIFICATION_CODE_NOT_AVAILABLE"), HttpStatus.BAD_REQUEST));

        Role roleUser = roleRepository.findByRoleType(RoleTypeEnum.USER)
                .orElseThrow(() -> RestException.restThrow(MessageService.getMessage("ROLE_NOT_FOUND"), HttpStatus.NOT_FOUND));

        User user = new User(dto.getFirstName(), dto.getLastName(),dto.getPhoneNumber(), roleUser);
        userRepository.save(user);

        verificationCode.setUsed(true);
        verificationCodeRepository.save(verificationCode);

        String accessToken = jwtProvider.generateToken(dto.getPhoneNumber(), true);
        String refreshToken = jwtProvider.generateToken(dto.getPhoneNumber(), false);

        return ApiResult.successResponse(new CheckUserRegisterDto(accessToken, refreshToken));    }

    @Override
    public ApiResult<TokenDto> refreshToken(TokenDto dto) {
        try {
            jwtProvider.validateToken(dto.getAccessToken());
            return ApiResult.successResponse(dto);
        } catch (ExpiredJwtException exception) {
            Claims claims = exception.getClaims();
            String subject = claims.getSubject();

            try {
                jwtProvider.validateToken(dto.getRefreshToken());
                String username = jwtProvider.getUsername(dto.getRefreshToken());
                if (!username.equals(subject)) {
                    throw RestException.forbidden();
                }
                String accessToken = jwtProvider.generateToken(username, true);
                String refreshToken = jwtProvider.generateToken(username, false);
                return ApiResult.successResponse(new TokenDto(accessToken, refreshToken));
            } catch (Exception e) {
                throw RestException.forbidden();
            }
        } catch (Exception e) {
            throw RestException.forbidden();
        }
    }

    @Override
    public ApiResult<TokenDto> login(LoginDto loginDto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            UserPrincipal userPrincipal = (UserPrincipal) authenticate.getPrincipal();
            User user = userPrincipal.getUser();
            String accessToken = jwtProvider.generateToken(user.getUsername(), true);
            String refreshToken = jwtProvider.generateToken(user.getUsername(), false);
            return ApiResult.successResponse(new TokenDto(accessToken, refreshToken));
        } catch (Exception e) {
            e.printStackTrace();
            throw RestException.restThrow(MessageService.getMessage("WRONG_USERNAME_OR_PASSWORD"), HttpStatus.FORBIDDEN);
        }
    }
    public String generateCode() {
        StringBuilder verificationCode = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            verificationCode.append((int) (Math.random() * 10));
        }
        return verificationCode.toString();
    }

}
