package com.example.tasktrackingsystem.controllers;

import com.example.tasktrackingsystem.dto.TaskDto;
import com.example.tasktrackingsystem.mappers.TaskMapper;
import com.example.tasktrackingsystem.service.task.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @PostMapping(value = "/create")
    public TaskDto create(@RequestBody final TaskDto taskDto){
        log.info("TaskController: вызов метода create");
        return taskService.create(taskDto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Long id){
        log.info("TaskController: вызов метода delete для задачи с id = {}", id);
        taskService.deleteById(id);
    }

    @GetMapping(value = "/findById/{id}")
    public TaskDto findById(@PathVariable Long id){
        log.info("TaskController: вызов метода findById для задачи с id = {}", id);
        return taskService.findById(id);
    }

    @PostMapping(value = "/ setStatusInProgress/{id}")
    public TaskDto setStatusInProgress(@PathVariable Long id){
        log.info("TaskController: вызов метода setStatusInProgress для задачи id = {}", id);
        return taskService.setStatusInProgress(id);
    }

    @PostMapping(value = "/setStatusClosed/{id}")
    public TaskDto setStatusClosed(@PathVariable Long id){
        log.info("TaskController: вызов метода setStatusInClosed для задачи id = {}", id);
        return taskService.setStatusClosed(id);
    }

    @GetMapping(value = "/findAll")
    public List<TaskDto> findAll(){
        log.info("TaskController: вызов метода findAll");
        return taskService.findAll();
    }

    @PostMapping(value = "/setUser/{userId}")
    public TaskDto setUser(@RequestBody final TaskDto taskDto, @PathVariable Long userId){
        log.info("TaskController: вызов метода setUser c userId = {}", userId);
        return taskService.setUserById(taskDto, userId);
    }
}
