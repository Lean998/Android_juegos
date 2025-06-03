package com.example.juegos;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InicioMain extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_main);
        configurarLogoInicio();
        configurarBtnVolver();

        DBPartidaHelper dbHelper = new DBPartidaHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

    }

    public void  verCategorias(View view){
        Intent intent = new Intent(this, CategoriasMain.class);
        startActivity(intent);
    }

    public void verJuego(View view){
        Intent intent = new Intent(this, Juego1.class);
        startActivity(intent);
    }
}
