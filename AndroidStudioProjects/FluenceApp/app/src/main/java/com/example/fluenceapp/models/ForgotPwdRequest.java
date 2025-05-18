package com.example.fluenceapp.models;

public class ForgotPwdRequest {
    private String email;

    public ForgotPwdRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}