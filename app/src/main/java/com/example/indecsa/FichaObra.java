package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;

public class FichaObra extends Fragment {

    public FichaObra() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ficha_obra, container, false);

        ListView lista = view.findViewById(R.id.listaContratistas);

        // Datos de prueba
        String[] nombres = {"Juan Pérez", "María López", "Carlos Ruiz"};
        String[] descripcion = {"Experto en albañilería", "Arquitecta de interiores", "Supervisor de obras"};
        int[] calificaciones = {5, 3, 4};
        int[] imagenes = {R.drawable.usuario, R.drawable.usuario, R.drawable.usuario};

        FichaObraAdapter adapter = new FichaObraAdapter(
                getContext(),
                nombres,
                descripcion,
                imagenes,
                calificaciones
        );

        lista.setAdapter(adapter);

        return view;
    }
}
