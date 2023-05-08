package com.nest.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

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
    private LocalDateTime dueDate;
    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Patient patient;
    public Tasks() {
    }

    public Tasks(int id, String description, String comment, String taskStatus, String priority, LocalDateTime dueDate, int patientId, boolean isDeleted, Patient patient) {
        this.id = id;
        this.description = description;
        this.comment = comment;
        this.taskStatus = taskStatus;
        this.priority = priority;
        this.dueDate = dueDate;
        this.isDeleted = isDeleted;
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }


    public boolean isIdDeleted() {
        return isDeleted;
    }

    public void setIdDeleted(boolean idDeleted) {
        this.isDeleted = idDeleted;
    }
}
