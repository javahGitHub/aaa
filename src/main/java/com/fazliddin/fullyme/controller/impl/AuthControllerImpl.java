package com.fazliddin.fullyme.controller.impl;

import com.fazliddin.fullyme.controller.AuthController;
import com.fazliddin.fullyme.payload.ApiResult;
import com.fazliddin.fullyme.payload.req.*;
import com.fazliddin.fullyme.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthServiceImpl authService;

    @Override
    public ApiResult<?> checkPhone(CheckPhoneDto dto) {
        return authService.checkPhone(dto);
    }

    @Override
    public ApiResult<?> verify(PhoneVerifyDto dto) {
        return authService.verify(dto);
    }

    @Override
    public ApiResult<?> signUp(RegisterDto dto) {
        return authService.signUp(dto);
    }

    @Override
    public ApiResult<TokenDto> refreshToken(TokenDto dto) {
        return authService.refreshToken(dto);
    }

    @Override
    public ApiResult<TokenDto> login(LoginDto loginDto) {
        return authService.login(loginDto);
    }
}
