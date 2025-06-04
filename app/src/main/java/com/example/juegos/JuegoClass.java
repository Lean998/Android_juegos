package com.example.juegos;

import android.view.View;

public class JuegoClass {
    private int id;
    private String nombre;
    private String descripcion;
    private String genero;
    private int niveles;
    private View.OnClickListener listener;

    public JuegoClass(int id,String nombre,String descripcion,String genero,int niveles, View.OnClickListener listener){
        this.id=id;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.genero=genero;
        this.niveles=niveles;
        this.listener=listener;
    }

    public int getId(){
        return this.id;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getDescripcion(){
        return this.descripcion;
    }
    public String getGenero(){
        return this.genero;
    }
    public int getNiveles(){
        return this.niveles;
    }
    public View.OnClickListener getListener(){
        return this.listener;
    }
}
