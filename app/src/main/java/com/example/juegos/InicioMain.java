package com.example.juegos;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;

public class InicioMain extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_main);

        configurarLogoInicio();
        configurarBotonCategorias();
        configurarBtnVolver();

        ImageView fondoInicio = findViewById(R.id.fondoInicio);
        try {
            AssetManager assetManager = getAssets();
            InputStream inputStream = assetManager.open("FondoInicio.png");
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            fondoInicio.setImageDrawable(drawable);
        } catch (IOException e) {

        }

        DBPartidaHelper dbHelper = new DBPartidaHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


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
        Intent intent = new Intent(this, CategoriasMain.class);
        startActivity(intent);
    }
}
