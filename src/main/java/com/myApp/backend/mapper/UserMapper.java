package com.myApp.backend.mapper;

import com.myApp.backend.dto.UserDto;
import com.myApp.backend.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userDtoToUser(UserDto userDto);   // Chuyển từ UserDto sang User
    UserDto userToUserDto(User user);       // Chuyển từ User sang UserDto
}
