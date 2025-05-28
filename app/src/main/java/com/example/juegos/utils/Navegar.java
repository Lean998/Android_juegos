package com.example.juegos.utils;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.example.juegos.CategoriasMain;

public class Navegar {




    private Navegar() {

    }

    public static void NavegarA(Context context, @Nullable  String titulo) {
        Intent intent = new Intent(context, CategoriasMain.class);
        intent.putExtra("titulo", titulo);
        context.startActivity(intent);
    }

}