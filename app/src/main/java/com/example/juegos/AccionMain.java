package com.example.juegos;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AccionMain extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accion);
        View headerView = findViewById(R.id.main_header);
        TextView tituloHeader = headerView.findViewById(R.id.hd_title);
        tituloHeader.setText("Accion");
        configurarLogoInicio();
        configurarBtnVolver();
    }
}
