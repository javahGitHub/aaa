package com.fazliddin.fullyme.mapper;

import com.fazliddin.fullyme.entity.User;
import com.fazliddin.fullyme.payload.resp.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

//    @Mapping(source = "user.get",target = "")
//    UserDto toDto(User user);
}
