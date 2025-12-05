package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button; // Usaremos botones normales como ejemplo
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class c_h_proyecto_especialidad_puebla extends Fragment {

    // (Opcional) La clave para el estado se puede reutilizar si es necesario
    private static final String KEY_ESTADO = "estado_seleccionado";
    // Clave para enviar la especialidad al siguiente fragmento
    private static final String KEY_ESPECIALIDAD = "especialidad_seleccionada";

    private String estadoSeleccionado; // Variable para almacenar el estado recibido

    public c_h_proyecto_especialidad_puebla() {
        // Constructor vacío requerido
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Recibir el argumento (el estado) que fue enviado desde el fragmento anterior
        if (getArguments() != null) {
            estadoSeleccionado = getArguments().getString(KEY_ESTADO);
            // Opcional: Puedes usar 'estadoSeleccionado' aquí o en el onViewCreated para mostrar el estado
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Asume que el nombre del layout es fragment_ch_proyecto_especialidad_cdmx.xml
        return inflater.inflate(R.layout.fragment_c_h_proyecto_especialidad_puebla, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. Encontrar los botones en el diseño
        Button btnObra = view.findViewById(R.id.btnProObra);
        Button btnRemodelacion = view.findViewById(R.id.btnProRemodelacion);
        Button btnVentaMobiliario = view.findViewById(R.id.btnProVenta);
        Button btnObraInstalacionMobiliario = view.findViewById(R.id.btnProInstalacion);

        // 2. Asignar los eventos de clic para navegar
        btnObra.setOnClickListener(v -> irAProyectoEspecialidad("Obra"));
        btnRemodelacion.setOnClickListener(v -> irAProyectoEspecialidad("Remodelacion"));
        btnVentaMobiliario.setOnClickListener(v -> irAProyectoEspecialidad("Venta Mobiliario"));
        btnObraInstalacionMobiliario.setOnClickListener(v -> irAProyectoEspecialidad("Obra e Instalación Mobiliario"));
    }

    /**
     * Realiza la transición al fragmento de proyecto final, enviando la especialidad seleccionada.
     * @param especialidad La especialidad que se pulsó (ej: "Obra").
     */
    private void irAProyectoEspecialidad(String especialidad) {

        // 1. Determinar qué fragmento instanciar
        Fragment siguienteFragment = null;

        switch (especialidad) {
            case "Obra":
                siguienteFragment = new c_h_proyecto_obra_puebla();
                break;
            case "Remodelacion":
                siguienteFragment = new c_h_proyecto_remodelacion_puebla();
                break;
            case "Venta Mobiliario":
                siguienteFragment = new c_h_proyecto_venta_mobiliario_puebla();
                break;
            case "Obra e Instalación Mobiliario":
                siguienteFragment = new c_h_proyecto_obra_intalacion_mobiliario_CDMX();
                break;
        }

        // 2. Empaquetar la información en un Bundle
        Bundle args = new Bundle();
        args.putString(KEY_ESPECIALIDAD, especialidad);
        siguienteFragment.setArguments(args);

        // 3. Realizar la transacción de fragmentos
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contenedorfragmentos, siguienteFragment)
                    .addToBackStack(null)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
        }
    }
}