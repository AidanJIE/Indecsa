package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class AgregarFicha extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agregar_ficha, container, false);

        // Configurar los botones de estados
        configurarBotonesEstados(view);

        return view;
    }

    private void configurarBotonesEstados(View view) {
        // Botón Hidalgo
        view.findViewById(R.id.btnImagen1).setOnClickListener(v -> {
            // Aquí puedes navegar a un fragment para agregar ficha en Hidalgo
        });

        // Botón CDMX
        view.findViewById(R.id.btnImagen2).setOnClickListener(v -> {
            // Aquí puedes navegar a un fragment para agregar ficha en CDMX
        });

        // Botón Puebla
        view.findViewById(R.id.btnImagen3).setOnClickListener(v -> {
            // Aquí puedes navegar a un fragment para agregar ficha en Puebla
        });
    }
}