package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EstadoFragment extends Fragment {

    private ImageButton btnHidalgo, btnCDMX, btnPuebla;

    public EstadoFragment() {
        // Constructor vacío
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fichas_estado, container, false);

        btnHidalgo = view.findViewById(R.id.btnHidalgo);
        btnCDMX = view.findViewById(R.id.btnCDMX);
        btnPuebla = view.findViewById(R.id.btnPuebla);

        btnHidalgo.setOnClickListener(v -> abrirEspecialidad("Hidalgo"));
        btnCDMX.setOnClickListener(v -> abrirEspecialidad("CDMX"));
        btnPuebla.setOnClickListener(v -> abrirEspecialidad("Puebla"));

        return view;
    }

    // 🔥 Método CORRECTO para cambiar de fragmentos desde un fragmento
    private void abrirEspecialidad(String estado) {

        EspecialidadFragment fragment = EspecialidadFragment.newInstance(estado);

        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedorfragmentos, fragment)
                .addToBackStack(null)
                .commit();
    }
}
