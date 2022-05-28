package com.fazliddin.fullyme.service;

import com.fazliddin.fullyme.common.MessageService;
import com.fazliddin.fullyme.entity.Attachment;
import com.fazliddin.fullyme.entity.User;
import com.fazliddin.fullyme.exception.RestException;
import com.fazliddin.fullyme.mapper.UserMapper;
import com.fazliddin.fullyme.payload.ApiResult;
import com.fazliddin.fullyme.payload.UserPrincipal;
import com.fazliddin.fullyme.payload.req.UserReqDto;
import com.fazliddin.fullyme.payload.resp.EditUserDto;
import com.fazliddin.fullyme.payload.resp.UserDto;
import com.fazliddin.fullyme.repository.AttachmentRepository;
import com.fazliddin.fullyme.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AttachmentRepository attachmentRepository;

    @Override
    public ApiResult<UserDto> me(UserPrincipal userPrincipal) {
        UserDto userDto = new UserDto();
        User user = userPrincipal.getUser();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
//        userDto.setAuthorities(userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
        userDto.setUsername(userDto.getUsername());
        userDto.setRole(user.getRole());
        userDto.setPhoto(user.getPhoto());
        return ApiResult.successResponse(userDto);
    }

    @Override
    public ApiResult<UserDto> checkAuth(UserPrincipal userPrincipal) {
        UserDto userDto = new UserDto();
        User user = userPrincipal.getUser();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setAuthorities(userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
        userDto.setUsername(userDto.getUsername());
        userDto.setRole(user.getRole());
        userDto.setPhoto(user.getPhoto());
        return ApiResult.successResponse(userDto);
    }

    @Override
    public ApiResult<?> edit(UUID id, UserReqDto userReqDto) {
        User user = userRepository.findById(id).orElseThrow(() -> RestException.notFound("USER"));
        Attachment attachment = attachmentRepository.findById(userReqDto.getPhotoId()).orElseThrow(() -> RestException.notFound("ATTACHMENT"));
        user.setFirstName(userReqDto.getFirstName());
        user.setLastName(userReqDto.getLastName());
        user.setUsername(userReqDto.getPhoneNumber());
        user.setPhoto(attachment);

        userRepository.save(user);
        return ApiResult.successResponse(MessageService.successEdit("USER"));
    }

    @Override
    public ApiResult<EditUserDto> editUser(User user, EditUserDto dto) {
        User editingUser = userRepository.findById(user.getId()).orElseThrow(() -> RestException.notFound("USER"));
        editingUser.setFirstName(dto.getFirstName());
        editingUser.setLastName(dto.getLastName());
        editingUser.setUsername(dto.getPhoneNumber());
        Attachment photo = attachmentRepository.findById(dto.getPhotoId()).orElseThrow(() -> RestException.notFound("PHOTO"));
        editingUser.setPhoto(photo);
        userRepository.save(editingUser);

        return ApiResult.successResponse("Edited!");
    }
}
