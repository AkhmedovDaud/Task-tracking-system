package com.example.tasktrackingsystem.controllers;

import com.example.tasktrackingsystem.WriterCSV;
import com.example.tasktrackingsystem.dto.UserDto;
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
    private final WriterCSV fileWriter;

    @PostMapping(value = "/create")
    public UserDto create(@RequestBody final UserDto userDto){
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

    @PostMapping(value = "/createCSV")
    public void createCSV(){
        log.info("UserController: вызов метода createCSV для создания отчета по задачам для пользователей");
        try {
            fileWriter.write();
            log.info("UserController: CSV отчёт успешно сформирован");
        }
       catch (Exception e) {
            log.info("error: {}", e.getMessage());
        }
    }
}
