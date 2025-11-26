package com.example.indecsa.models;

public class LoginResponse {

    private boolean success;
    private String message;
    private CapitalHumano capitalHumano;

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public CapitalHumano getCapitalHumano() { return capitalHumano; }

    public static class CapitalHumano {
        private int idCapHum;
        private String correoCapHum;

        public int getIdCapHum() { return idCapHum; }
        public String getCorreoCapHum() { return correoCapHum; }
    }
}
