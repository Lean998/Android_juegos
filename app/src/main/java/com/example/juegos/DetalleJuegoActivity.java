package com.example.juegos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.juegos.BaseActivity;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DetalleJuegoActivity extends BaseActivity {

    private TextView textoInfo;
    private Button btnEstadisticas, btnJugar;
    private boolean mostrandoDescripcion = true;
    private String descripcionJuego;
    private String categoria;

    private int idJuego;
    private String nombreJuego;
    private int niveles;
    private ImageView imagenJuego;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_juego);

        configurarLogoInicio();
        configurarBtnVolver();

        textoInfo = findViewById(R.id.textoInfo);
        btnJugar = findViewById(R.id.btnJugar);
        btnEstadisticas = findViewById(R.id.btnEstadisticas);
        imagenJuego = findViewById(R.id.imagenJuego);

        idJuego = getIntent().getIntExtra("idJuego",-1);
        nombreJuego = getIntent().getStringExtra("nombreJuego");
        descripcionJuego = getIntent().getStringExtra("descripcionJuego");
        categoria = getIntent().getStringExtra("categoria");
        niveles= getIntent().getIntExtra("nivelesJuego",-1);
        textoInfo.setText(descripcionJuego);

        if (idJuego == -1) {
            Toast.makeText(this, "Error: no se recibió el id del juego", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        if (nombreJuego == null) {
            Toast.makeText(this, "Error: no se recibió el nombre del juego", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        if (niveles == -1) {
            Toast.makeText(this, "Error: no se recibieron los niveles del juego", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        btnJugar.setOnClickListener(v -> {
            Intent intent = new Intent(DetalleJuegoActivity.this, SimulacionJuegoActivity.class);
            intent.putExtra("nombreJuego", nombreJuego);
            intent.putExtra("idJuego", idJuego);
            intent.putExtra("nivelesJuego",niveles);
            startActivity(intent);
        });
        btnEstadisticas.setOnClickListener(v -> {
            Intent intent = new Intent(DetalleJuegoActivity.this, EstadisticasJuego.class);
            intent.putExtra("idJuego", idJuego);
            startActivity(intent);
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
    }



    public String getCategoria() {
        return categoria;
    }


}
