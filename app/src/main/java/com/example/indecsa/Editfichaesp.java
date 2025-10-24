package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class Editfichaesp extends Fragment {

    public Editfichaesp() {
        // Constructor vacío requerido
    }

    public static Editfichaesp newInstance(String param1, String param2) {
        Editfichaesp fragment = new Editfichaesp();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_editfichaesp, container, false);

        // Obtenemos los botones
        Button btnObra = view.findViewById(R.id.btnObra);
        Button btnRemodelacion = view.findViewById(R.id.btnRemodelacion);
        Button btnVentaMobiliario = view.findViewById(R.id.btnVentaMobiliario);
        Button btnInstalacionMobiliario = view.findViewById(R.id.btnInstalacionMobiliario);

        // Un solo listener para todos
        View.OnClickListener listener = v -> {
            // Obtener texto del botón presionado
            String textoBoton = ((Button) v).getText().toString();

            // Comprobamos según el texto del botón
            Fragment destino;
            switch (textoBoton) {
                case "OBRA":
                    destino = new FichaObra(); // Fragment para OBRA
                    break;
                case "REMODELACIÓN":
                    destino = new FichaObra();
                    break;
                case "VENTA DE MOBILIARIO":
                    destino = new FichaObra();
                    break;
                case "INSTALACIÓN DE MOBILIARIO":
                    destino = new FichaObra();
                    break;
                default:
                    destino = new FichaObra(); // valor por defecto
                    break;
            }

            // Cambiar al fragment correspondiente
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.contenedorfragmentos, destino)
                    .addToBackStack(null)
                    .commit();
        };

        // Asignamos el listener a todos los botones
        btnObra.setOnClickListener(listener);
        btnRemodelacion.setOnClickListener(listener);
        btnVentaMobiliario.setOnClickListener(listener);
        btnInstalacionMobiliario.setOnClickListener(listener);

        return view;
    }
}
