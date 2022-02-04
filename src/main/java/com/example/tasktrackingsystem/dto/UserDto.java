package com.example.tasktrackingsystem.dto;

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
    private Long projectId;

    @Override
    public String toString() {
        return "Фамилия '" + surname +
                "; Имя '" + name +
                "; Отчество '" + patronymic;
    }
}
