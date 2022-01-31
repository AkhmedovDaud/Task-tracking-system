package com.example.tasktrackingsystem.service.project;

import com.example.tasktrackingsystem.dto.project.ProjectDto;

import java.util.List;

public interface ProjectService {
    ProjectDto create(ProjectDto projectDto);
    void deleteById(Long id);
    ProjectDto findById(Long id);
    List<ProjectDto> findAll();
}
