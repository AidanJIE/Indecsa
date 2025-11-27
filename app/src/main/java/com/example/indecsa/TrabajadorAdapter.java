package com.example.indecsa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TrabajadorAdapter extends RecyclerView.Adapter<TrabajadorAdapter.TrabajadorViewHolder> {

    private List<Trabajador> trabajadores;
    private OnTrabajadorClickListener listener;

    public interface OnTrabajadorClickListener {
        void onEditarClick(Trabajador trabajador);
        void onDisponibilidadClick(Trabajador trabajador);
    }

    public TrabajadorAdapter(List<Trabajador> trabajadores, OnTrabajadorClickListener listener) {
        this.trabajadores = trabajadores;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TrabajadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_trabajador, parent, false);
        return new TrabajadorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrabajadorViewHolder holder, int position) {
        Trabajador trabajador = trabajadores.get(position);
        holder.bind(trabajador);

        // Configurar clicks de botones
        holder.btnEditar.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditarClick(trabajador);
            }
        });

        holder.btnDisponibilidad.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDisponibilidadClick(trabajador);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trabajadores.size();
    }

    public static class TrabajadorViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgContratista;
        private TextView txtNombreTrabajador, DescripcionTrabajador, EspecialidadTrabajador, EquipoTrabajador, ExperienciaTrabajador;
        private EditText editext;
        private ImageView star1, star2, star3, star4, star5;
        private Button btnDisponibilidad, btnEditar;

        public TrabajadorViewHolder(@NonNull View itemView) {
            super(itemView);

            // Inicializar vistas
            imgContratista = itemView.findViewById(R.id.imgContratista);
            txtNombreTrabajador = itemView.findViewById(R.id.txtNombreTrabajador);
            DescripcionTrabajador = itemView.findViewById(R.id.DescripcionTrabajador);
            EspecialidadTrabajador = itemView.findViewById(R.id.EspecialidadTrabajador);
            EquipoTrabajador = itemView.findViewById(R.id.EquipoTrabajador);
            ExperienciaTrabajador = itemView.findViewById(R.id.ExperienciaTrabajador);
            editext = itemView.findViewById(R.id.editext);

            // Estrellas
            star1 = itemView.findViewById(R.id.star1);
            star2 = itemView.findViewById(R.id.star2);
            star3 = itemView.findViewById(R.id.star3);
            star4 = itemView.findViewById(R.id.star4);
            star5 = itemView.findViewById(R.id.star5);

            // Botones
            btnDisponibilidad = itemView.findViewById(R.id.btnDisponibilidad);
            btnEditar = itemView.findViewById(R.id.btnEditar);
        }

        public void bind(Trabajador trabajador) {
            // Configurar imagen
            imgContratista.setImageResource(trabajador.getImagenResId());

            // Configurar textos
            txtNombreTrabajador.setText(trabajador.getNombre());
            DescripcionTrabajador.setText("Descripción: " + trabajador.getDescripcion());
            EspecialidadTrabajador.setText("Especialidad: " + trabajador.getEspecialidad());
            EquipoTrabajador.setText("Equipo: " + trabajador.getEquipo());
            ExperienciaTrabajador.setText("Experiencia");

            // Configurar estrellas según la experiencia
            //configurarEstrellas(trabajador.getExperiencia());

            // Configurar texto del botón de disponibilidad
            btnDisponibilidad.setText(trabajador.getDisponibilidad());
        }

        /*private void configurarEstrellas(int experiencia) {
            // Resetear todas las estrellas
            int starEmpty = R.drawable.ic_star_empty; // Debes tener este drawable
            int starFilled = R.drawable.ic_star; // Tu drawable de estrella llena

            star1.setImageResource(experiencia >= 1 ? starFilled : starEmpty);
            star2.setImageResource(experiencia >= 2 ? starFilled : starEmpty);
            star3.setImageResource(experiencia >= 3 ? starFilled : starEmpty);
            star4.setImageResource(experiencia >= 4 ? starFilled : starEmpty);
            star5.setImageResource(experiencia >= 5 ? starFilled : starEmpty);
        }*/
    }
}