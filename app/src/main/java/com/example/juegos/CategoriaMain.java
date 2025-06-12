package com.example.juegos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class CategoriaMain extends BaseActivity {
    private String categoria;
    private ArrayList<String> categorias;
    private ViewPager viewPager;
    private int posicionActual = 0;
    private CategoriaPagerAdapter categoriaPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        categoria = intent.getStringExtra("categoria");
        setContentView(R.layout.categoria_main);

        viewPager = findViewById(R.id.viewPager_categoria);

        inicializarNavegacionViewPager();

        TextView tituloHeader = findViewById(R.id.hd_title);
        tituloHeader.setText(categoria);

        List<JuegoClass> listaJuegos=new ArrayList<>();

        RecyclerView recyclerJuegos = findViewById(R.id.recyclerJuegos);
        recyclerJuegos.setLayoutManager(new LinearLayoutManager(this));

        DBPartidaHelper dbHelper = new DBPartidaHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Log.d("CategoriaMain", "CATEGORIA RECIBIDA: " + categoria);
        Cursor cursor = db.rawQuery("SELECT id, nombre, descripcion, niveles FROM juegos WHERE genero = ?", new String[]{categoria});
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String descripcion = cursor.getString(2);
            int niveles = cursor.getInt(3);
            listaJuegos.add(new JuegoClass(id, nombre, descripcion, "", niveles,v -> abrirJuego(id,nombre,descripcion,categoria,niveles)));
        }
        cursor.close();

        btnJuegoAdapter adapter = new btnJuegoAdapter(listaJuegos);
        recyclerJuegos.setAdapter(adapter);

        configurarLogoInicio();
        configurarBtnVolver();

    }
    private void abrirJuego(int idJuego, String nombreJuego, String descripcionJuego, String categoriaJuego , int nivelesJuego) {
        Intent intent = new Intent(this, com.example.juegos.DetalleJuegoActivity.class);
        intent.putExtra("idJuego",idJuego);
        intent.putExtra("nombreJuego", nombreJuego);
        intent.putExtra("descripcionJuego", descripcionJuego);
        intent.putExtra("nivelesJuego", nivelesJuego);
        intent.putExtra("categoria", categoriaJuego);
        startActivity(intent);
    }

    private void inicializarNavegacionViewPager() {
        cargarCategoria();

        if (categorias != null && categorias.size() > 1) {
            categoriaPagerAdapter = new CategoriaPagerAdapter();
            viewPager.setAdapter(categoriaPagerAdapter);
            viewPager.setCurrentItem(posicionActual);


            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    posicionActual = position;
                    actualizarCategoriaActual();
                    View headerView = findViewById(R.id.main_header);
                    TextView tituloHeader = headerView.findViewById(R.id.hd_title);
                    tituloHeader.setText(categorias.get(posicionActual));
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        } else {
            // Si solo hay un juego, ocultar el ViewPager
            viewPager.setVisibility(ViewPager.GONE);
        }
    }

    private void cargarCategoria() {
        categorias = new ArrayList<>();
        categorias.add(0,"Estrategia");
        categorias.add(1,"Disparos");
        categorias.add(2,"Acción");
        categorias.add(3,"Deportes");
        categorias.add(4,"Simulación");
        categorias.add(5,"Rol");

        for(int i=0;i<categorias.size();i++) {
            if (categorias.get(i).equalsIgnoreCase(categoria.trim())) {
                posicionActual = i;
            }
        }
    }

    private void actualizarCategoriaActual() {
        if (categorias != null && posicionActual < categorias.size()) {
            categoria = categorias.get(posicionActual);
        }
    }

    private class CategoriaPagerAdapter extends androidx.viewpager.widget.PagerAdapter {

        @Override
        public int getCount() {
            return categorias != null ? categorias.size() : 0;
        }
        @Override
        public Object instantiateItem(android.view.ViewGroup container, int position) {
            android.view.LayoutInflater inflater = (android.view.LayoutInflater)
                    getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);

            android.view.View view = inflater.inflate(R.layout.item_categoria_main, container, false);

            String otraCategoria = categorias.get(position);

            List<JuegoClass> listaJuegos=new ArrayList<>();

            RecyclerView recyclerJuegos = view.findViewById(R.id.recyclerJuegos);
            recyclerJuegos.setLayoutManager(new LinearLayoutManager(CategoriaMain.this));

            DBPartidaHelper dbHelper = new DBPartidaHelper(CategoriaMain.this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            Log.d("CategoriaMain", "CATEGORIA RECIBIDA: " + otraCategoria);
            Cursor cursor = db.rawQuery("SELECT id, nombre, descripcion, niveles FROM juegos WHERE genero = ?", new String[]{otraCategoria});
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                String descripcion = cursor.getString(2);
                int niveles = cursor.getInt(3);
                listaJuegos.add(new JuegoClass(id, nombre, descripcion, "", niveles,v -> abrirJuego(id,nombre,descripcion,otraCategoria,niveles)));
            }
            cursor.close();

            btnJuegoAdapter adapter = new btnJuegoAdapter(listaJuegos);
            recyclerJuegos.setAdapter(adapter);

            container.addView(view, 0);
            return view;
        }

        @Override
        public void destroyItem(android.view.ViewGroup container, int position, Object object) {
            container.removeView((android.view.View) object);
        }

        @Override
        public boolean isViewFromObject(android.view.View view, Object object) {
            return view == object;
        }
    }
}

