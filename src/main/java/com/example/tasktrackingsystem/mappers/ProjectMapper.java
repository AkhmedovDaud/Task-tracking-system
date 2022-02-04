package com.example.tasktrackingsystem.mappers;

import com.example.tasktrackingsystem.dto.ProjectDto;
import com.example.tasktrackingsystem.entity.ProjectEntity;
import com.example.tasktrackingsystem.mappers.utils.MapperUtils;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {MapperUtils.class}, componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProjectMapper {

    @Mapping(source = "name", target = "name", qualifiedByName = "Trim")
    ProjectEntity toProjectEntity (ProjectDto projectDto);

    ProjectDto toProjectDto(ProjectEntity projectEntity);

}
