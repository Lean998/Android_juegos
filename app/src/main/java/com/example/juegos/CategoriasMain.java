package com.example.juegos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

    public void  verEstrategia(View view){
        Intent intent = new Intent(this, EstrategiasMain.class);
        intent.putExtra("nombre_juego", "Juegos de Estrategia");
        startActivity(intent);
    }

    public void  verRol(View view){
        Intent intent = new Intent(this, RolMain.class);
        intent.putExtra("nombre_juego", "Juegos de Rol");
        startActivity(intent);
    }

    public void  verDeportes(View view){
        Intent intent = new Intent(this, DeportesMain.class);
        intent.putExtra("nombre_juego", "Juegos de Deporte");
        startActivity(intent);
    }

    public void  verDisparos(View view){
        Intent intent = new Intent(this, DisparosMain.class);
        intent.putExtra("nombre_juego", "Juegos de Disparos");
        startActivity(intent);
    }

    public void  verSimulacion(View view){
        Intent intent = new Intent(this, SimulacionMain.class);
        intent.putExtra("nombre_juego", "Juegos de Simulacion");
        startActivity(intent);
    }

    public void  verAccion(View view){
        Intent intent = new Intent(this, AccionMain.class);
        intent.putExtra("nombre_juego", "Juegos de Accion");
        startActivity(intent);
    }

}