package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class CapitalHumano extends Fragment {

    public CapitalHumano() {
        // Required empty public constructor
    }

    public static CapitalHumano newInstance(String param1, String param2) {
        CapitalHumano fragment = new CapitalHumano();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflar el layout
        View view = inflater.inflate(R.layout.fragment_capital_humano, container, false);

        // Obtener referencias de los botones
        ImageButton btnProyectos = view.findViewById(R.id.btnproyectos);
        ImageButton btnTrabajadores = view.findViewById(R.id.btnTrabajadores);
        ImageButton btnOtraCosa = view.findViewById(R.id.btnOtraCosa);

        // Evento botón 1
        btnProyectos.setOnClickListener(v ->
                Toast.makeText(getContext(), "PROYECTOS", Toast.LENGTH_SHORT).show()
        );

        // Evento botón 2
        btnTrabajadores.setOnClickListener(v ->
                Toast.makeText(getContext(), "TRABAJADORES", Toast.LENGTH_SHORT).show()
        );

        // Evento botón 3
        btnOtraCosa.setOnClickListener(v ->
                Toast.makeText(getContext(), "OPCIÓN", Toast.LENGTH_SHORT).show()
        );

        return view;
    }
}
