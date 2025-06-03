package com.example.juegos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juegos.BaseActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DetalleJuegoActivity extends AppCompatActivity {

    private TextView textoInfo;
    private Button btnAlternar, btnJugar;
    private boolean mostrandoDescripcion = true;
    private String descripcion = "Este es un gran juego de estrategia...";
    private String categoria;
    private List<String> estadisticas = new ArrayList<>();
    private String nombreJuego;
    private ImageView imagenJuego;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_juego);

        textoInfo = findViewById(R.id.textoInfo);
        btnJugar = findViewById(R.id.btnJugar);
        imagenJuego = findViewById(R.id.imagenJuego);

        nombreJuego = getIntent().getStringExtra("nombreJuego");
        categoria = getIntent().getStringExtra("categoria");

        if (nombreJuego == null) {
            Toast.makeText(this, "Error: no se recibió el nombre del juego", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        btnJugar.setOnClickListener(v -> {
            Intent intent = new Intent(DetalleJuegoActivity.this, SimulacionJuegoActivity.class);
            intent.putExtra("nombreJuego", nombreJuego);
            startActivity(intent);
        });

        ListView listaPartidas = findViewById(R.id.listaPartidas);
        List<String> partidas = obtenerPartidasPorJuego(nombreJuego); // asumimos que tenés el nombre del juego

        if (partidas.isEmpty()) {
            partidas.add("No hay partidas registradas aún.");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, partidas);

        listaPartidas.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String nuevaEstadistica = getIntent().getStringExtra("nuevaEstadistica");
        if (nuevaEstadistica != null && !estadisticas.contains(nuevaEstadistica)) {
            estadisticas.add(0, nuevaEstadistica);
            if (estadisticas.size() > 10) estadisticas.remove(10);
        }
    }

    public List<String> obtenerPartidasPorJuego(String nombreJuego) {
        List<String> listaPartidas = new ArrayList<>();

        DBPartidaHelper dbHelper = new DBPartidaHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columnas = {
                DBPartidaHelper.COLUMN_JUGADOR,
                DBPartidaHelper.COLUMN_DIFICULTAD,
                DBPartidaHelper.COLUMN_NIVEL,
                DBPartidaHelper.COLUMN_PUNTAJE,
                DBPartidaHelper.COLUMN_FECHA
        };

        String seleccion = DBPartidaHelper.COLUMN_JUEGO + " = ?";
        String[] argumentos = { nombreJuego };

        Cursor cursor = db.query(
                DBPartidaHelper.TABLE_PARTIDAS,
                columnas,
                seleccion,
                argumentos,
                null,
                null,
                DBPartidaHelper.COLUMN_FECHA + " DESC",
                "10" // <--- Límite de 10 resultados
        );

        while (cursor.moveToNext()) {
            String jugador = cursor.getString(0);
            String dificultad = cursor.getString(1);
            String nivel = cursor.getString(2);
            int puntaje = cursor.getInt(3);
            String fecha = cursor.getString(4);

            String resultado = "Jugador: " + jugador +
                    "\nDificultad: " + dificultad +
                    "\nNivel: " + nivel +
                    "\nPuntaje: " + puntaje +
                    "\nFecha: " + fecha;

            listaPartidas.add(resultado);
        }

        cursor.close();
        db.close();

        return listaPartidas;
    }

    public String getCategoria() {
        return categoria;
    }


}
