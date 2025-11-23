package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class EditarContratista extends Fragment {

    private static final String ARG_NOMBRE = "nombre";
    private static final String ARG_DESCRIPCION = "descripcion";

    private String nombreContratista;
    private String descripcionContratista;

    private int calificacion = 0;

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

        // ==============================
        // REFERENCIAS A VISTAS
        // ==============================

        EditText edtNombre = view.findViewById(R.id.edinombrecont);
        EditText edtDescripcion = view.findViewById(R.id.edidatit2);
        Spinner spnEspecialidad = view.findViewById(R.id.spinnerEspecialidad);
        Spinner spnEstado = view.findViewById(R.id.spinnerEstado);
        TextView txtMensaje = view.findViewById(R.id.txtMensaje);
        Button btnEditar = view.findViewById(R.id.btnEditar);

        ImageButton star1 = view.findViewById(R.id.star1);
        ImageButton star2 = view.findViewById(R.id.star2);
        ImageButton star3 = view.findViewById(R.id.star3);
        ImageButton star4 = view.findViewById(R.id.star4);
        ImageButton star5 = view.findViewById(R.id.star5);

        // ==============================
        // MOSTRAR DATOS EN LOS CAMPOS
        // ==============================

        edtNombre.setText(nombreContratista);
        edtDescripcion.setText(descripcionContratista);

        // ==============================
        // SPINNER ESPECIALIDAD
        // ==============================

        String[] especialidades = {"OBRA", "REMODELACIÓN", "VENTA DE MOBILIARIO", "INSTALACIÓN DE MOBILIARIO"};
        ArrayAdapter<String> adapterEsp = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, especialidades);
        adapterEsp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnEspecialidad.setAdapter(adapterEsp);

        // ==============================
        // SPINNER ESTADO
        // ==============================

        String[] estados = {"Hidalgo", "CDMX", "Puebla"};
        ArrayAdapter<String> adapterEst = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, estados);
        adapterEst.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnEstado.setAdapter(adapterEst);

        // ==============================
        // SISTEMA DE ESTRELLAS (CALIFICACIÓN)
        // ==============================

        View.OnClickListener listenerStar = v -> {
            int id = v.getId();

            if (id == R.id.star1) calificacion = 1;
            else if (id == R.id.star2) calificacion = 2;
            else if (id == R.id.star3) calificacion = 3;
            else if (id == R.id.star4) calificacion = 4;
            else if (id == R.id.star5) calificacion = 5;

            actualizarEstrellas(star1, star2, star3, star4, star5);
        };


        star1.setOnClickListener(listenerStar);
        star2.setOnClickListener(listenerStar);
        star3.setOnClickListener(listenerStar);
        star4.setOnClickListener(listenerStar);
        star5.setOnClickListener(listenerStar);

        // ==============================
        // BOTÓN GUARDAR
        // ==============================

        btnEditar.setOnClickListener(v -> {
            txtMensaje.setText("SE EDITÓ CORRECTAMENTE (Calificación: " + calificacion + ")");
            txtMensaje.setVisibility(View.VISIBLE);
        });

        return view;
    }

    private void actualizarEstrellas(ImageButton... stars) {
        for (int i = 0; i < stars.length; i++) {
            if (i < calificacion)
                stars[i].setImageResource(R.drawable.ic_star);
            else
                stars[i].setImageResource(R.drawable.estrellavacia);
        }
    }
}
