package com.example.tasktrackingsystem.service.user;

import com.example.tasktrackingsystem.dto.UserDto;
import com.example.tasktrackingsystem.entity.ProjectEntity;
import com.example.tasktrackingsystem.entity.UserEntity;
import com.example.tasktrackingsystem.mappers.UserMapper;
import com.example.tasktrackingsystem.repository.ProjectRepository;
import com.example.tasktrackingsystem.repository.TaskRepository;
import com.example.tasktrackingsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
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
            final ProjectEntity byId = projectRepository.getById(userDto.getProjectId());
            UserEntity savedUser = userMapper.toUserEntity(userDto);
            savedUser.getProjects().add(byId);
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

}
