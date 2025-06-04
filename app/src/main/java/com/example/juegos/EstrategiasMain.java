package com.example.juegos;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class EstrategiasMain extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estrategias_main);
        View headerView = findViewById(R.id.main_header);
        TextView tituloHeader = headerView.findViewById(R.id.hd_title);
        tituloHeader.setText("Estrategia");

        configurarLogoInicio();
        configurarBtnVolver();

        Button btnJuego1, btnJuego2, btnJuego3, btnJuego4;

        btnJuego1 = findViewById(R.id.btnJuego1);
        btnJuego2 = findViewById(R.id.btnJuego2);
        btnJuego3 = findViewById(R.id.btnJuego3);
        btnJuego4 = findViewById(R.id.btnJuego4);

        btnJuego1.setOnClickListener(v -> abrirJuego(0,"Juego1"));
        btnJuego2.setOnClickListener(v -> abrirJuego(1,"Juego2"));
        btnJuego3.setOnClickListener(v -> abrirJuego(2,"Juego3"));
        btnJuego4.setOnClickListener(v -> abrirJuego(3,"Juego4"));
    }
    private void abrirJuego(int idJuego, String nombreJuego) {
        Intent intent = new Intent(this, com.example.juegos.DetalleJuegoActivity.class);
        intent.putExtra("idJuego",idJuego);
        intent.putExtra("nombreJuego", nombreJuego);
        intent.putExtra("categoria", "Estrategia");
        startActivity(intent);
    }
}

