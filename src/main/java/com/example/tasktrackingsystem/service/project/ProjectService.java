package com.example.tasktrackingsystem.service.project;

import com.example.tasktrackingsystem.dto.ProjectDto;

import java.util.List;

public interface ProjectService{
    ProjectDto create(ProjectDto projectDto);
    void deleteById(Long id);
    ProjectDto findById(Long id);
//    void setUserById(ProjectDto projectDto, Long userId);
//    List <ProjUsrsEntity> findUsers(Long projectId);
//    List<ProjUsrsEntity> findAllUsers();
    List<ProjectDto> findAll();
}
