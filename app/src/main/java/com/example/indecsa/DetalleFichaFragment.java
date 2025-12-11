package com.example.indecsa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class DetalleFichaFragment extends Fragment {

    // Constantes para los argumentos
    private static final String ARG_NOMBRE = "nombre";
    private static final String ARG_DESCRIPCION = "descripcion";
    private static final String ARG_PROYECTO = "proyecto";
    private static final String ARG_EQUIPO = "equipo";
    private static final String ARG_ESTADO = "estado";
    private static final String ARG_ESPECIALIDAD = "especialidad";

    public DetalleFichaFragment() {
        // Required empty public constructor
    }

    public static DetalleFichaFragment newInstance(String nombre, String descripcion,
                                                   String proyecto, String equipo,
                                                   String estado, String especialidad) {
        DetalleFichaFragment fragment = new DetalleFichaFragment();
        Bundle args = new Bundle();

        args.putString(ARG_NOMBRE, nombre);
        args.putString(ARG_DESCRIPCION, descripcion);
        args.putString(ARG_PROYECTO, proyecto);
        args.putString(ARG_EQUIPO, equipo);
        args.putString(ARG_ESTADO, estado);
        args.putString(ARG_ESPECIALIDAD, especialidad);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_ficha, container, false);

        Bundle args = getArguments();
        if (args != null) {
            String nombre = args.getString(ARG_NOMBRE, "");
            String descripcion = args.getString(ARG_DESCRIPCION, "");
            String proyecto = args.getString(ARG_PROYECTO, "");
            String equipo = args.getString(ARG_EQUIPO, "");
            String estado = args.getString(ARG_ESTADO, "");
            String especialidad = args.getString(ARG_ESPECIALIDAD, "");

            // Referencias a los TextView
            TextView txtNombre = view.findViewById(R.id.detalleNombre);
            TextView txtDescripcion = view.findViewById(R.id.detalleDescripcion);
            TextView txtProyecto = view.findViewById(R.id.detalleProyecto);
            TextView txtEquipo = view.findViewById(R.id.detalleEquipo);

            // Campos adicionales
            TextView txtEstado = view.findViewById(R.id.detalleEstado);
            TextView txtEspecialidad = view.findViewById(R.id.detalleEspecialidad);
            TextView txtTelefono = view.findViewById(R.id.detalleTelefono);
            TextView txtCorreo = view.findViewById(R.id.detalleCorreo);
            TextView txtFechaRegistro = view.findViewById(R.id.detalleFechaRegistro);

            // Asignar datos principales
            txtNombre.setText(nombre);
            txtDescripcion.setText(descripcion);
            txtProyecto.setText(proyecto);
            txtEquipo.setText(equipo);

            // Asignar datos adicionales
            if (txtEstado != null) {
                txtEstado.setText("Estado: " + estado);
            }
            if (txtEspecialidad != null) {
                txtEspecialidad.setText("Especialidad: " + especialidad);
            }
            if (txtTelefono != null) {
                txtTelefono.setText("Teléfono: 55-1234-5678"); // Ejemplo
            }
            if (txtCorreo != null) {
                txtCorreo.setText("Correo: " + nombre.toLowerCase().replace(" ", ".") + "@indecsa.com");
            }
            if (txtFechaRegistro != null) {
                txtFechaRegistro.setText("Fecha de registro: 15/01/2024");
            }

            // *** BOTONES EN EL DETALLE ***
            Button btnEditar = view.findViewById(R.id.detalleBtnEditar);
            Button btnEliminar = view.findViewById(R.id.detalleBtnEliminar);
            Button btnRegresar = view.findViewById(R.id.btnRegresar);

            // BOTÓN EDITAR
            if (btnEditar != null) {
                btnEditar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),
                                "Editando ficha: " + nombre,
                                Toast.LENGTH_SHORT).show();

                        // Aquí puedes abrir un formulario de edición
                        // Por ejemplo: abrirFragmentoEdicion(nombre, descripcion, etc.);
                    }
                });
            }

            // BOTÓN ELIMINAR
            if (btnEliminar != null) {
                btnEliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(),
                                "Eliminando ficha: " + nombre,
                                Toast.LENGTH_SHORT).show();

                        // Aquí puedes mostrar un diálogo de confirmación
                        // mostrarDialogoConfirmacion(nombre);
                    }
                });
            }

            // BOTÓN REGRESAR
            if (btnRegresar != null) {
                btnRegresar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Regresar a la lista
                        requireActivity().getSupportFragmentManager().popBackStack();
                    }
                });
            }

            // Mensaje de confirmación
            Toast.makeText(getContext(), "Detalle de: " + nombre, Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    // Método opcional para abrir formulario de edición
    private void abrirFragmentoEdicion(String nombre, String descripcion,
                                       String proyecto, String equipo,
                                       String estado, String especialidad) {
        // Aquí puedes crear un Fragment para editar
        // Ejemplo: EditarFichaFragment.newInstance(...)
        Toast.makeText(getContext(), "Abrir formulario de edición", Toast.LENGTH_SHORT).show();
    }
}