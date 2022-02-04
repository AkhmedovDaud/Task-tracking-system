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
import java.util.List;

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
        String[] saveToCsv = new String[tasksList.size() + usersList.size()];

        for(int i = 0; i < usersList.size(); i++){
            int currentRow = 1;
            UserDto user = usersList.get(i);
            saveToCsv[currentRow++] = user.toString();
            for(int j = 0; j < tasksList.size(); j++){
                TaskDto task = tasksList.get(j);
                if(user.getId() != task.getUserId()) j++;
                saveToCsv[currentRow++] = task.toString();
                j++;
            }
        }
        writer.writeNext(saveToCsv);
        writer.close();
    }
}
