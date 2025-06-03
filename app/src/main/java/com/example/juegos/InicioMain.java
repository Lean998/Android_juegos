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
<<<<<<< HEAD
        configurarLogoInicio();
        configurarBtnVolver();

        DBPartidaHelper dbHelper = new DBPartidaHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

=======
>>>>>>> d4733a17a6452a473f2cd928939e940317701642
    }

    public void verInformacion(View view){
        Intent intent = new Intent(this, InformacionMain.class);
        startActivity(intent);
    }

    public void verCategorias(View view){
        Intent intent = new Intent(this, CategoriasMain.class);
        startActivity(intent);
    }

    public void verJuego(View view){
        Intent intent = new Intent(this, Juego1.class);
        startActivity(intent);
    }
}
