package com.fazliddin.fullyme.service;

import com.fazliddin.fullyme.payload.ApiResult;
import com.fazliddin.fullyme.payload.req.*;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    ApiResult<?> checkPhone(CheckPhoneDto dto);

    ApiResult<?> verify(PhoneVerifyDto dto);

    ApiResult<?> signUp(RegisterDto dto);

    ApiResult<TokenDto> refreshToken(TokenDto dto);

    ApiResult<TokenDto> login(LoginDto loginDto);
}
