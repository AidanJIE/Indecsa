package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

public class fichaagrega extends Fragment {

    private String estadoRecibido;
    private String especialidadRecibida;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fichaagrega, container, false);

        if (getArguments() != null) {
            estadoRecibido = getArguments().getString("estadoSeleccionado");
            especialidadRecibida = getArguments().getString("especialidadSeleccionada");
        }

        configurarSpinners(view);

        return view;
    }

    private void configurarSpinners(View view) {

        Spinner spinnerEstado = view.findViewById(R.id.spinnerEstado);
        Spinner spinnerEspecialidad = view.findViewById(R.id.spinnerEspecialidad);

        // ESTADO
        String[] estados = new String[] { estadoRecibido };
        ArrayAdapter<String> adapterEstado = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, estados);
        adapterEstado.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(adapterEstado);
        spinnerEstado.setEnabled(false); // BLOQUEADO

        // ESPECIALIDAD
        String[] especialidad = new String[] { especialidadRecibida };
        ArrayAdapter<String> adapterEsp = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, especialidad);
        adapterEsp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEspecialidad.setAdapter(adapterEsp);
        spinnerEspecialidad.setEnabled(false); // BLOQUEADO
    }
}
