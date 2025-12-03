package com.example.indecsa;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Calendar;

public class CarteraActivity extends AppCompatActivity {
    private EditText edtProyecto;
    private RatingBar ratingExperiencia;
    private Button btnDisponible, btnFechaInicio, btnFechaFin, btnGuardar;
    private ListView listEquipo;
    private boolean disponible = true;
    private ArrayList<String> equipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cartera_contratistas);

        edtProyecto = findViewById(R.id.edtProyecto);
        ratingExperiencia = findViewById(R.id.ratingExperiencia);
        btnDisponible = findViewById(R.id.btnDisponible);
        btnFechaInicio = findViewById(R.id.btnFechaInicio);
        btnFechaFin = findViewById(R.id.btnFechaFin);
        btnGuardar = findViewById(R.id.btnGuardar);
        listEquipo = findViewById(R.id.listEquipo);

        // Equipo de trabajo (5 trabajadores con nombre, NSS y especialidad)
        equipo = new ArrayList<>();
        equipo.add("Juan Pérez | NSS: 12345 | Albañilería");
        equipo.add("María López | NSS: 67890 | Electricidad");
        equipo.add("Carlos Sánchez | NSS: 54321 | Plomería");
        equipo.add("Ana Torres | NSS: 98765 | Pintura");
        equipo.add("Luis Ramírez | NSS: 11223 | Carpintería");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                equipo
        );
        listEquipo.setAdapter(adapter);

        // Cargar datos guardados al entrar
        cargarCambios();

        // Botón disponible con colores
        btnDisponible.setOnClickListener(v -> {
            disponible = !disponible;
            actualizarBotonDisponible();
        });

        // Fechas
        btnFechaInicio.setOnClickListener(v -> mostrarCalendario(btnFechaInicio));
        btnFechaFin.setOnClickListener(v -> mostrarCalendario(btnFechaFin));

        // Botón guardar cambios
        btnGuardar.setOnClickListener(v -> guardarCambios());
    }

    private void actualizarBotonDisponible() {
        if (disponible) {
            btnDisponible.setText("Disponible");
            btnDisponible.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        } else {
            btnDisponible.setText("No disponible");
            btnDisponible.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    private void mostrarCalendario(Button boton) {
        final Calendar calendario = Calendar.getInstance();
        int año = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    String fechaSeleccionada = dayOfMonth + "/" + (month + 1) + "/" + year;
                    boton.setText(fechaSeleccionada);
                },
                año, mes, dia
        );
        datePickerDialog.show();
    }

    // Guardar cambios en SharedPreferences
    private void guardarCambios() {
        SharedPreferences prefs = getSharedPreferences("CarteraPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("proyecto", edtProyecto.getText().toString());
        editor.putFloat("experiencia", ratingExperiencia.getRating());
        editor.putBoolean("disponible", disponible);
        editor.putString("fechaInicio", btnFechaInicio.getText().toString());
        editor.putString("fechaFin", btnFechaFin.getText().toString());

        // Guardar equipo como texto concatenado
        StringBuilder equipoTexto = new StringBuilder();
        for (String trabajador : equipo) {
            equipoTexto.append(trabajador).append(";");
        }
        editor.putString("equipo", equipoTexto.toString());

        editor.apply(); // Guarda los cambios

        Toast.makeText(this, "SE GUARDARON LOS CAMBIOS", Toast.LENGTH_LONG).show();
    }

    // Cargar cambios al entrar
    private void cargarCambios() {
        SharedPreferences prefs = getSharedPreferences("CarteraPrefs", MODE_PRIVATE);

        edtProyecto.setText(prefs.getString("proyecto", ""));
        ratingExperiencia.setRating(prefs.getFloat("experiencia", 0f));
        disponible = prefs.getBoolean("disponible", true);
        actualizarBotonDisponible();

        btnFechaInicio.setText(prefs.getString("fechaInicio", "Seleccionar Fecha de Inicio"));
        btnFechaFin.setText(prefs.getString("fechaFin", "Seleccionar Fecha de Fin"));

        // Cargar equipo si se guardó
        String equipoTexto = prefs.getString("equipo", "");
        if (!equipoTexto.isEmpty()) {
            String[] trabajadores = equipoTexto.split(";");
            equipo.clear();
            for (String t : trabajadores) {
                if (!t.trim().isEmpty()) {
                    equipo.add(t.trim());
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    equipo
            );
            listEquipo.setAdapter(adapter);
        }
    }
}