package com.example.tasktrackingsystem.mappers.utils;


import com.example.tasktrackingsystem.entity.ProjectEntity;
import com.example.tasktrackingsystem.entity.UserEntity;
import lombok.experimental.UtilityClass;
import org.mapstruct.Named;

@UtilityClass
public class MapperUtils {

    @Named("Trim")
    public static String stringWithOutSpaces(String name){ return name.trim(); }

    @Named("getProjectId")
    public static Long projectId(ProjectEntity projectId){
        if(projectId == null) {
            return null;
        }
        return projectId.getId();
    }

    @Named("getUserId")
    public static Long userId(UserEntity userId){
        if(userId == null) {
            return null;
        }
        return userId.getId();
    }

}
