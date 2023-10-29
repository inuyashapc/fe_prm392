package com.example.projectprmteam2.model;

public class LoginResponse {
    private boolean success;

    public LoginResponse() {
    }

    public LoginResponse(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
