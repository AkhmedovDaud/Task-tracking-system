package com.example.tasktrackingsystem.service.project;

import com.example.tasktrackingsystem.dto.ProjectDto;
import com.example.tasktrackingsystem.entity.ProjectEntity;
import com.example.tasktrackingsystem.mappers.ProjectMapper;
import com.example.tasktrackingsystem.repository.ProjectRepository;
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
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectMapper projectMapper;

    public ProjectDto create(ProjectDto projectDto) {
        if (projectDto == null) {
            return null;
        }

        String name = projectDto.getName();
        if (name == null || name.isEmpty()) {
            log.error("Ошибка при создании проекта. Имя проекта не должно быть пустым");
            return null;
        }

        name = name.trim();
        if (projectRepository.existsByName(name)){
            log.error("Ошибка при создании проекта. Проект с именем {} уже существует", name);
            return null;
        }
        ProjectEntity project = projectMapper.toProjectEntity(projectDto);
        projectRepository.save(project);
        log.info("Проект успешно создан: name = {}", name);
         return projectMapper.toProjectDto(project);
    }

    @Override
    public void deleteById(Long id) {
        if(!projectRepository.existsById(id)){
            log.info("Ошибка удаления проекта! В БД не найден проект с указанным id = {}", id);
        }
        projectRepository.deleteById(id);
        log.error("Проект с id = {} успешно удален", id);
    }

    @Override
    public ProjectDto findById(Long id) {
        Optional<ProjectEntity> projById = projectRepository.findById(id);
        if(!projById.isPresent()){
            log.error("Ошибка поиска проекта! Не удалось найти проект по заданному id = {}", id);
            return null;
        }
        ProjectEntity project = projById.get();
        log.info("Проект по id = {} успешно найден", id);
        return projectMapper.toProjectDto(project);
    }

//    @Override
//    public void setUserById(ProjectDto projectDto, Long userId) {
//        Long projectId = projectDto.getId();
//        if(!userRepository.existsById(userId)){
//            log.error("Ошибка при назначении пользователя на проект. Пользователя с id = {} нет в БД", projectId);
//        }
//        if(!projectRepository.existsById(projectId)){
//            log.error("Ошибка при назначении пользователя на проект. Проекта с id = {} нет в БД", projectId);
//        }
//        ProjUsrsEntity entity = new ProjUsrsEntity(projectId, userId);
//        projUsersRepository.save(entity);
//        log.info("Пользователь с id = {} успешно назначен на проект с id = {}", userId, projectId);
//    }
//
//    @Override
//    public List <ProjUsrsEntity> findUsers(Long projectId) {
//        if(!projectRepository.existsById(projectId)){
//            log.error("Ошибка поиска пользователей по проекту. Проекта с id = {} нет в БД", projectId);
//            return null;
//        }
//        log.info("Пользователи по проекту с id = {} успешно найдены", projectId);
//        return new ArrayList<>(projUsersRepository.findByProjectsId(projectId));
//    }
//
//    @Override
//    public List<ProjUsrsEntity> findAllUsers() {
//        log.info("Получить список всех проектов и пользователей из БД");
//        return new ArrayList<>(projUsersRepository.findAll());
//    }

    @Override
    public List<ProjectDto> findAll() {
        log.info("Получить список всех проектов из БД");
        return projectRepository.findAll()
                .stream()
                .map(projectMapper::toProjectDto)
                .collect(Collectors.toList());
    }
}
