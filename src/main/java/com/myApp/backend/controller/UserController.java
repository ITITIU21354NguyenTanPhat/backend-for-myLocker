package com.myApp.backend.controller;

import com.myApp.backend.dto.EmailRequest;
import com.myApp.backend.dto.LoginResponse;
import com.myApp.backend.dto.UserDto;
import com.myApp.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // API login, kiểm tra email hợp lệ
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody EmailRequest emailRequest) {
        // Gọi phương thức isValidUser để kiểm tra email hợp lệ hay không
        boolean isValidUser = userService.isValidUser(emailRequest.getEmail()).isValidUser();
        if (isValidUser) {
            return ResponseEntity.ok(new LoginResponse(true));  // Người dùng hợp lệ
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse(false));  // Người dùng không hợp lệ
        }
    }

    // API thêm người dùng
    @PostMapping
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.addUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // API lấy thông tin người dùng theo id
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long userId) {
        UserDto userDto = userService.getUser(userId);
        return ResponseEntity.ok(userDto);
    }

    // API lấy tất cả người dùng
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // API cập nhật người dùng theo id
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody UserDto userDto) {
        UserDto updatedUserDto = userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updatedUserDto);
    }

    // API xóa người dùng theo id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User is deleted successfully!");
    }
}
