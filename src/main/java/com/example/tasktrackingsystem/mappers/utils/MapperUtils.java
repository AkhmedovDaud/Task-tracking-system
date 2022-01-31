package com.example.tasktrackingsystem.mappers.utils;


import lombok.experimental.UtilityClass;
import org.mapstruct.Named;

@UtilityClass
public class MapperUtils {

    @Named("Trim")
    public static String stringWithOutSpaces(String name){ return name.trim(); }
}
