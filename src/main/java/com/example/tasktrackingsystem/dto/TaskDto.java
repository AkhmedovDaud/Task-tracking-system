package com.example.tasktrackingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private Long id;
    private String name;
    private String desc;
    private Long projectId;
    private String status;
    private Long userId;

    @Override
    public String toString() {
        return "Задача№" + id +
                "; " + name +
                "; " + desc +
                "; " + projectId +
                "; " + status +
                ';';
    }
}
