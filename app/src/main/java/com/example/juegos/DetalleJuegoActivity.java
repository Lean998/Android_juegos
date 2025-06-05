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

public class DetalleJuegoActivity extends BaseActivity {

    private TextView textoInfo;
    private Button btnAlternar, btnJugar;
    private boolean mostrandoDescripcion = true;
    private String descripcionJuego;
    private String categoria;
    private ArrayList<String> estadisticas = new ArrayList<>();
    private int idJuego;
    private String nombreJuego;
    private int niveles;
    private ImageView imagenJuego;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_juego);

        configurarLogoInicio();
        configurarBtnVolver();

        textoInfo = findViewById(R.id.textoInfo);
        btnJugar = findViewById(R.id.btnJugar);
        imagenJuego = findViewById(R.id.imagenJuego);

        idJuego = getIntent().getIntExtra("idJuego",-1);
        nombreJuego = getIntent().getStringExtra("nombreJuego");
        descripcionJuego = getIntent().getStringExtra("descripcionJuego");
        categoria = getIntent().getStringExtra("categoria");
        niveles= getIntent().getIntExtra("nivelesJuego",-1);
        textoInfo.setText(descripcionJuego);

        if (idJuego == -1) {
            Toast.makeText(this, "Error: no se recibió el id del juego", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        if (nombreJuego == null) {
            Toast.makeText(this, "Error: no se recibió el nombre del juego", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        if (niveles == -1) {
            Toast.makeText(this, "Error: no se recibieron los niveles del juego", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        btnJugar.setOnClickListener(v -> {
            Intent intent = new Intent(DetalleJuegoActivity.this, SimulacionJuegoActivity.class);
            intent.putExtra("nombreJuego", nombreJuego);
            intent.putExtra("idJuego", idJuego);
            intent.putExtra("nivelesJuego",niveles);
            intent.putStringArrayListExtra("estadisticas", estadisticas);
            startActivity(intent);
        });

        ListView listaPartidas = findViewById(R.id.listaPartidas);
        estadisticas = obtenerPartidasPorJuego(idJuego); // asumimos que tenés el id del juego

        if (estadisticas.isEmpty()) {
            estadisticas.add("No hay partidas registradas aún.");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, estadisticas);

        listaPartidas.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<String> nuevaEstadisticas = getIntent().getStringArrayListExtra("estadisticas");
        if (nuevaEstadisticas != null && !estadisticas.equals(nuevaEstadisticas)) {
            estadisticas = nuevaEstadisticas;
            ListView listaPartidas = findViewById(R.id.listaPartidas);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, estadisticas);
            listaPartidas.setAdapter(adapter);
        }
    }

    public ArrayList<String> obtenerPartidasPorJuego(int idJuego) {
        ArrayList<String> listaPartidas = new ArrayList<>();

        DBPartidaHelper dbHelper = new DBPartidaHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] columnas = {
                DBPartidaHelper.COLUMN_JUGADOR,
                DBPartidaHelper.COLUMN_DIFICULTAD,
                DBPartidaHelper.COLUMN_NIVEL,
                DBPartidaHelper.COLUMN_PUNTAJE,
                DBPartidaHelper.COLUMN_FECHA
        };

        String seleccion = DBPartidaHelper.COLUMN_IDJUEGO + " = " + idJuego;

        Cursor cursor = db.query(
                DBPartidaHelper.TABLE_PARTIDAS,
                columnas,
                seleccion,
                null,
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
                    "\n" + nivel +
                    "\nPuntaje: " + puntaje +
                    "\nFecha: " + fecha;

            listaPartidas.add(resultado);
        }

        cursor.close();

        return listaPartidas;
    }

    public String getCategoria() {
        return categoria;
    }


}
