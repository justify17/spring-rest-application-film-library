package com.studying.springrestapplication.mapper;

import com.studying.springrestapplication.dto.UserDto;
import com.studying.springrestapplication.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userDtoToUser(UserDto userDto);
}
