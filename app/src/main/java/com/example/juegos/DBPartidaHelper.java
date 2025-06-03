package com.example.juegos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBPartidaHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "juegos.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PARTIDAS = "partidas";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_JUGADOR = "jugador";
    public static final String COLUMN_JUEGO = "juego";
    public static final String COLUMN_DIFICULTAD = "dificultad";
    public static final String COLUMN_NIVEL = "nivel";
    public static final String COLUMN_PUNTAJE = "puntaje";
    public static final String COLUMN_FECHA = "fecha";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_PARTIDAS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_JUGADOR + " TEXT, " +
                    COLUMN_JUEGO + " TEXT, " +
                    COLUMN_DIFICULTAD + " TEXT, " +
                    COLUMN_NIVEL + " TEXT, " +
                    COLUMN_PUNTAJE + " INTEGER, " +
                    COLUMN_FECHA + " TEXT" +
                    ");";

    public DBPartidaHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTIDAS);
        onCreate(db);
    }
}
