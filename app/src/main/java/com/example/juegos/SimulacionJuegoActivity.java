package com.example.juegos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SimulacionJuegoActivity extends AppCompatActivity{


    EditText etNombre, etPuntaje;
    Spinner spinnerComplejidad, spinnerNivel;
    Button btnFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulacion_juego);

        etNombre = findViewById(R.id.etNombre);
        etPuntaje = findViewById(R.id.etPuntaje);
        spinnerComplejidad = findViewById(R.id.spinnerComplejidad);
        spinnerNivel = findViewById(R.id.spinnerNivel);
        btnFinalizar = findViewById(R.id.btnFinalizar);

        ArrayAdapter<String> adapterComplejidad = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"Novato", "Amateur", "Profesional"});
        adapterComplejidad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerComplejidad.setAdapter(adapterComplejidad);

        ArrayAdapter<String> adapterNivel = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"Nivel 1", "Nivel 2", "Nivel 3", "Nivel 4", "Nivel 5", "Nivel 6", "Nivel 7", "Nivel 8", "Nivel 9", "Nivel 10"});
        adapterNivel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNivel.setAdapter(adapterNivel);

        btnFinalizar.setOnClickListener(v -> finalizarPartida());
    }

    private void finalizarPartida() {
        String nombre = etNombre.getText().toString().trim();
        String puntajeTexto = etPuntaje.getText().toString().trim();

        if (nombre.isEmpty() || puntajeTexto.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        String complejidad = spinnerComplejidad.getSelectedItem().toString();
        String nivel = spinnerNivel.getSelectedItem().toString();
        String nombreJuego = getIntent().getStringExtra("nombreJuego");

        int puntaje;
        try {
            puntaje = Integer.parseInt(puntajeTexto);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "El puntaje debe ser un número válido", Toast.LENGTH_SHORT).show();
            return;
        }

        guardarPartida(nombre, nombreJuego, complejidad, nivel, puntaje);

        String resumen = "Jugador: " + nombre +
                "\nJuego: " + nombreJuego +
                "\nComplejidad: " + complejidad +
                "\n" + nivel +
                "\nPuntaje: " + puntaje;

        Intent intent = new Intent(this, DetalleJuegoActivity.class);
        intent.putExtra("nombreJuego", nombreJuego);
        intent.putExtra("nuevaEstadistica", resumen);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    public void guardarPartida(String nombreJugador, String juego, String dificultad, String nivel, int puntos) {
        String fechaActual = obtenerFechaActual();

        DBPartidaHelper dbHelper = new DBPartidaHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBPartidaHelper.COLUMN_JUGADOR, nombreJugador);
        values.put(DBPartidaHelper.COLUMN_JUEGO, juego);
        values.put(DBPartidaHelper.COLUMN_DIFICULTAD, dificultad);
        values.put(DBPartidaHelper.COLUMN_NIVEL, nivel);
        values.put(DBPartidaHelper.COLUMN_PUNTAJE, puntos);
        values.put(DBPartidaHelper.COLUMN_FECHA, fechaActual);

        long resultado = db.insert(DBPartidaHelper.TABLE_PARTIDAS, null, values);

        if (resultado != -1) {
            Toast.makeText(this, "Partida guardada correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al guardar la partida", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }


    private String obtenerFechaActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

}
