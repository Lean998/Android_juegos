package com.example.juegos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table juegos (idJuego int primary key, nombre varchar(50) not null, descripcion varchar(255) not null, tipo varchar(100) not null, niveles int not null)");
        db.execSQL("create table puntuaciones (idPuntuacion int primary key , puntuacion float NOT NULL , fechaObtencion datetime  default CURRENT_TIMESTAMP, idJuego int not null, constraint fk_puntuacion_idJuego foreign key (idJuego) references juegos(idjuego))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
