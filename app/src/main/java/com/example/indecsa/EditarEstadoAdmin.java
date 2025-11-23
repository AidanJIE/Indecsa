package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class EditarEstadoAdmin extends Fragment {

    public EditarEstadoAdmin() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_asignaredoadmin, container, false);

        // Configurar los botones de estados
        configurarBotonesEstados(view);

        return view;
    }

    private void configurarBotonesEstados(View view) {
        view.findViewById(R.id.btnImagen1).setOnClickListener(v -> {
            navegarAFichaAgrega("HIDALGO");
        });

        view.findViewById(R.id.btnImagen2).setOnClickListener(v -> {
            navegarAFichaAgrega("CDMX");
        });

        view.findViewById(R.id.btnImagen3).setOnClickListener(v -> {
            navegarAFichaAgrega("PUEBLA");
        });
    }

    private void navegarAFichaAgrega(String estado) {
        Fragment ficha = new Editfichaesp();
        Bundle args = new Bundle();
        args.putString("estado", estado);
        ficha.setArguments(args);

        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedorfragmentos, ficha)
                .addToBackStack(null)
                .commit();
    }

}