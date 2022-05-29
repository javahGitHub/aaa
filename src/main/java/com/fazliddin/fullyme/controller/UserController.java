package com.fazliddin.fullyme.controller;

import com.fazliddin.fullyme.annotation.CurrentUser;
import com.fazliddin.fullyme.constants.AppConstants;
import com.fazliddin.fullyme.entity.User;
import com.fazliddin.fullyme.payload.ApiResult;
import com.fazliddin.fullyme.payload.UserPrincipal;
import com.fazliddin.fullyme.payload.req.UserReqDto;
import com.fazliddin.fullyme.payload.resp.EditUserDto;
import com.fazliddin.fullyme.payload.resp.UserRespDto;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping(UserController.USER_CONTROLLER)
public interface UserController {

    String USER_CONTROLLER = AppConstants.BASE_URL + "/user";

    @GetMapping
    ApiResult<UserRespDto> me(@CurrentUser UserPrincipal userPrincipal);

    @PostMapping("/check-auth")
    ApiResult<UserRespDto> checkAuth(@CurrentUser UserPrincipal userPrincipal);
    // edit

    @PutMapping("/{id}")
    ApiResult<?> edit(@PathVariable UUID id, @RequestBody UserReqDto userReqDto);


    @PutMapping("/edit")
    ApiResult<EditUserDto> editUser(@CurrentUser User user, @RequestBody EditUserDto dto);

//    @PostMapping("/staff")
//    @PreAuthorize("hasAnyAuthority('ADD_STAFF')")
//    ApiResult<?> addStaff(@RequestBody @Valid AddStaffDto staffDto);

}

