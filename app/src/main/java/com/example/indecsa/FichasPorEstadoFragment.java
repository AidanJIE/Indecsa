package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FichasPorEstadoFragment extends Fragment {

    public FichasPorEstadoFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fichas_estado, container, false);

        // Referenciar botones
        ImageButton btnHidalgo = view.findViewById(R.id.btnHidalgo);
        ImageButton btnCDMX = view.findViewById(R.id.btnCDMX);
        ImageButton btnPuebla = view.findViewById(R.id.btnPuebla);

        // Listener: abrir fragment de especialidad con parámetro
        btnHidalgo.setOnClickListener(v -> abrirEspecialidad("Hidalgo"));
        btnCDMX.setOnClickListener(v -> abrirEspecialidad("CDMX"));
        btnPuebla.setOnClickListener(v -> abrirEspecialidad("Puebla"));

        return view;
    }


    private void abrirEspecialidad(String estadoSeleccionado) {

        // Crear instancia del nuevo fragment
        EspecialidadFragment fragment = new EspecialidadFragment();

        // Crear bundle con dato del estado
        Bundle args = new Bundle();
        args.putString("estado", estadoSeleccionado);

        // Enviar parámetro al fragment
        fragment.setArguments(args);

        // Reemplazar fragmento
        FragmentTransaction transaction = requireActivity()
                .getSupportFragmentManager()
                .beginTransaction();

        transaction.replace(R.id.contenedorfragmentos, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
