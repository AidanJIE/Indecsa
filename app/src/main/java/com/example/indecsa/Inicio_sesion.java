package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class Inicio_sesion extends Fragment {

    public Inicio_sesion() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del login
        View view = inflater.inflate(R.layout.fragment_inicio_sesion, container, false);

        // Aquí puedes agregar la lógica de los botones
        // Por ejemplo:
        view.findViewById(R.id.btnInicio_sesion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para iniciar sesión
            }
        });

        view.findViewById(R.id.btnCrear_cuenta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para crear cuenta
            }
        });

        return view;
    }
}