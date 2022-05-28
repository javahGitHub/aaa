package com.fazliddin.fullyme.controller;

import com.fazliddin.fullyme.constants.AppConstants;
import com.fazliddin.fullyme.payload.ApiResult;
import com.fazliddin.fullyme.payload.req.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(AuthController.AUTH_CONTROLLER)
public interface AuthController {

    String AUTH_CONTROLLER = AppConstants.BASE_URL + "/auth";

    @PostMapping("/check-phone")
    ApiResult<?> checkPhone(@RequestBody @Valid CheckPhoneDto dto);

    @PostMapping("/verify")
    ApiResult<?> verify(@RequestBody @Valid PhoneVerifyDto dto);

    @PostMapping("/sign-up")
    ApiResult<?> signUp(@RequestBody @Valid RegisterDto dto);

    @PostMapping("/refresh-token")
    ApiResult<TokenDto> refreshToken(@RequestBody @Valid TokenDto dto);

    @PostMapping("/login")
    ApiResult<TokenDto> login(@RequestBody @Valid LoginDto loginDto);
}
