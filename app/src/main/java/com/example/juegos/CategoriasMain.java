package com.example.juegos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

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
        intent.putExtra("nombre_juego", "Juegos de " + categoria);
        intent.putExtra("categoria", categoria );
        startActivity(intent);
    }

}