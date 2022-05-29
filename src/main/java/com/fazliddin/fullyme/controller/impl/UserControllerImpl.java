package com.fazliddin.fullyme.controller.impl;

import com.fazliddin.fullyme.controller.UserController;
import com.fazliddin.fullyme.entity.User;
import com.fazliddin.fullyme.payload.ApiResult;
import com.fazliddin.fullyme.payload.UserPrincipal;
import com.fazliddin.fullyme.payload.req.UserReqDto;
import com.fazliddin.fullyme.payload.resp.EditUserDto;
import com.fazliddin.fullyme.payload.resp.UserDto;
import com.fazliddin.fullyme.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;
    @Override
    public ApiResult<UserDto> me(UserPrincipal userPrincipal) {
        return userService.me(userPrincipal);
    }

    @Override
    public ApiResult<UserDto> checkAuth(UserPrincipal userPrincipal) {
        return userService.checkAuth(userPrincipal);
    }

    @Override
    public ApiResult<?> edit(UUID id, UserReqDto userReqDto) {
        return userService.edit(id, userReqDto);
    }

    @Override
    public ApiResult<EditUserDto> editUser(User user, EditUserDto dto) {
        return userService.editUser(user,dto);
    }
}
