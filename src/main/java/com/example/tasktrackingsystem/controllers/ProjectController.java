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

//    @PostMapping(value = "/setUser/{userId}")
//    public void setUser(@RequestBody ProjectDto projectDto, @PathVariable Long userId){
//        log.info("ProjectController: вызов метода setUser с userId = {}", userId);
//        projectService.setUserById(projectDto, userId);
//    }

//    @GetMapping(value = "/findUsers/{projectId}")
//    public  List<ProjUsrsEntity> findUsers(@PathVariable Long projectId){
//        log.info("ProjectController: вызов метода findUsers c projectId = {}", projectId);
//        return projUsersRepository.findByProjectsId(projectId);
//    }
//
//    @GetMapping(value = "/findAllUsers")
//    public  List<ProjUsrsEntity> findAllUsers() {
//        log.info("ProjectController: вызов метода findAllUsers для поиска всех пользователей по всем проектам");
//        return projUsersRepository.findAll();
//    }
}
