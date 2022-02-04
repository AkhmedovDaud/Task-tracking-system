package com.example.tasktrackingsystem.service.user;


import com.example.tasktrackingsystem.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto create(UserDto userDto);
    void deleteById(Long id);
    UserDto findById(Long id);
    List<UserDto> findAll();
}
