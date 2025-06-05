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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

        int niveles= getIntent().getIntExtra("nivelesJuego",-1);
        if(niveles!=-1){
            List<String> listNiveles=new ArrayList<>();
            for (int i=0;i<niveles;i++){
                listNiveles.add("Nivel "+(i+1));
            }
            ArrayAdapter<String> adapterNivel = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,listNiveles);
            adapterNivel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerNivel.setAdapter(adapterNivel);
        }

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
        int idJuego = getIntent().getIntExtra("idJuego",-1);
        ArrayList<String> estadisticas = getIntent().getStringArrayListExtra("estadisticas");

        if(idJuego == -1){
            Toast.makeText(this, "Error inesperado al finalizar la partida", Toast.LENGTH_SHORT).show();
            return;
        }

        int puntaje;
        try {
            puntaje = Integer.parseInt(puntajeTexto);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "El puntaje debe ser un número válido", Toast.LENGTH_SHORT).show();
            return;
        }

        guardarPartida(nombre, idJuego, complejidad, nivel, puntaje);

        String resumen = "Jugador: " + nombre +
                "\nDificultad: " + complejidad +
                "\n" + nivel +
                "\nPuntaje: " + puntaje +
                "\nFecha: " + obtenerFechaActual();

        try{
            estadisticas.add(0,resumen);
        }catch(NullPointerException e){}

        try {
            estadisticas.remove(10);
        }catch(IndexOutOfBoundsException e){}


        Intent intent = new Intent(this, DetalleJuegoActivity.class);
        intent.putExtra("idJuego", idJuego);
        intent.putExtra("estadisticas", estadisticas);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

    public void guardarPartida(String nombreJugador, int idJuego, String dificultad, String nivel, int puntos) {

        DBPartidaHelper dbHelper = new DBPartidaHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBPartidaHelper.COLUMN_JUGADOR, nombreJugador);
        values.put(DBPartidaHelper.COLUMN_IDJUEGO, idJuego);
        values.put(DBPartidaHelper.COLUMN_DIFICULTAD, dificultad);
        values.put(DBPartidaHelper.COLUMN_NIVEL, nivel);
        values.put(DBPartidaHelper.COLUMN_PUNTAJE, puntos);

        long resultado = db.insert(DBPartidaHelper.TABLE_PARTIDAS, null, values);

        if (resultado != -1) {
            Toast.makeText(this, "Partida guardada correctamente", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al guardar la partida", Toast.LENGTH_SHORT).show();
        }

    }

    private String obtenerFechaActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

}
