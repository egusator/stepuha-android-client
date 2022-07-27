package com.example.helloworld.dto;


import com.example.helloworld.model.AppError;
import com.google.gson.annotations.SerializedName;

public class ErrorDTO {
    @SerializedName("status")
    private String status;
    @SerializedName("error")
    private AppError error;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AppError getError() {
        return error;
    }

    public void setError(AppError error) {
        this.error = error;
    }

    public ErrorDTO(String status, AppError error) {
        this.status = status;
        this.error = error;
    }
}