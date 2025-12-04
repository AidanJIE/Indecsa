package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EspecialidadFragment extends Fragment {

    private Button btnAlbanileria, btnElectricidad, btnPintura, btnPlomeria, btnCarpinteria;
    private TextView txtTituloEspecialidad;

    private String estadoSeleccionado;

    public EspecialidadFragment() {
        // Constructor vacío
    }

    public static EspecialidadFragment newInstance(String estado) {
        EspecialidadFragment fragment = new EspecialidadFragment();
        Bundle args = new Bundle();
        args.putString("estado", estado);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.especialidad, container, false);

        // Recibir estado desde argumentos
        if (getArguments() != null) {
            estadoSeleccionado = getArguments().getString("estado");
        }

        txtTituloEspecialidad = view.findViewById(R.id.txtTituloEspecialidad);
        if (estadoSeleccionado != null) {
            txtTituloEspecialidad.setText("Especialidades para " + estadoSeleccionado);
        }

        // Botones
        btnAlbanileria = view.findViewById(R.id.btnAlbanileria);
        btnElectricidad = view.findViewById(R.id.btnElectricidad);
        btnPintura = view.findViewById(R.id.btnPintura);
        btnPlomeria = view.findViewById(R.id.btnPlomeria);
        btnCarpinteria = view.findViewById(R.id.btnCarpinteria);

        // Listeners → abrir CarteraFragment con estado+especialidad
        btnAlbanileria.setOnClickListener(v -> abrirCartera("Albañilería"));
        btnElectricidad.setOnClickListener(v -> abrirCartera("Electricidad"));
        btnPintura.setOnClickListener(v -> abrirCartera("Pintura"));
        btnPlomeria.setOnClickListener(v -> abrirCartera("Plomería"));
        btnCarpinteria.setOnClickListener(v -> abrirCartera("Carpintería"));

        return view;
    }

    private void abrirCartera(String especialidad) {

        Cartera fragment = new Cartera();

        // Pasar estado + especialidad a CarteraFragment
        Bundle args = new Bundle();
        args.putString("estado", estadoSeleccionado);
        args.putString("especialidad", especialidad);
        fragment.setArguments(args);

        // Reemplazar fragmento
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contenedorfragmentos, fragment)
                .addToBackStack(null)
                .commit();
    }
}
