package com.example.myapplication.Model;

public class TaskModel {
    private String taskName;
    private String taskDescription;
    private boolean completed;

    public TaskModel(String taskName, String taskDescription, boolean completed) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.completed = completed;
    }
    public String toString() {
        return "TaskModel{" +
                "taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", completed=" + completed +
                '}';
    }


    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
