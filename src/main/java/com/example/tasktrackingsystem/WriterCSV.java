package com.example.tasktrackingsystem;


import com.example.tasktrackingsystem.dto.TaskDto;
import com.example.tasktrackingsystem.dto.UserDto;
import com.example.tasktrackingsystem.service.task.TaskService;
import com.example.tasktrackingsystem.service.user.UserService;
import com.opencsv.CSVWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class WriterCSV {
    private final UserService userService;
    private final TaskService taskService;

    public void write() throws IOException {
        String file = "data.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(file));
        List<TaskDto> tasksList = taskService.findAll();
        List<UserDto> usersList = userService.findAll();
        List<String[]> saveToCsv = new ArrayList<>();

        int currentRow = 0;
        for(int i = 0; i < usersList.size(); i++){
            UserDto user = usersList.get(i);
            saveToCsv.add(new String[]{user.toString()});
            for(int j = 0; j < tasksList.size(); j++){
                TaskDto task = tasksList.get(j);
                if(!Objects.equals(user.getId(), task.getUserId())) {
                    j++;
                }
                saveToCsv.add(new String[]{task.toString()});
            }
        }
        writer.writeAll(saveToCsv);
        writer.close();
    }
}
