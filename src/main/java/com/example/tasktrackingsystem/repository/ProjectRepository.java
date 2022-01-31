package com.example.tasktrackingsystem.repository;


import com.example.tasktrackingsystem.entity.project.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, Long> {
    ProjectEntity findByName(String name);
    void deleteByName(String name);
    boolean existsByName(String name);
}
