package com.example.juegos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoriasMain extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categorias_main);
        TextView tituloHeader = findViewById(R.id.hd_title);
        tituloHeader.setText("Categorias");
        configurarLogoInicio();
        configurarBtnVolver();
    }

    public void  verCategoria(View view){
        String categoria = (String) view.getTag();
        Intent intent = new Intent(this, CategoriaMain.class);
        intent.putExtra("categoria", categoria );
        startActivity(intent);
    }
}