package com.example.indecsa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

// CAMBIO AQUÍ: Nombre de la clase
public class ProyectoAdapter extends BaseAdapter {

    public interface OnItemClickListener {
        // CAMBIO AQUÍ: Usa la clase Proyecto
        void onItemClick(Proyecto proyecto);
    }

    private Context context;
    // CAMBIO AQUÍ: Usa la clase Proyecto
    private List<Proyecto> proyectos;
    private final OnItemClickListener listener;

    // CAMBIO AQUÍ: Constructor usa List<Proyecto>
    public ProyectoAdapter(Context context, List<Proyecto> proyectos, OnItemClickListener listener) {
        this.context = context;
        this.proyectos = proyectos != null ? proyectos : new ArrayList<Proyecto>();
        this.listener = listener;
    }

    // CAMBIO AQUÍ: Usa la clase Proyecto
    public void actualizarLista(List<Proyecto> nuevaLista) {
        this.proyectos = nuevaLista != null ? nuevaLista : new ArrayList<Proyecto>();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return proyectos.size();
    }

    @Override
    public Object getItem(int position) {
        return proyectos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            // Se asume que el layout item_trabajador ahora se usa para proyectos
            convertView = LayoutInflater.from(context).inflate(R.layout.item_trabajador, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // CAMBIO AQUÍ: Usa la clase Proyecto y la lista de proyectos
        Proyecto proyecto = proyectos.get(position);
        holder.bind(proyecto, listener);

        return convertView;
    }

    public static class ViewHolder {
        private ImageView imgContratista;
        private TextView txtNombreTrabajador, DescripcionTrabajador, EspecialidadTrabajador, EquipoTrabajador;

        public ViewHolder(View itemView) {
            imgContratista = itemView.findViewById(R.id.imgContratista);
            txtNombreTrabajador = itemView.findViewById(R.id.txtNombreTrabajador);
            DescripcionTrabajador = itemView.findViewById(R.id.DescripcionTrabajador);
            EspecialidadTrabajador = itemView.findViewById(R.id.EspecialidadTrabajador);
            EquipoTrabajador = itemView.findViewById(R.id.EquipoTrabajador);
        }

        // CAMBIO AQUÍ: Usa la clase Proyecto y los nuevos Getters
        public void bind(Proyecto proyecto, OnItemClickListener listener) {
            imgContratista.setImageResource(proyecto.getImagenResId());

            // CORRECCIÓN: Usa getProyectito() en lugar de getNombre()
            txtNombreTrabajador.setText(proyecto.getProyectito());

            DescripcionTrabajador.setText("Descripción: " + proyecto.getDescripcion());
            EspecialidadTrabajador.setText("Especialidad: " + proyecto.getEspecialidad());

            // CORRECCIÓN: Usar getDireccion() para mostrar el dato en el campo EquipoTrabajador
            EquipoTrabajador.setText("Dirección: " + proyecto.getDireccion());

            // Aquí se podría añadir el click listener si se hiciera a nivel de fila completa
            // convertView.setOnClickListener(v -> listener.onItemClick(proyecto));
        }
    }
}