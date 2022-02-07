package com.example.tasktrackingsystem.controllers;


import com.example.tasktrackingsystem.dto.ProjectDto;
import com.example.tasktrackingsystem.mappers.ProjectMapper;
import com.example.tasktrackingsystem.service.project.ProjectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/project")
public class ProjectController {
    private final ProjectMapper projectMapper;
    private final ProjectService projectService;

    @PostMapping(value = "/create")
    public void create(@RequestBody final ProjectDto projectDto){
        log.info("ProjectController: вызов метода createProject с параметром: projectDto = {}", projectDto);
       projectService.create(projectDto);
    }

    @DeleteMapping(value = "/deleteById/{id}")
    public void deleteById(@PathVariable Long id){
        log.info("ProjectController: вызов метода deleteById: id = {}", id);
        projectService.deleteById(id);
    }

    @GetMapping(value = "/findById/{id}")
    public ProjectDto findById(@PathVariable Long id){
        log.info("ProjectController: вызов метода findById: id = {}", id);
        return projectService.findById(id);
    }

    @GetMapping(value = "/findAll")
    public List<ProjectDto> findAll(){
        log.info("ProjectController: вызов метода findAll");
        return projectService.findAll();
    }

}
