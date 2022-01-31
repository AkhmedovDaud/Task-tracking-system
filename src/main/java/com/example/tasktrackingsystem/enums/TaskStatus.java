package com.example.tasktrackingsystem.enums;

public enum TaskStatus {
    IN_PROGRESS("В работе"),
    CLOSED("Закрыта");

    private final String description;

    TaskStatus(String description){
        this.description = description;
    }

    public String getDescription() { return this.description; }
}
