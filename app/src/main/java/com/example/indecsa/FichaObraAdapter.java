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

    public FichaObraAdapter(Context context, String[] nombres, String[] descripcion, int[] imagenes) {
        super(context, R.layout.item_contratista, nombres);
        this.context = context;
        this.nombres = nombres;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View rowView = inflater.inflate(R.layout.item_contratista, null, true);

        TextView nombre = rowView.findViewById(R.id.txtNombreContratista);
        TextView desc = rowView.findViewById(R.id.txtDescripcionContratista);
        ImageView img = rowView.findViewById(R.id.imgContratista);
        Button btnEditar = rowView.findViewById(R.id.btnEditar);

        nombre.setText(nombres[position]);
        desc.setText(descripcion[position]);
        img.setImageResource(imagenes[position]);

        btnEditar.setOnClickListener(v -> {
            // Navegar al fragmento de edición
            if (context instanceof FragmentActivity) {
                FragmentActivity activity = (FragmentActivity) context;

                // Crear el fragmento de edición y pasar los datos
                EditarContratistaFragment editarFragment =
                        EditarContratistaFragment.newInstance(
                                nombres[position],
                                descripcion[position]
                        );

                // Realizar la transacción del fragmento
                FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();

                // Reemplazar el fragmento actual con el de edición
                transaction.replace(R.id.contenedorfragmentos, editarFragment);

                // Agregar a la pila de retroceso para poder volver
                transaction.addToBackStack(null);

                // Ejecutar la transacción
                transaction.commit();
            }
        });

        return rowView;
    }
}