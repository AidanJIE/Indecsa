package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.fragment.app.Fragment;

public class Inicio_sesion extends Fragment {

    private EditText etCorreo, etPassword;

    public Inicio_sesion() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inicio_sesion, container, false);

        etCorreo = view.findViewById(R.id.etCorreo);
        etPassword = view.findViewById(R.id.etPassword);

        // Botón de inicio de sesión → manda a EstadoFragment
        view.findViewById(R.id.btnInicio_sesion)
                .setOnClickListener(v -> irAlEstadoFragment());

        // Botón de crear cuenta → también manda a EstadoFragment
        view.findViewById(R.id.btnCrear_cuenta)
                .setOnClickListener(v -> irAlEstadoFragment());

        return view;
    }

    // Método que abre EstadoFragment
    private void irAlEstadoFragment() {
        EstadoFragment estadoFragment = new EstadoFragment();
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedorfragmentos, estadoFragment)
                .addToBackStack(null)
                .commit();
    }
}