package com.example.tasktrackingsystem.mappers;

import com.example.tasktrackingsystem.dto.UserDto;
import com.example.tasktrackingsystem.entity.UserEntity;
import com.example.tasktrackingsystem.mappers.utils.MapperUtils;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(uses = {MapperUtils.class}, componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    UserDto toUserDto(UserEntity userEntity);
    UserEntity toUserEntity(UserDto userDto);
}
