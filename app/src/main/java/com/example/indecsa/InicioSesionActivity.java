package com.example.indecsa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class InicioSesionActivity extends AppCompatActivity {

    private EditText etCorreo, etPassword;
    private Button btnInicioSesion, btnCrearCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_sesion);

        etCorreo = findViewById(R.id.etCorreo);
        etPassword = findViewById(R.id.etPassword);
        btnInicioSesion = findViewById(R.id.btnInicio_sesion);
        btnCrearCuenta = findViewById(R.id.btnCrear_cuenta);

        btnInicioSesion.setOnClickListener(v -> {
            String correo = etCorreo.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (correo.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                // Simulando tu estructura: EstadoActivity mamada = new EstadoActivity();
                Intent mamada = new Intent(this, EstadoActivity.class);
                startActivity(mamada);
            }
        });

        btnCrearCuenta.setOnClickListener(v -> {
            Toast.makeText(this, "Funcionalidad de crear cuenta pendiente", Toast.LENGTH_SHORT).show();
        });
    }
}