package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

        // Inflar layout
        View view = inflater.inflate(R.layout.fragment_capital_humano, container, false);

        // Referencias de botones
        ImageButton btnProyectos = view.findViewById(R.id.btnproyectos);
        ImageButton btnTrabajadores = view.findViewById(R.id.btnTrabajadores);
        ImageButton btnOtraCosa = view.findViewById(R.id.btnOtraCosa);

        

        // Botón 2 → Fragmento TrabajadoresEstado
        btnTrabajadores.setOnClickListener(v ->
                abrirFragmento(new TrabajadoresEstadoCapitalHumano())
        );

        // Botón 3 → Otro Fragmento
        btnOtraCosa.setOnClickListener(v ->
                abrirFragmento(new FichasPorEstadoFragment())
        );

        return view;
    }


    private void abrirFragmento(Fragment fragment) {
        FragmentTransaction transaction = requireActivity()
                .getSupportFragmentManager()
                .beginTransaction();

        transaction.replace(R.id.contenedorfragmentos, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
