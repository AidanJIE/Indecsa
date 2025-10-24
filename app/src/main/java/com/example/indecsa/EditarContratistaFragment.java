package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class EditarContratistaFragment extends Fragment {

    private String nombre;
    private String descripcion;
    private String proyecto;

    public EditarContratistaFragment() {
        // Constructor vacío requerido
    }

    public static EditarContratistaFragment newInstance(String nombre, String descripcion, String proyecto) {
        EditarContratistaFragment fragment = new EditarContratistaFragment();
        Bundle args = new Bundle();
        args.putString("nombre", nombre);
        args.putString("descripcion", descripcion);
        args.putString("proyecto", proyecto);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombre = getArguments().getString("nombre");
            descripcion = getArguments().getString("descripcion");
            proyecto = getArguments().getString("proyecto");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editar_contratista, container, false);

        // Referencias al layout
        TextView txtNombre = view.findViewById(R.id.txtNombre);
        TextView txtDescripcion = view.findViewById(R.id.txtDescripcion);
        TextView txtProyecto = view.findViewById(R.id.txtProyecto);

        // Mostrar datos recibidos (o inventados)
        txtNombre.setText("Nombre: " + (nombre != null ? nombre : "Juan Pérez"));
        txtDescripcion.setText("Descripción: " + (descripcion != null ? descripcion : "Contratista con 5 años de experiencia"));
        txtProyecto.setText("Proyecto actual: " + (proyecto != null ? proyecto : "Remodelación de oficinas"));

        return view;
    }
}