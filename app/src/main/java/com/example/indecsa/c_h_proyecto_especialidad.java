package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

// CAMBIO AQUÍ: Nombre de la clase
public class c_h_proyecto_especialidad extends Fragment {

    private String ProyectoSeleccionado = "";

    public c_h_proyecto_especialidad() {
        // Constructor vacío requerido
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Asegúrate de que este layout (XML) exista en tu proyecto:
        return inflater.inflate(R.layout.fragment_c_h_proyecto_especialidad, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            ProyectoSeleccionado = getArguments().getString("PROYECTO_SELECCIONADO", "");
            actualizarTitulo();
        }

        configurarBotonesEspecialidad();
    }

    private void actualizarTitulo() {
        View view = getView();
        if (view != null) {
            TextView titulo = view.findViewById(R.id.txtTitulo);
            if (titulo != null) {
                titulo.setText("Especialidad - " + ProyectoSeleccionado);
            }
        }
    }

    private void configurarBotonesEspecialidad() {
        View view = getView();
        if (view == null) return;

        // Se mantienen los IDs de los botones del layout original (R.id.btnObra, etc.)
        view.findViewById(R.id.btnObra).setOnClickListener(v -> navegarATodaFichas("Obra"));

        view.findViewById(R.id.btnRemodelacion).setOnClickListener(v -> navegarATodaFichas("Remodelación"));

        view.findViewById(R.id.btnVentaMobiliario).setOnClickListener(v -> navegarATodaFichas("Venta de Mobiliario"));

        view.findViewById(R.id.btnInstalacionMobiliario).setOnClickListener(v -> navegarATodaFichas("Instalación de Mobiliario"));
    }

    // CAMBIO AQUÍ: Nombre del método y fragmento de destino
    private void navegarATodaFichas(String especialidadSeleccionada) {
        Bundle bundle = new Bundle();
        bundle.putString("ESTADO_SELECCIONADO", ProyectoSeleccionado);
        bundle.putString("ESPECIALIDAD_SELECCIONADA", especialidadSeleccionada);

        // CAMBIO AQUÍ: Fragmento de destino
        c_h_proyecto_toda_fichas todaFichasFragment = new c_h_proyecto_toda_fichas();
        todaFichasFragment.setArguments(bundle);

        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedorfragmentos, todaFichasFragment)
                .addToBackStack(null)
                .commit();
    }
}