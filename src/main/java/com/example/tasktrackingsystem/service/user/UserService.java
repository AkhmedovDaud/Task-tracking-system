package com.example.tasktrackingsystem.service.user;


import com.example.tasktrackingsystem.dto.user.UserDto;

import java.util.List;

public interface UserService {
    UserDto create(UserDto userDto);
    void deleteById(Long id);
    UserDto findById(Long id);
    List<UserDto> findAll();
    UserDto setTask(Long taskId, UserDto userDto);
    UserDto setProject(Long projId, UserDto userDto);
}
