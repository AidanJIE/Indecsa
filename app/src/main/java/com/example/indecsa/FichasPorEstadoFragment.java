package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FichasPorEstadoFragment extends Fragment {

    public FichasPorEstadoFragment() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflar el layout
        View view = inflater.inflate(R.layout.fichas_estado, container, false);

        // Referenciar botones
        ImageButton btnImagen1 = view.findViewById(R.id.btnImagen1);
        ImageButton btnImagen2 = view.findViewById(R.id.btnImagen2);
        ImageButton btnImagen3 = view.findViewById(R.id.btnImagen3);

        // Listeners de ejemplo (puedes cambiar a abrir actividades o fragmentos)
        btnImagen1.setOnClickListener(v ->
                Toast.makeText(getContext(), "Hidalgo seleccionado", Toast.LENGTH_SHORT).show()
        );

        btnImagen2.setOnClickListener(v ->
                Toast.makeText(getContext(), "CDMX seleccionado", Toast.LENGTH_SHORT).show()
        );

        btnImagen3.setOnClickListener(v ->
                Toast.makeText(getContext(), "Puebla seleccionado", Toast.LENGTH_SHORT).show()
        );

        return view;
    }
}