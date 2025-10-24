package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class Inicio_sesion extends Fragment {

    public Inicio_sesion() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout del login
        View view = inflater.inflate(R.layout.fragment_inicio_sesion, container, false);

        // Botón de inicio de sesión
        view.findViewById(R.id.btnInicio_sesion).setOnClickListener(v -> {
            // Al presionar, cambia al fragmento Editfichaesp
            Editfichaesp editFichaFragment = new Editfichaesp();
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedorfragmentos, editFichaFragment)
                    .addToBackStack(null)
                    .commit();
        });

        // Botón de crear cuenta
        view.findViewById(R.id.btnCrear_cuenta).setOnClickListener(v -> {
            // También te puede mandar al mismo fragmento por ahora
            Editfichaesp editFichaFragment = new Editfichaesp();
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedorfragmentos, editFichaFragment)
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }
}
