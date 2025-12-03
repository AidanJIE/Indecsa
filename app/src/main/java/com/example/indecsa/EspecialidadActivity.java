package com.example.indecsa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class EspecialidadActivity extends AppCompatActivity {

    private Button btnAlbanileria, btnElectricidad, btnPintura, btnPlomeria, btnCarpinteria;
    private TextView txtTituloEspecialidad;
    private String estadoSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.especialidad);

        // Recibir estado desde EstadoActivity
        estadoSeleccionado = getIntent().getStringExtra("estado");

        txtTituloEspecialidad = findViewById(R.id.txtTituloEspecialidad);
        if (estadoSeleccionado != null) {
            txtTituloEspecialidad.setText("Especialidades para " + estadoSeleccionado);
        }

        // Referencias a botones
        btnAlbanileria = findViewById(R.id.btnAlbanileria);
        btnElectricidad = findViewById(R.id.btnElectricidad);
        btnPintura = findViewById(R.id.btnPintura);
        btnPlomeria = findViewById(R.id.btnPlomeria);
        btnCarpinteria = findViewById(R.id.btnCarpinteria);

        // Listeners
        btnAlbanileria.setOnClickListener(v -> abrirCartera("Albañilería"));
        btnElectricidad.setOnClickListener(v -> abrirCartera("Electricidad"));
        btnPintura.setOnClickListener(v -> abrirCartera("Pintura"));
        btnPlomeria.setOnClickListener(v -> abrirCartera("Plomería"));
        btnCarpinteria.setOnClickListener(v -> abrirCartera("Carpintería"));
    }

    private void abrirCartera(String especialidad) {
        Intent intent = new Intent(this, CarteraActivity.class);
        intent.putExtra("estado", estadoSeleccionado);
        intent.putExtra("especialidad", especialidad);
        startActivity(intent);
    }
}