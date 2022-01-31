package com.example.tasktrackingsystem.service.project;

import com.example.tasktrackingsystem.dto.project.ProjectDto;
import com.example.tasktrackingsystem.entity.project.ProjectEntity;
import com.example.tasktrackingsystem.mappers.ProjectMapper;
import com.example.tasktrackingsystem.repository.ProjectRepository;
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
    private final ProjectMapper projectMapper;

    public ProjectDto create(ProjectDto projectDto) {
        String name = projectDto.getName().trim();
        if(name.isEmpty()) {
            log.error("Ошибка при создании проекта. Имя проекта не должно быть пустым");
            return null;
        }
        else if(projectRepository.existsByName(name)){
            log.error("Ошибка при создании проекта. Проект с именем {} уже существует", projectDto.getName());
            return null;
        }
        ProjectEntity savedProject = projectMapper.toProjectEntity(projectDto);
        projectRepository.save(savedProject);
        log.info("Проект успешно создан: name = {}", projectDto.getName());
        return projectMapper.toProjectDto(savedProject);
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

    @Override
    public List<ProjectDto> findAll() {
        log.info("Получить список всех проектов из БД");
        return projectRepository.findAll()
                .stream()
                .map(projectMapper::toProjectDto)
                .collect(Collectors.toList());
    }
}
