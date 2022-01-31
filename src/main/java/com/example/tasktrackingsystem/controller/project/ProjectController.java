package com.example.tasktrackingsystem.controller.project;


import com.example.tasktrackingsystem.dto.project.ProjectDto;
import com.example.tasktrackingsystem.mappers.ProjectMapper;
import com.example.tasktrackingsystem.repository.ProjectRepository;
import com.example.tasktrackingsystem.service.project.ProjectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/project")
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final ProjectService projectService;

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody final ProjectDto projectDto){
        log.info("ProjectController: вызов метода createProject с параметром: projectDto = {}", projectDto);
        ProjectDto response = projectService.create(projectDto);
       return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        log.info("ProjectController: вызов метода deleteById: id = {}", id);
        projectService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/findById/{id}")
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
