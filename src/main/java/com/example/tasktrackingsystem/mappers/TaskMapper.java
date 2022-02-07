package com.example.tasktrackingsystem.mappers;

import com.example.tasktrackingsystem.dto.TaskDto;
import com.example.tasktrackingsystem.entity.TaskEntity;
import com.example.tasktrackingsystem.mappers.utils.MapperUtils;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {MapperUtils.class}, componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface TaskMapper {

    @Mapping(source = "name", target = "name", qualifiedByName = "Trim")
    TaskEntity toTaskEntity(TaskDto taskDto);

    @Mapping(source = "project", target = "projectId", qualifiedByName = "getProjectId")
    @Mapping(source = "user", target = "userId", qualifiedByName = "getUserId")
    TaskDto toTaskDto(TaskEntity taskEntity);
}
