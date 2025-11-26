package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Registrarse extends Fragment {

    EditText ediNombre, ediCorreo;
    Spinner spinnerRol;
    Button btnRegistrar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_registrarse, container, false);

        ediNombre = view.findViewById(R.id.ediNombre);
        ediCorreo = view.findViewById(R.id.ediCorreo);
        spinnerRol = view.findViewById(R.id.spinnerRol);
        btnRegistrar = view.findViewById(R.id.btnRegistrar);

        // Opciones del spinner
        String[] roles = {"Capital humano", "Administrador"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                roles
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRol.setAdapter(adapter);

        btnRegistrar.setOnClickListener(v -> registrarUsuario());

        return view;
    }

    private void registrarUsuario() {
        String nombre = ediNombre.getText().toString().trim();
        String correo = ediCorreo.getText().toString().trim();
        String rol = spinnerRol.getSelectedItem().toString();

        if (nombre.isEmpty() || correo.isEmpty()) {
            Toast.makeText(requireContext(), "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(requireContext(),
                "Registrado:\nNombre: " + nombre +
                        "\nCorreo: " + correo +
                        "\nRol: " + rol,
                Toast.LENGTH_LONG).show();
    }
}
