package com.example.juegos;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class btnJuegoAdapter extends RecyclerView.Adapter<btnJuegoAdapter.btnJuegoViewHolder> {

    private List<JuegoClass> listaJuegos;

    public btnJuegoAdapter(List<JuegoClass> listaJuegos) {
        this.listaJuegos = listaJuegos;
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
        holder.btnJuego.setText(juego.getNombre());
        String nombreJuego = juego.getNombre();
        holder.btnJuego.setText(nombreJuego);
        holder.btnJuego.setTag(position); // Para identificar si hace falta
        holder.btnJuego.setOnClickListener(juego.getListener());
    }

    @Override
    public int getItemCount() {
        return listaJuegos.size();
    }

    static class btnJuegoViewHolder extends RecyclerView.ViewHolder {
        Button btnJuego;
        public btnJuegoViewHolder(@NonNull View itemView) {
            super(itemView);
            btnJuego = itemView.findViewById(R.id.btnJuego);
        }
    }
}
