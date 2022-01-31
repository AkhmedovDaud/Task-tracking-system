package com.example.tasktrackingsystem.controller.user;

import com.example.tasktrackingsystem.dto.user.UserDto;
import com.example.tasktrackingsystem.mappers.UserMapper;
import com.example.tasktrackingsystem.repository.UserRepository;
import com.example.tasktrackingsystem.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/create")
    public UserDto create(@RequestBody UserDto userDto){
        log.info("UserController: вызов метода create");
        return userService.create(userDto);
    }

    @GetMapping(value = "/findById/{id}")
    public UserDto findById(@PathVariable Long id){
        log.info("UserController: вызов метода findById с id = {}", id);
        return userService.findById(id);
    }

    @GetMapping(value = "/findAll")
    public List<UserDto> findAll(){
        log.info("UserController: вызов метода findAll");
        return userService.findAll();
    }

    @DeleteMapping(value = "/deleteById/{id}")
    public void deleteById(@PathVariable Long id){
        log.info("UserController: вызов метода deleteById для пользователя с id = {}", id);
        userService.deleteById(id);
    }

    @PostMapping(value = "/setTaskById/{id}")
    public UserDto setTaskById(@PathVariable Long id, @RequestBody UserDto userDto){
        log.info("UserController: вызов метода setTaskById: id задачи = {}, id пользователя = {}", id, userDto.getId());
        return userService.setTask(id, userDto);
    }

    @PostMapping(value = "/setProjectById/{id}")
    public UserDto setProjectById(@PathVariable Long id, @RequestBody UserDto userDto){
        log.info("UserController: вызов метода setProjectById: id задачи = {}, id пользователя = {}", id, userDto.getId());
        return userService.setProject(id, userDto);
    }
}
