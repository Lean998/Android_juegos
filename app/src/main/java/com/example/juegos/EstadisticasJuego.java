package com.example.juegos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class EstadisticasJuego extends BaseActivity{
    private ArrayList<String> estadisticas = new ArrayList<>();
    private int idJuego;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estadisticasjuego);

        configurarLogoInicio();
        configurarBotonCategorias();
        configurarBtnVolver();
        idJuego = getIntent().getIntExtra("idJuego",-1);
        ListView listaPartidas = findViewById(R.id.listaPartidas);
        estadisticas = obtenerPartidasPorJuego(idJuego); // asumimos que tenés el id del juego

        if (estadisticas.isEmpty()) {
            estadisticas.add("No hay partidas registradas aún.");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, estadisticas);
        listaPartidas.setAdapter(adapter);
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

        SimpleDateFormat sdfUTC = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdfUTC.setTimeZone(TimeZone.getTimeZone("UTC"));

        SimpleDateFormat sdfLocal = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        sdfLocal.setTimeZone(TimeZone.getTimeZone("GMT-3"));

        while (cursor.moveToNext()) {
            String jugador = cursor.getString(0);
            String dificultad = cursor.getString(1);
            String nivel = cursor.getString(2);
            int puntaje = cursor.getInt(3);
            String fecha = cursor.getString(4);
            String fechaFormateada = fecha;
            try {
                Date fechaUTC = sdfUTC.parse(fecha);
                fechaFormateada = sdfLocal.format(fechaUTC);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String resultado = "Jugador: " + jugador +
                    "\nDificultad: " + dificultad +
                    "\n" + nivel +
                    "\nPuntaje: " + puntaje +
                    "\nFecha: " + fechaFormateada;

            listaPartidas.add(resultado);
        }

        cursor.close();

        return listaPartidas;
    }
}
