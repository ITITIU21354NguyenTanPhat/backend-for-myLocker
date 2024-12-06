package com.myApp.backend.service;

import com.myApp.backend.dto.UserDto;
import com.myApp.backend.dto.LoginResponse;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto userDto);
    UserDto addUser(UserDto userDto);
    List<UserDto> addUsers(List<UserDto> userDtos);
    UserDto getUser(Long id);
    List<UserDto> getAllUsers();
    void deleteUser(Long id);
    UserDto updateUser(UserDto userDto, Long id);
    LoginResponse isValidUser(String email);  // Chỉnh lại để nhận String email
    UserDto getUserByEmail(String email);
    UserDto updateUserByEmail(String email, UserDto userDto);
}
