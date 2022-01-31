package com.example.tasktrackingsystem.service.user;

import com.example.tasktrackingsystem.dto.user.UserDto;
import com.example.tasktrackingsystem.entity.project.ProjectEntity;
import com.example.tasktrackingsystem.entity.task.TaskEntity;
import com.example.tasktrackingsystem.entity.user.UserEntity;
import com.example.tasktrackingsystem.mappers.UserMapper;
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
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Override
    public UserDto create(UserDto userDto) {
        if(userDto.getName() != null && userDto.getSurname() != null){
            UserEntity savedUser = userMapper.toUserEntity(userDto);
            userRepository.save(savedUser);
            log.info("Пользователь успешно создан");
            return userMapper.toUserDto(savedUser);
        }
        else {
            log.error("Ошибка при создании пользователя. Поля: имя, фамилия не могут быть пустыми");
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        if(!userRepository.existsById(id)){
            log.error("Ошибка удаления пользователя. Пользователь с id = {} не найден в БД", id);
        }
        userRepository.deleteById(id);
        log.info("Пользователь с id = {} успешно удален", id);
    }

    @Override
    public UserDto findById(Long id) {
        if(!userRepository.existsById(id)){
            log.error("Ошибка поиска пользователя. Пользователь с id = {} не найден", id);
            return null;
        }
        log.info("Пользователь с id = {} успешно найден", id);
        UserEntity user = userRepository.getById(id);
        return userMapper.toUserDto(user);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto setTask(Long taskId, UserDto userDto) {
        Long userId = userDto.getId();
        Optional <TaskEntity> taskById = taskRepository.findById(taskId);
        if(!taskById.isPresent() || !userRepository.existsById(userId)){
            log.error("Задача с id = {} не найдена или пользователь с id = {} не найден", taskId, userId);
            return null;
        }
        taskById.ifPresent(task -> {
            String taskName = task.getName();
            userDto.setTasks(userDto.getTasks() + ", " + taskName);
        });
        UserEntity savedUser = userMapper.toUserEntity(userDto);
        userRepository.save(savedUser);
        log.info("Задача с id = {} успешно задана пользователю с id = {}", taskId, userId);
        return userMapper.toUserDto(savedUser);
    }

    @Override
    public UserDto setProject(Long projId, UserDto userDto) {
        Long userId = userDto.getId();
        Optional <ProjectEntity> projById = projectRepository.findById(projId);
        if(!projById.isPresent() || !userRepository.existsById(userId)){
            log.error("Проект с указанным id = {} не найден или пользователь с id = {} не найден", projId, userId);
            return null;
        }
        projById.ifPresent(proj -> {
            String projName = proj.getName();
            userDto.setProjects(userDto.getProjects() + ", " + projName);
        });
        UserEntity savedUser = userMapper.toUserEntity(userDto);
        userRepository.save(savedUser);
        log.info("Проект с id = {} успешно задан пользователю с id = {}", projId, userId);
        return userMapper.toUserDto(savedUser);
    }
}
