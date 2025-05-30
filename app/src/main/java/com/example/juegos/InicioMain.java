package com.example.juegos;

import android.content.Intent;
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
    }

    public void verInformacion(View view){
        Intent intent = new Intent(this, InformacionMain.class);
        startActivity(intent);
    }

    public void verCategorias(View view){
        Intent intent = new Intent(this, CategoriasMain.class);
        startActivity(intent);
    }
}
