package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class EditarContratistaFragment extends Fragment {

    private static final String ARG_NOMBRE = "nombre";
    private static final String ARG_DESCRIPCION = "descripcion";
    private String nombreContratista;
    private String descripcionContratista;

    public EditarContratistaFragment() {
        // Constructor vacío requerido
    }

    public static EditarContratistaFragment newInstance(String nombre, String descripcion) {
        EditarContratistaFragment fragment = new EditarContratistaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NOMBRE, nombre);
        args.putString(ARG_DESCRIPCION, descripcion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombreContratista = getArguments().getString(ARG_NOMBRE);
            descripcionContratista = getArguments().getString(ARG_DESCRIPCION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editar_contratista, container, false);

        // Configurar los datos del contratista

        TextView txtMensaje = view.findViewById(R.id.txtMensaje);
        Button btnEditar = view.findViewById(R.id.btnEditar);
        Button btnAgregar = view.findViewById(R.id.btnAgregar);

        // Mostrar datos actuales
        

        // Configurar botón EDITAR
        btnEditar.setOnClickListener(v -> {
            // Aquí iría la lógica para editar el contratista
            // Por ahora solo mostramos el mensaje de confirmación
            txtMensaje.setVisibility(View.VISIBLE);
        });

        // Configurar botón AGREGAR
        btnAgregar.setOnClickListener(v -> {
            // Aquí iría la lógica para agregar nuevo proyecto
        });

        return view;
    }
}