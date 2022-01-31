package com.example.tasktrackingsystem.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private String tasks;
    private String projects;
}
