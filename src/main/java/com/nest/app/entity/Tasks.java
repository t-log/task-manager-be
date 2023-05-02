package com.nest.app.entity;

import com.nest.app.dto.PatientTasks;
import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Tasks {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String description;
    private String comment;
    private String taskStatus;
    private String priority;
    private String dueDate;
    private int patientId;
    private boolean isDeleted;


    public Tasks() {
    }

    public Tasks(int id, String description, String comment, String taskStatus, String priority, String dueDate, int patientId, boolean isDeleted) {
        this.id = id;
        this.description = description;
        this.comment = comment;
        this.taskStatus = taskStatus;
        this.priority = priority;
        this.dueDate = dueDate;
        this.patientId = patientId;
        this.isDeleted = isDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public boolean isIdDeleted() {
        return isDeleted;
    }

    public void setIdDeleted(boolean idDeleted) {
        this.isDeleted = idDeleted;
    }
}
