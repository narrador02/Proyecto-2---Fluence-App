package com.example.fluenceapp.models;

public class RegisterRequest {
    private String nombreUsuario;
    private String email;
    private String telefono;
    private String password;

    public RegisterRequest(String nombreUsuario, String email, String telefono, String password) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
    }
}