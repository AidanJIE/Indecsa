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

public class TrabajadorAdapter extends BaseAdapter {

    public interface OnItemClickListener {
        void onItemClick(Trabajador trabajador);
    }

    private Context context;
    private List<Trabajador> trabajadores;
    private final OnItemClickListener listener;

    public TrabajadorAdapter(Context context, List<Trabajador> trabajadores, OnItemClickListener listener) {
        this.context = context;
        this.trabajadores = trabajadores != null ? trabajadores : new ArrayList<Trabajador>();
        this.listener = listener;
    }

    public void actualizarLista(List<Trabajador> nuevaLista) {
        this.trabajadores = nuevaLista != null ? nuevaLista : new ArrayList<Trabajador>();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return trabajadores.size();
    }

    @Override
    public Object getItem(int position) {
        return trabajadores.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_trabajador, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Trabajador trabajador = trabajadores.get(position);
        holder.bind(trabajador, listener);

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

        public void bind(Trabajador trabajador, OnItemClickListener listener) {
            imgContratista.setImageResource(trabajador.getImagenResId());
            txtNombreTrabajador.setText(trabajador.getNombre());
            DescripcionTrabajador.setText("Descripción: " + trabajador.getDescripcion());
            EspecialidadTrabajador.setText("Especialidad: " + trabajador.getEspecialidad());
            EquipoTrabajador.setText("Estado: " + trabajador.getEstado());

            // El click listener se maneja en el getView
        }
    }
}