package com.example.indecsa;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log; // Añade esto
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import com.example.indecsa.models.TrabajadorDto;
import com.example.indecsa.network.ApiService;
import com.example.indecsa.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrabajadoresCapitalHumano extends Fragment {

    private static final String TAG = "TrabajadoresAPI"; // Añade esto
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

            // Añade log para ver qué filtros se están enviando
            Log.d(TAG, "Filtros recibidos - Estado: " + estadoFiltro + ", Especialidad: " + especialidadFiltro);

            actualizarTituloConFiltros();
        }

        configurarListView();
        cargarTrabajadoresDeAPI();
    }

    private void actualizarTituloConFiltros() {
        View view = getView();
        if (view == null) return;

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

        ListView listView = view.findViewById(R.id.listaTrabajador);
        listView.setAdapter(adapter);
    }

    private void cargarTrabajadoresDeAPI() {
        ApiService api = RetrofitClient.getClient().create(ApiService.class);

        // Log de la URL que se está llamando
        String url = "trabajadores/filtros?estado=" + estadoFiltro + "&especialidad=" + especialidadFiltro;
        Log.d(TAG, "Llamando a API: " + url);

        api.getTrabajadoresFiltrados(estadoFiltro, especialidadFiltro)
                .enqueue(new Callback<List<TrabajadorDto>>() {
                    @Override
                    public void onResponse(Call<List<TrabajadorDto>> call, Response<List<TrabajadorDto>> response) {
                        Log.d(TAG, "Respuesta recibida. Código: " + response.code());
                        Log.d(TAG, "¿Es exitosa?: " + response.isSuccessful());

                        if (response.isSuccessful() && response.body() != null) {
                            Log.d(TAG, "Trabajadores recibidos: " + response.body().size());

                            // Convertir DTOs a Trabajadores
                            List<Trabajador> trabajadores = Trabajador.fromDtoList(response.body());
                            adapter.actualizarLista(trabajadores);

                            // Mostrar mensaje si no hay resultados
                            if (trabajadores.isEmpty()) {
                                Toast.makeText(getContext(), "No se encontraron trabajadores", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "Lista de trabajadores vacía");
                            } else {
                                Log.d(TAG, "Trabajadores cargados exitosamente: " + trabajadores.size());
                            }
                        } else {
                            String errorMsg = "Error al cargar trabajadores. Código: " + response.code();
                            Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
                            Log.e(TAG, errorMsg);

                            // Cargar datos de ejemplo como fallback
                            cargarDatosEjemplo();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<TrabajadorDto>> call, Throwable t) {
                        String errorMsg = "Error de conexión: " + t.getMessage();
                        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
                        Log.e(TAG, errorMsg);
                        t.printStackTrace();

                        // Cargar datos de ejemplo como fallback
                        cargarDatosEjemplo();
                    }
                });
    }

    private void cargarDatosEjemplo() {
        Log.d(TAG, "Cargando datos de ejemplo...");

        List<Trabajador> trabajadores = obtenerTodosTrabajadores();
        List<Trabajador> trabajadoresFiltrados = new ArrayList<>();

        for (Trabajador trabajador : trabajadores) {
            boolean cumpleEstado = estadoFiltro.isEmpty() || trabajador.getEstado().equals(estadoFiltro);
            boolean cumpleEspecialidad = especialidadFiltro.isEmpty() || trabajador.getEspecialidad().equals(especialidadFiltro);

            if (cumpleEstado && cumpleEspecialidad) {
                trabajadoresFiltrados.add(trabajador);
            }
        }

        adapter.actualizarLista(trabajadoresFiltrados);
        Log.d(TAG, "Datos de ejemplo cargados: " + trabajadoresFiltrados.size());
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

        // Configurar estrellas
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
                int drawable = (i < experiencia) ? R.drawable.img : R.drawable.img_1;
                star.setImageResource(drawable);
            }
        }
    }
}