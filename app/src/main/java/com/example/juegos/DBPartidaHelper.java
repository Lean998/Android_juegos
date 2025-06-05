package com.example.juegos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBPartidaHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "juegos.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PARTIDAS = "partidas";
    public static final String TABLE_JUEGOS = "juegos";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_JUGADOR = "jugador";
    public static final String COLUMN_IDJUEGO = "idJuego";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_DESCRIPCION = "descripcion";
    public static final String COLUMN_GENERO = "genero";
    public static final String COLUMN_DIFICULTAD = "dificultad";
    public static final String COLUMN_NIVELES = "niveles";
    public static final String COLUMN_NIVEL = "nivel";
    public static final String COLUMN_PUNTAJE = "puntaje";
    public static final String COLUMN_FECHA = "fecha";

    private static final String TABLE_CREATE_JUEGOS =
            "CREATE TABLE " + TABLE_JUEGOS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOMBRE + " VARCHAR(50) NOT NULL, " +
                    COLUMN_DESCRIPCION + " VARCHAR(100) NOT NULL, " +
                    COLUMN_GENERO + " VARCHAR(50) NOT NULL, " +
                    COLUMN_NIVELES + " INTEGER NOT NULL " +
                    ");";

    private static final String TABLE_FILL_JUEGOS =
            "INSERT INTO " + TABLE_JUEGOS + " (" +
                    COLUMN_NOMBRE + ", " +
                    COLUMN_DESCRIPCION + ", " +
                    COLUMN_GENERO + ", " +
                    COLUMN_NIVELES +
                    ") VALUES " +
                    "('Estrategia1', 'Descripcion Estrategia1', 'Estrategia', 10), " +
                    "('Estrategia2', 'Descripcion Estrategia2', 'Estrategia', 7)," +
                    "('Estrategia3', 'Descripcion Estrategia3', 'Estrategia', 5)," +
                    "('Estrategia4', 'Descripcion Estrategia4', 'Estrategia', 2)";
    private static final String TABLE_CREATE_PARTIDAS =
            "CREATE TABLE " + TABLE_PARTIDAS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_JUGADOR + " VARCHAR(50) NOT NULL, " +
                    COLUMN_IDJUEGO + " INTEGER NOT NULL, " +
                    COLUMN_DIFICULTAD + " VARCHAR(50), " +
                    COLUMN_NIVEL + " INTEGER NOT NULL, " +
                    COLUMN_PUNTAJE + " INTEGER NOT NULL, " +
                    COLUMN_FECHA + " DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    "CONSTRAINT fk_partidas_idJuego FOREIGN KEY (" + COLUMN_IDJUEGO + " ) REFERENCES juegos(" + COLUMN_ID + ")" +
                    ");";

    public DBPartidaHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_JUEGOS);
        db.execSQL(TABLE_FILL_JUEGOS);
        db.execSQL(TABLE_CREATE_PARTIDAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_JUEGOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PARTIDAS);
        onCreate(db);
    }
}
