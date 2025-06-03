package com.example.juegos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DeportesMain extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deportes);
        View headerView = findViewById(R.id.main_header);
        TextView tituloHeader = headerView.findViewById(R.id.hd_title);
        tituloHeader.setText("Deportes");

        configurarLogoInicio();
        configurarBtnVolver();
    }
    public void verJuego(View view){
        Intent intent = new Intent(this, JuegoMain.class);
        View headerView = findViewById(R.id.main_header);
        TextView tituloHeader = headerView.findViewById(R.id.hd_title);
        intent.putExtra("TIPO_JUEGO",tituloHeader.getText());
        intent.putExtra("ID_JUEGO",view.getId());
        startActivity(intent);
    }
}
