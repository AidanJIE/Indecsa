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

        // Configurar los botones de especialidades
        configurarBotonesEspecialidades(view);

        return view;
    }

    private void configurarBotonesEspecialidades(View view) {
        // Botón OBRA
        view.findViewById(R.id.btnObra).setOnClickListener(v -> {
            navegarAEstados("OBRA");
        });

        // Botón REMODELACION
        view.findViewById(R.id.btnRemodelacion).setOnClickListener(v -> {
            navegarAEstados("REMODELACION");
        });

        // Botón VENTA DE MOBILIARIO
        view.findViewById(R.id.btnVentaMobiliario).setOnClickListener(v -> {
            navegarAEstados("VENTA_MOBILIARIO");
        });

        // Botón INSTALACION DE MOBILIARIO
        view.findViewById(R.id.btnInstalacionMobiliario).setOnClickListener(v -> {
            navegarAEstados("INSTALACION_MOBILIARIO");
        });
    }

    private void navegarAEstados(String especialidad) {
        // Crear instancia del fragmento de estados y pasar la especialidad
         fichaagrega estadosFrafment = new fichaagrega ();

        // Reemplazar el fragment actual
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedorfragmentos, estadosFrafment)
                .addToBackStack(null) // Para poder volver
                .commit();
    }
}