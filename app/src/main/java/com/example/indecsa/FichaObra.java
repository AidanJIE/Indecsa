package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.fragment.app.Fragment;

public class FichaObra extends Fragment {

    public FichaObra() {
        // Constructor vacío
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ficha_obra, container, false);

        // Inicializar ListView
        ListView lista = view.findViewById(R.id.listaContratistas);

        // Datos de prueba (luego puedes cargarlos de BD)
        String[] nombres = {"Juan Pérez", "María López", "Carlos Ruiz"};
        String[] descripcion = {"Experto en albañilería", "Arquitecta de interiores", "Supervisor de obras"};
        int[] imagenes = {R.drawable.usuario, R.drawable.usuario, R.drawable.usuario};

        // Adaptador personalizado
        FichaObraAdapter adapter = new FichaObraAdapter(getContext(), nombres, descripcion, imagenes);
        lista.setAdapter(adapter);

        return view;
    }
}
