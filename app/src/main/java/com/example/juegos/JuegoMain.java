package com.example.juegos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class JuegoMain extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);
        View headerView = findViewById(R.id.main_header);
        TextView tituloHeader = headerView.findViewById(R.id.hd_title);
        Intent intent = getIntent();
        String titulo="Juegos de "+ String.valueOf(intent.getIntExtra("ID_JUEGO",4))+" A";
        tituloHeader.setText(titulo);
        configurarLogoInicio();
        configurarBtnVolver();
    }

}
/*
public void registrar (View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"Juegos",null,1);
        SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
        String idJuego=juego_idJuego.getText().toString();
        if(6==4){
            ContentValues registro = new ContentValues();
            registro.put("idJuego",idJuego);
            baseDeDatos.insert("Juegos",null,registro);
            baseDeDatos.close();
            juego_idJuego.setText("");
            Toast.makeText(this,"Datos Guardados",Toast.LENGTH_SHORT).show();
        }

    }
    public void precargar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"Juego",null,1);
        SQLiteDatabase baseDeDatos = admin.getReadableDatabase();
        Cursor fila = baseDeDatos.rawQuery("select * from Juegos",null);
        if(fila.moveToFirst()){
            juego_idJuego.setText(fila.getString(1));
        }
        baseDeDatos.close();
    }
    */