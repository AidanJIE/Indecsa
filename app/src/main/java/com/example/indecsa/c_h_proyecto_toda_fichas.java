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

public class c_h_proyecto_toda_fichas extends Fragment {

    private ProyectoAdapter adapter;
    private String estadoFiltro = "";
    private String especialidadFiltro = "";

    public c_h_proyecto_toda_fichas() {
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
        cargarProyectosFiltrados();
    }

    private void actualizarTituloConFiltros() {
        View view = getView();
        if (view == null) return;

        TextView titulo = view.findViewById(R.id.txtTituloObra);
        if (titulo != null) {
            String texto = "Todos los Proyectos";

            if (!estadoFiltro.isEmpty() && !especialidadFiltro.isEmpty()) {
                texto = "Proyectos - " + estadoFiltro + " - " + especialidadFiltro;
            } else if (!estadoFiltro.isEmpty()) {
                texto = "Proyectos - " + estadoFiltro;
            } else if (!especialidadFiltro.isEmpty()) {
                texto = "Proyectos - " + especialidadFiltro;
            }

            titulo.setText(texto);
        }
    }

    private void configurarListView() {
        View view = getView();
        if (view == null) return;

        adapter = new ProyectoAdapter(requireContext(), new ArrayList<Proyecto>(), new ProyectoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Proyecto proyecto) {
                mostrarDetalleProyecto(proyecto);
            }
        });

        ListView listView = view.findViewById(R.id.listaTrabajador);
        listView.setAdapter(adapter);
    }

    private void cargarProyectosFiltrados() {
        List<Proyecto> todosProyectos = obtenerTodosProyectos();
        List<Proyecto> proyectosFiltrados = new ArrayList<>();

        for (Proyecto proyecto : todosProyectos) {
            boolean cumpleEstado = estadoFiltro.isEmpty() || proyecto.getEstado().equals(estadoFiltro);
            boolean cumpleEspecialidad = especialidadFiltro.isEmpty() || proyecto.getEspecialidad().equals(especialidadFiltro);

            if (cumpleEstado && cumpleEspecialidad) {
                proyectosFiltrados.add(proyecto);
            }
        }

        adapter.actualizarLista(proyectosFiltrados);
    }

    private List<Proyecto> obtenerTodosProyectos() {
        List<Proyecto> proyectos = new ArrayList<>();

        // CORRECCIÓN AQUÍ: Se usan los nuevos nombres de campos y el constructor de Proyecto:
        // Constructor: Proyecto(proyectito, descripcion, especialidad, estado, avance, direccion, imagenResId)

        proyectos.add(new Proyecto("Construcción Edificio CDMX", "Obra mayor de 10 pisos", "Obra", "CDMX", 4, "Av. Reforma #100", R.drawable.usuario));
        proyectos.add(new Proyecto("Remodelación Tienda Puebla", "Modernización de fachada e interiores", "Remodelación", "Puebla", 5, "Calle 5 Sur #201", R.drawable.usuario));
        proyectos.add(new Proyecto("Diseño Oficina Hidalgo", "Venta e instalación de mobiliario corporativo", "Venta de Mobiliario", "Hidalgo", 3, "Blvd. Nuevo Milenio #5", R.drawable.usuario));
        proyectos.add(new Proyecto("Instalación Cocinas Puebla", "Instalación de 50 cocinas prefabricadas", "Instalación de Mobiliario", "Puebla", 4, "Fracc. La Noria, Bodega 3", R.drawable.usuario));
        proyectos.add(new Proyecto("Rehabilitación Casa CDMX", "Reparación estructural y acabados", "Remodelación", "CDMX", 4, "Cerrada de Tlalpan #12", R.drawable.usuario));

        return proyectos;
    }

    private void mostrarDetalleProyecto(Proyecto proyecto) {
        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.item_trabajador);

        // Configurar datos del proyecto
        // NOTA: Se mantienen los IDs del layout (R.id.txtNombreTrabajador, etc.) pero se usan para mostrar datos de Proyecto
        TextView txtNombre = dialog.findViewById(R.id.txtNombreTrabajador);
        TextView txtDescripcion = dialog.findViewById(R.id.DescripcionTrabajador);
        TextView txtEspecialidad = dialog.findViewById(R.id.EspecialidadTrabajador);
        TextView txtEquipo = dialog.findViewById(R.id.EquipoTrabajador);

        // CORRECCIÓN AQUÍ: Uso de los nuevos Getters
        if (txtNombre != null) txtNombre.setText(proyecto.getProyectito()); // Usa getProyectito()
        if (txtDescripcion != null) txtDescripcion.setText("Descripción: " + proyecto.getDescripcion());
        if (txtEspecialidad != null) txtEspecialidad.setText("Especialidad: " + proyecto.getEspecialidad());
        // Se usa getDireccion() para el campo "EquipoTrabajador" y getEstado() para el texto del título
        if (txtEquipo != null) txtEquipo.setText("Dirección: " + proyecto.getDireccion());

        // CORRECCIÓN AQUÍ: Uso del nuevo Getter
        configurarEstrellasDialog(dialog, proyecto.getAvance()); // Usa getAvance()

        // Botones (se mantienen sus IDs)
        // ... (Lógica de botones se mantiene) ...

        dialog.show();
    }

    private void configurarEstrellasDialog(Dialog dialog, int avance) {
        int[] starIds = {R.id.star1, R.id.star2, R.id.star3, R.id.star4, R.id.star5};

        for (int i = 0; i < starIds.length; i++) {
            ImageView star = dialog.findViewById(starIds[i]);
            if (star != null) {
                int drawable = (i < avance) ? R.drawable.img : R.drawable.img_1;
                star.setImageResource(drawable);
            }
        }
    }
}