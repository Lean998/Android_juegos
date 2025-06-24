package com.example.juegos;


import android.content.Context;
import android.content.res.loader.AssetsProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class btnJuegoAdapter extends RecyclerView.Adapter<btnJuegoAdapter.btnJuegoViewHolder> {

    private List<JuegoClass> listaJuegos;
    private Context context;

    public btnJuegoAdapter(List<JuegoClass> listaJuegos, Context context) {

        this.listaJuegos = listaJuegos;
        this.context = context;
    }

    @NonNull
    @Override
    public btnJuegoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.btnjuego, parent, false);
        return new btnJuegoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull btnJuegoViewHolder holder, int position) {
        JuegoClass juego = listaJuegos.get(position);
        holder.tituloJuego.setText(juego.getNombre());
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(juego.getGenero()+"/"+juego.getNombre().replace(" ","_")+".png");
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            holder.btnJuego.setImageDrawable(drawable);
        } catch (IOException e) {

        }
        holder.btnJuego.setTag(position); // Para identificar si hace falta
        holder.btnJuego.setOnClickListener(juego.getListener());
    }

    @Override
    public int getItemCount() {
        return listaJuegos.size();
    }

    static class btnJuegoViewHolder extends RecyclerView.ViewHolder {
        ImageView btnJuego;
        TextView tituloJuego;
        public btnJuegoViewHolder(@NonNull View itemView) {
            super(itemView);
            btnJuego = itemView.findViewById(R.id.btnJuego);
            tituloJuego=itemView.findViewById(R.id.tituloJuego);
        }
    }
}
