package com.example.fluenceapp.models;

public class LoginRequest {
    private String email;
    private String password;
    private String tipo;

    public LoginRequest(String email, String password, String tipo) {
        this.email = email;
        this.password = password;
        this.tipo = tipo;
    }

    // Getters y setters si los necesitas
}
