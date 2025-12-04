package com.example.indecsa;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

public class TrabajadoresCapitalHumano extends Fragment {

    private TrabajadorAdapter adapter;
    private String estadoFiltro = "";
    private String especialidadFiltro = "";

    public TrabajadoresCapitalHumano() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trabajadores_capital_humano, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            estadoFiltro = getArguments().getString("ESTADO_SELECCIONADO", "");
            especialidadFiltro = getArguments().getString("ESPECIALIDAD_SELECCIONADA", "");
            actualizarTituloConFiltros();
        }

        configurarListView();
        cargarTrabajadoresFiltrados();
    }

    private void actualizarTituloConFiltros() {
        View view = getView();
        if (view == null) return;

        // ID CORREGIDO: txtTituloObra
        TextView titulo = view.findViewById(R.id.txtTituloObra);
        if (titulo != null) {
            String texto = "Todos los Trabajadores";

            if (!estadoFiltro.isEmpty() && !especialidadFiltro.isEmpty()) {
                texto = "Trabajadores - " + estadoFiltro + " - " + especialidadFiltro;
            } else if (!estadoFiltro.isEmpty()) {
                texto = "Trabajadores - " + estadoFiltro;
            } else if (!especialidadFiltro.isEmpty()) {
                texto = "Trabajadores - " + especialidadFiltro;
            }

            titulo.setText(texto);
        }
    }

    private void configurarListView() {
        View view = getView();
        if (view == null) return;

        adapter = new TrabajadorAdapter(requireContext(), new ArrayList<Trabajador>(), new TrabajadorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Trabajador trabajador) {
                mostrarDetalleTrabajador(trabajador);
            }
        });

        // ID CORREGIDO: listaTrabajador (ListView en lugar de RecyclerView)
        ListView listView = view.findViewById(R.id.listaTrabajador);
        listView.setAdapter(adapter);
    }

    private void cargarTrabajadoresFiltrados() {
        List<Trabajador> todosTrabajadores = obtenerTodosTrabajadores();
        List<Trabajador> trabajadoresFiltrados = new ArrayList<>();

        for (Trabajador trabajador : todosTrabajadores) {
            boolean cumpleEstado = estadoFiltro.isEmpty() || trabajador.getEstado().equals(estadoFiltro);
            boolean cumpleEspecialidad = especialidadFiltro.isEmpty() || trabajador.getEspecialidad().equals(especialidadFiltro);

            if (cumpleEstado && cumpleEspecialidad) {
                trabajadoresFiltrados.add(trabajador);
            }
        }

        adapter.actualizarLista(trabajadoresFiltrados);
    }

    private List<Trabajador> obtenerTodosTrabajadores() {
        List<Trabajador> trabajadores = new ArrayList<>();

        // Datos de ejemplo ACTUALIZADOS con las nuevas especialidades
        trabajadores.add(new Trabajador("Juan Pérez", "Albañil experto", "Obra", "Hidalgo", 4, "Disponible", R.drawable.usuario));
        trabajadores.add(new Trabajador("María García", "Electricista certificada", "Remodelación", "CDMX", 5, "Ocupada", R.drawable.usuario));
        trabajadores.add(new Trabajador("Carlos López", "Plomero con 10 años exp", "Venta de Mobiliario", "Puebla", 3, "Disponible", R.drawable.usuario));
        trabajadores.add(new Trabajador("Ana Rodríguez", "Albañil especializada", "Obra", "Puebla", 4, "Disponible", R.drawable.usuario));
        trabajadores.add(new Trabajador("Pedro Sánchez", "Electricista industrial", "Instalación de Mobiliario", "Hidalgo", 2, "Ocupada", R.drawable.usuario));
        trabajadores.add(new Trabajador("Laura Martínez", "Diseñadora de interiores", "Remodelación", "CDMX", 4, "Disponible", R.drawable.usuario));
        trabajadores.add(new Trabajador("Roberto Jiménez", "Vendedor de muebles", "Venta de Mobiliario", "Hidalgo", 3, "Disponible", R.drawable.usuario));
        trabajadores.add(new Trabajador("Sofía Hernández", "Instaladora profesional", "Instalación de Mobiliario", "Puebla", 5, "Ocupada", R.drawable.usuario));

        return trabajadores;
    }

    private void mostrarDetalleTrabajador(Trabajador trabajador) {
        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.item_trabajador);

        // Configurar datos del trabajador
        TextView txtNombre = dialog.findViewById(R.id.txtNombreTrabajador);
        TextView txtDescripcion = dialog.findViewById(R.id.DescripcionTrabajador);
        TextView txtEspecialidad = dialog.findViewById(R.id.EspecialidadTrabajador);
        TextView txtEquipo = dialog.findViewById(R.id.EquipoTrabajador);

        if (txtNombre != null) txtNombre.setText(trabajador.getNombre());
        if (txtDescripcion != null) txtDescripcion.setText("Descripción: " + trabajador.getDescripcion());
        if (txtEspecialidad != null) txtEspecialidad.setText("Especialidad: " + trabajador.getEspecialidad());
        if (txtEquipo != null) txtEquipo.setText("Estado: " + trabajador.getEstado());

        // Configurar estrellas - USA TUS DIBUJOS EXISTENTES
        configurarEstrellasDialog(dialog, trabajador.getExperiencia());

        // Botones
        Button btnEditar = dialog.findViewById(R.id.btnEditar);
        Button btnDisponibilidad = dialog.findViewById(R.id.btnDisponibilidad);

        if (btnEditar != null) {
            btnEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if (btnDisponibilidad != null) {
            btnDisponibilidad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        dialog.show();
    }

    private void configurarEstrellasDialog(Dialog dialog, int experiencia) {
        int[] starIds = {R.id.star1, R.id.star2, R.id.star3, R.id.star4, R.id.star5};

        for (int i = 0; i < starIds.length; i++) {
            ImageView star = dialog.findViewById(starIds[i]);
            if (star != null) {
                // USA TUS PROPIOS DIBUJOS - cambia por los que tengas
                int drawable = (i < experiencia) ? R.drawable.img : R.drawable.img_1; // ← CAMBIA POR TUS DIBUJOS
                star.setImageResource(drawable);
            }
        }
    }
}