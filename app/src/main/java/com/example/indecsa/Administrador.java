package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class Administrador extends Fragment {

    public Administrador() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del menú principal (tu XML con los botones Agregar/Editar)
        return inflater.inflate(R.layout.fragment_administrador, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Aquí puedes configurar los botones del menú si los necesitas
        view.findViewById(R.id.btnImagen1).setOnClickListener(v -> {
            // Acción para el botón "Agregar"
        });

        view.findViewById(R.id.btnImagen2).setOnClickListener(v -> {
            // Acción para el botón "Editar"
        });
    }
}