package com.example.indecsa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class EstadoActivity extends AppCompatActivity {
    private ImageButton btnHidalgo, btnCDMX, btnPuebla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fichas_estado);

        btnHidalgo = findViewById(R.id.btnHidalgo);
        btnCDMX = findViewById(R.id.btnCDMX);
        btnPuebla = findViewById(R.id.btnPuebla);

        btnHidalgo.setOnClickListener(v -> abrirEspecialidad("Hidalgo"));
        btnCDMX.setOnClickListener(v -> abrirEspecialidad("CDMX"));
        btnPuebla.setOnClickListener(v -> abrirEspecialidad("Puebla"));
    }

    private void abrirEspecialidad(String estado) {
        Intent intent = new Intent(this, EspecialidadActivity.class);
        intent.putExtra("estado", estado);
        startActivity(intent);
    }
}