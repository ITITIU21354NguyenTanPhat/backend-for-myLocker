package com.myApp.backend.service.impl;

import com.myApp.backend.dto.UserDto;
import com.myApp.backend.dto.LoginResponse;
import com.myApp.backend.entity.User;
import com.myApp.backend.exception.ResourceNotFoundException;
import com.myApp.backend.exception.EmailAlreadyExistsException;
import com.myApp.backend.mapper.UserMapper;
import com.myApp.backend.repository.UserRepository;
import com.myApp.backend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user;
        if (userDto.getId() != null) {
            user = userRepository.findById(userDto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + userDto.getId()));
            user.setEmail(userDto.getEmail());
        } else {
            if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
                throw new EmailAlreadyExistsException("Email already exists: " + userDto.getEmail());
            }
            user = userMapper.userDtoToUser(userDto);
        }
        User savedUser = userRepository.save(user);
        return userMapper.userToUserDto(savedUser);
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists: " + userDto.getEmail());
        }
        User user = userMapper.userDtoToUser(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.userToUserDto(savedUser);
    }

    @Override
    public List<UserDto> addUsers(List<UserDto> userDtos) {
        List<User> users = userDtos.stream()
                .map(userDto -> {
                    if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
                        throw new EmailAlreadyExistsException("Email already exists: " + userDto.getEmail());
                    }
                    return userMapper.userDtoToUser(userDto);
                })
                .collect(Collectors.toList());
        List<User> savedUsers = userRepository.saveAll(users);
        return savedUsers.stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id:" + id));
        return userMapper.userToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id : " + id));
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (userDto.getEmail() != null) {
            if (userRepository.findByEmail(userDto.getEmail()).isPresent() && !user.getEmail().equals(userDto.getEmail())) {
                throw new EmailAlreadyExistsException("Email already exists: " + userDto.getEmail());
            }
            user.setEmail(userDto.getEmail());
        }

        User updatedUser = userRepository.save(user);
        return userMapper.userToUserDto(updatedUser);
    }

    // Phương thức kiểm tra tính hợp lệ của người dùng qua email
    @Override
    public LoginResponse isValidUser(String email) {  // Sửa để nhận email dạng String
        boolean isValid = userRepository.findByEmail(email).isPresent();
        return new LoginResponse(isValid);  // Trả về kết quả hợp lệ hay không
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto updateUserByEmail(String email, UserDto userDto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));

        if (userDto.getEmail() != null) {
            if (userRepository.findByEmail(userDto.getEmail()).isPresent() && !user.getEmail().equals(userDto.getEmail())) {
                throw new EmailAlreadyExistsException("Email already exists: " + userDto.getEmail());
            }
            user.setEmail(userDto.getEmail());
        }

        User updatedUser = userRepository.save(user);
        return userMapper.userToUserDto(updatedUser);
    }
}
