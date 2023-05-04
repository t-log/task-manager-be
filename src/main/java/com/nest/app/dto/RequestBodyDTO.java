package com.nest.app.dto;

import java.util.List;
import java.util.Map;

public class RequestBodyDTO {

    List<Map<String,Boolean>> priority;
    List<Map<String,Boolean>> status;
    int preset;

    public RequestBodyDTO(){

    }

    public List<Map<String, Boolean>> getPriority() {
        return priority;
    }

    public void setPriority(List<Map<String, Boolean>> priority) {
        this.priority = priority;
    }

    public List<Map<String, Boolean>> getStatus() {
        return status;
    }

    public void setStatus(List<Map<String, Boolean>> status) {
        this.status = status;
    }

    public int getPreset() {
        return preset;
    }

    public void setPreset(int preset) {
        this.preset = preset;
    }

    @Override
    public String toString() {
        return "RequestBodyDTO{" +
                "priority=" + priority +
                ", status=" + status +
                ", preset='" + preset + '\'' +
                '}';
    }
}
