package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class Inicio_sesion extends Fragment {

    private EditText etCorreo, etPassword;
    private Button btnInicioSesion, btnCrearCuenta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflar el layout del fragmento
        View view = inflater.inflate(R.layout.inicio_sesion, container, false);

        // Enlazar vistas
        etCorreo = view.findViewById(R.id.etCorreo);
        etPassword = view.findViewById(R.id.etPassword);
        btnInicioSesion = view.findViewById(R.id.btnInicio_sesion);
        btnCrearCuenta = view.findViewById(R.id.btnCrear_cuenta);

        // Acción de iniciar sesión
        btnInicioSesion.setOnClickListener(v -> {
            String correo = etCorreo.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (correo.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(requireContext(), "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

                // Aquí puedes reemplazar el fragmento por EstadoActivity o abrir otro fragmento
                // Ejemplo: abrir EstadoActivity como Activity
                // Intent intent = new Intent(requireContext(), EstadoActivity.class);
                // startActivity(intent);
            }
        });

        // Acción de crear cuenta
        btnCrearCuenta.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Funcionalidad de crear cuenta pendiente", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}