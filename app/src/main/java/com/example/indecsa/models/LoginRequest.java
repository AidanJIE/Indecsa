package com.example.indecsa.models;

public class LoginRequest {
    private String correoCapHum;
    private String contraseñaCapHum;

    public LoginRequest(String correo, String password) {
        this.correoCapHum = correo;
        this.contraseñaCapHum = password;
    }
}
