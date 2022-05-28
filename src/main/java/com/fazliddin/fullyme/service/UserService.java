package com.fazliddin.fullyme.service;

import com.fazliddin.fullyme.entity.User;
import com.fazliddin.fullyme.payload.ApiResult;
import com.fazliddin.fullyme.payload.UserPrincipal;
import com.fazliddin.fullyme.payload.req.UserReqDto;
import com.fazliddin.fullyme.payload.resp.EditUserDto;
import com.fazliddin.fullyme.payload.resp.UserDto;

import java.util.UUID;

public interface UserService {

    ApiResult<UserDto> me(UserPrincipal userPrincipal);

    ApiResult<UserDto> checkAuth(UserPrincipal userPrincipal);

    ApiResult<?> edit(UUID id, UserReqDto userReqDto);

    ApiResult<EditUserDto> editUser(User user, EditUserDto dto);
}
