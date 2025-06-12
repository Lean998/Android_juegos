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
                    "('Estrategia4', 'Descripcion Estrategia4', 'Estrategia', 10)," +
                    "('Estrategia5', 'Descripcion Estrategia5', 'Estrategia', 10)," +
                    "('Estrategia6', 'Descripcion Estrategia6', 'Estrategia', 10)," +
                    "('Estrategia7', 'Descripcion Estrategia7', 'Estrategia', 10)," +
                    "('Estrategia8', 'Descripcion Estrategia8', 'Estrategia', 10)," +
                    "('Estrategia9', 'Descripcion Estrategia9', 'Estrategia', 10)," +

                    "('Disparos1', 'Descripcion Disparos1', 'Disparos', 10)," +
                    "('Disparos2', 'Descripcion Disparos2', 'Disparos', 10)," +
                    "('Disparos3', 'Descripcion Disparos3', 'Disparos', 10)," +
                    "('Disparos4', 'Descripcion Disparos4', 'Disparos', 10)," +
                    "('Disparos5', 'Descripcion Disparos5', 'Disparos', 10)," +
                    "('Disparos6', 'Descripcion Disparos6', 'Disparos', 10)," +

                    "('Acción1', 'Descripcion Acción1', 'Acción', 10)," +
                    "('Acción2', 'Descripcion Acción2', 'Acción', 10)," +
                    "('Acción3', 'Descripcion Acción3', 'Acción', 10)," +
                    "('Acción4', 'Descripcion Acción4', 'Acción', 10)," +
                    "('Acción5', 'Descripcion Acción5', 'Acción', 10)," +
                    "('Acción6', 'Descripcion Acción6', 'Acción', 10)," +

                    "('Deportes1', 'Descripcion Deportes1', 'Deportes', 10)," +
                    "('Deportes2', 'Descripcion Deportes2', 'Deportes', 10)," +
                    "('Deportes3', 'Descripcion Deportes3', 'Deportes', 10)," +
                    "('Deportes4', 'Descripcion Deportes4', 'Deportes', 10)," +
                    "('Deportes5', 'Descripcion Deportes5', 'Deportes', 10)," +
                    "('Deportes6', 'Descripcion Deportes6', 'Deportes', 10)," +

                    "('Simulación1', 'Descripcion Simulación1', 'Simulación', 10)," +
                    "('Simulación2', 'Descripcion Simulación2', 'Simulación', 10)," +
                    "('Simulación3', 'Descripcion Simulación3', 'Simulación', 10)," +
                    "('Simulación4', 'Descripcion Simulación4', 'Simulación', 10)," +
                    "('Simulación5', 'Descripcion Simulación5', 'Simulación', 10)," +
                    "('Simulación6', 'Descripcion Simulación6', 'Simulación', 10)," +

                    "('Rol1', 'Descripcion Rol1', 'Rol', 10)," +
                    "('Rol2', 'Descripcion Rol2', 'Rol', 10)," +
                    "('Rol3', 'Descripcion Rol3', 'Rol', 10)," +
                    "('Rol4', 'Descripcion Rol4', 'Rol', 10)," +
                    "('Rol5', 'Descripcion Rol5', 'Rol', 10)," +
                    "('Rol6', 'Descripcion Rol6', 'Rol', 10)"

            ;





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
