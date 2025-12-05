package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class EditarContratista extends Fragment {

    private static final String ARG_NOMBRE = "nombre";
    private static final String ARG_DESCRIPCION = "descripcion";

    private String nombreContratista;
    private String descripcionContratista;

    private float calificacion = 0f;

    public EditarContratista() {}

    public static EditarContratista newInstance(String nombre, String descripcion) {
        EditarContratista fragment = new EditarContratista();
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

        EditText edtNombre = view.findViewById(R.id.edinombrecont);
        EditText edtDescripcion = view.findViewById(R.id.edidatit2);
        Spinner spnEspecialidad = view.findViewById(R.id.spinnerEspecialidad);
        Spinner spnEstado = view.findViewById(R.id.spinnerEstado);
        TextView txtMensaje = view.findViewById(R.id.txtMensaje);
        Button btnEditar = view.findViewById(R.id.btnEditar);
        RatingBar ratingBar = view.findViewById(R.id.ratingExperiencia); // NUEVO

        // Cargar datos
        edtNombre.setText(nombreContratista);
        edtDescripcion.setText(descripcionContratista);

        // Spinner especialidad
        String[] especialidades = {"OBRA", "REMODELACIÓN", "VENTA DE MOBILIARIO", "INSTALACIÓN DE MOBILIARIO"};
        ArrayAdapter<String> adapterEsp = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, especialidades);
        adapterEsp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnEspecialidad.setAdapter(adapterEsp);

        // Spinner estado
        String[] estados = {"Hidalgo", "CDMX", "Puebla"};
        ArrayAdapter<String> adapterEst = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, estados);
        adapterEst.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnEstado.setAdapter(adapterEst);

        // RatingBar listener
        ratingBar.setOnRatingBarChangeListener((ratingBar1, rating, fromUser) -> {
            calificacion = rating;
        });

        // Botón guardar
        btnEditar.setOnClickListener(v -> {
            txtMensaje.setText("SE EDITÓ CORRECTAMENTE (Calificación: " + calificacion + ")");
            txtMensaje.setVisibility(View.VISIBLE);
        });

        return view;
    }
}
