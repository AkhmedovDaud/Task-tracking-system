package com.example.tasktrackingsystem.service.task;

import com.example.tasktrackingsystem.dto.TaskDto;
import com.example.tasktrackingsystem.entity.TaskEntity;
import com.example.tasktrackingsystem.entity.UserEntity;
import com.example.tasktrackingsystem.enums.TaskStatus;
import com.example.tasktrackingsystem.mappers.TaskMapper;
import com.example.tasktrackingsystem.repository.ProjectRepository;
import com.example.tasktrackingsystem.repository.TaskRepository;
import com.example.tasktrackingsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TaskMapper taskMapper;

    @Override
    public TaskDto create(TaskDto taskDto) {
        String name = taskDto.getName().trim();
        if(name.isEmpty()){
            log.error("Ошибка при создании задачи. Имя задачи не может быть пустым");
            return null;
        }
        TaskEntity savedTask = taskMapper.toTaskEntity(taskDto);
        if (taskDto.getProjectId() != null) {
            savedTask.setProject(projectRepository.getById(taskDto.getProjectId()));
        }
        if (taskDto.getUserId() != null){
            savedTask.setUser(userRepository.getById(taskDto.getUserId()));
        }
        taskRepository.save(savedTask);
        log.info("Задача успешно создана: name = {}", name);
        return taskMapper.toTaskDto(savedTask);
    }

    @Override
    public TaskDto findById(Long id) {
        if(!taskRepository.existsById(id)){
            log.info("Неудачный поиск задачи. Задача с id = {} не найдена в БД", id);
            return null;
        }
        TaskEntity task = taskRepository.getById(id);
        log.error("Поиск задачи по id = {} прошел успешно", id);
        return taskMapper.toTaskDto(task);
    }

    @Override
    public void deleteById(Long id) {
        if(!taskRepository.existsById(id)) {
            log.error("Ошибка удаления задачи. Задача с id = {} не найдена в БД", id);
        }
        taskRepository.deleteById(id);
        log.info("Успешное удаление задачи: id = {}", id);
    }

    @Override
    public TaskDto setStatusInProgress(Long id) {
        Optional<TaskEntity> taskById = taskRepository.findById(id);
        if(!taskById.isPresent()){
            log.error("Ошибка изменения статуса задачи. В БД нет задач с id = {}", id);
            return null;
        }
        TaskEntity task = taskById.get();
        task.setStatus(TaskStatus.IN_PROGRESS.name());
        taskRepository.save(task);
        return taskMapper.toTaskDto(task);
    }

    @Override
    public TaskDto setStatusClosed(Long id) {
        Optional <TaskEntity> taskById = taskRepository.findById(id);
        if(!taskById.isPresent()){
            log.error("Ошибка изменения статуса задачи. В БД нет задач с id = {}", id);
            return null;
        }
        TaskEntity task = taskById.get();
        task.setStatus(TaskStatus.CLOSED.name());
        taskRepository.save(task);
        return taskMapper.toTaskDto(task);
    }

    @Override
    public TaskDto setUserById(TaskDto taskDto, Long userId) {
        Long taskId = taskDto.getId();
        Optional<UserEntity> user = userRepository.findById(userId);
        if(!user.isPresent()){
            log.error("Ошибка при назначении задачи пользователю. Пользователя с id = {} нет в БД", userId);
            return null;
        }
        if(!taskRepository.existsById(taskId)){
            log.error("Ошибка при назначении задачи пользователю. Задачи с id = {} нет в БД", taskId);
            return null;
        }
        TaskEntity saveTask = taskMapper.toTaskEntity(taskDto);
        saveTask.setUser(user.get());
        taskRepository.save(saveTask);
        log.info("Пользователь с id = {} успешно назначен на задачу с id = {}", userId, taskId);
        return taskMapper.toTaskDto(saveTask);
    }

    @Override
    public List<TaskDto> findAll() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toTaskDto)
                .collect(Collectors.toList());
    }
}
