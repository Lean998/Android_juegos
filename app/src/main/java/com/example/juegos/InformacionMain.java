package com.example.juegos;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InformacionMain extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.informacion);
        View headerView = findViewById(R.id.main_header);
        TextView tituloHeader = headerView.findViewById(R.id.hd_title);
        tituloHeader.setText(R.string.btnInformacion);
        configurarLogoInicio();
        configurarBtnVolver();
    }



}
