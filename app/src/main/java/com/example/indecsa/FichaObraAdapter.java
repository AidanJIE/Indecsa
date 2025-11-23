package com.example.indecsa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

public class FichaObraAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] nombres;
    private final String[] descripcion;
    private final int[] imagenes;
    private final int[] calificaciones;

    public FichaObraAdapter(Context context, String[] nombres, String[] descripcion, int[] imagenes, int[] calificaciones) {
        super(context, R.layout.item_contratista, nombres);
        this.context = context;
        this.nombres = nombres;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
        this.calificaciones = calificaciones;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.item_contratista, null, true);

        TextView nombre = rowView.findViewById(R.id.txtNombreContratista);
        TextView desc = rowView.findViewById(R.id.txtDescripcionContratista);
        ImageView img = rowView.findViewById(R.id.imgContratista);

        // Estrellas
        ImageView star1 = rowView.findViewById(R.id.star1);
        ImageView star2 = rowView.findViewById(R.id.star2);
        ImageView star3 = rowView.findViewById(R.id.star3);
        ImageView star4 = rowView.findViewById(R.id.star4);
        ImageView star5 = rowView.findViewById(R.id.star5);

        // Mostrar datos
        nombre.setText(nombres[position]);
        desc.setText(descripcion[position]);
        img.setImageResource(imagenes[position]);

        // Obtener calificación
        int rating = calificaciones[position];

        // Drawable de estrellas
        int llena = R.drawable.ic_star;
        int vacia = R.drawable.estrellavacia;

        // Pintar estrellas según la calificación
        star1.setImageResource(rating >= 1 ? llena : vacia);
        star2.setImageResource(rating >= 2 ? llena : vacia);
        star3.setImageResource(rating >= 3 ? llena : vacia);
        star4.setImageResource(rating >= 4 ? llena : vacia);
        star5.setImageResource(rating >= 5 ? llena : vacia);

        // Botón EDITAR
        Button btnEditar = rowView.findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(v -> {
            if (context instanceof FragmentActivity) {
                FragmentActivity activity = (FragmentActivity) context;

                EditarContratista editarFragment =
                        EditarContratista.newInstance(
                                nombres[position],
                                descripcion[position]
                        );

                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedorfragmentos, editarFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return rowView;
    }
}
