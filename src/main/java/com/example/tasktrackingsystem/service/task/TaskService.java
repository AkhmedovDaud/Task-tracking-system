package com.example.tasktrackingsystem.service.task;

import com.example.tasktrackingsystem.dto.task.TaskDto;

import java.util.List;

public interface TaskService {
    TaskDto create(TaskDto taskDto);
    TaskDto findById(Long id);
    void deleteById(Long id);
    TaskDto setStatusInProgress(Long id);
    TaskDto setStatusClosed(Long id);
    List<TaskDto> findAll();
}
