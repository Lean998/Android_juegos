package com.example.juegos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoriaMain extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String categoria = intent.getStringExtra("categoria");
        setContentView(R.layout.categoria_main);
        View headerView = findViewById(R.id.main_header);
        TextView tituloHeader = headerView.findViewById(R.id.hd_title);
        tituloHeader.setText(categoria);

        configurarLogoInicio();
        configurarBtnVolver();

        List<JuegoClass> listaJuegos=new ArrayList<>();

        RecyclerView recyclerJuegos = findViewById(R.id.recyclerJuegos);
        recyclerJuegos.setLayoutManager(new LinearLayoutManager(this));

        DBPartidaHelper dbHelper = new DBPartidaHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Log.d("CategoriaMain", "CATEGORIA RECIBIDA: " + categoria);
        Cursor cursor = db.rawQuery("SELECT id, nombre, descripcion, niveles FROM juegos WHERE genero = ?", new String[]{categoria});
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String descripcion = cursor.getString(2);
            int niveles = cursor.getInt(3);
            listaJuegos.add(new JuegoClass(id, nombre, descripcion, "", niveles,v -> abrirJuego(id,nombre,descripcion,categoria,niveles)));
        }
        cursor.close();

        btnJuegoAdapter adapter = new btnJuegoAdapter(listaJuegos);
        recyclerJuegos.setAdapter(adapter);


    }
    private void abrirJuego(int idJuego, String nombreJuego, String descripcionJuego, String categoriaJuego , int nivelesJuego) {
        Intent intent = new Intent(this, com.example.juegos.DetalleJuegoActivity.class);
        intent.putExtra("idJuego",idJuego);
        intent.putExtra("nombreJuego", nombreJuego);
        intent.putExtra("descripcionJuego", descripcionJuego);
        intent.putExtra("nivelesJuego", nivelesJuego);
        intent.putExtra("categoria", categoriaJuego);
        startActivity(intent);
    }
}

