package com.example.juegos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;


public class DetalleJuegoActivity extends BaseActivity {

    private TextView textoInfo;
    private Button btnEstadisticas, btnJugar;
    private boolean mostrandoDescripcion = true;
    private String descripcionJuego;
    private String categoria;

    private int idJuego;
    private String nombreJuego;
    private int niveles;
    private ImageView imagenJuego;
    private ArrayList<JuegoClass> juegosMismaCategoria;
    private int posicionActual = 0;
    private ViewPager viewPager;
    private JuegosPagerAdapter juegosPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_juego);

        configurarLogoInicio();
        configurarBtnVolver();

        textoInfo = findViewById(R.id.textoInfo);
        btnJugar = findViewById(R.id.btnJugar);
        btnEstadisticas = findViewById(R.id.btnEstadisticas);
        imagenJuego = findViewById(R.id.imagenJuego);
        viewPager = findViewById(R.id.viewPager_juegos);

        idJuego = getIntent().getIntExtra("idJuego",-1);
        nombreJuego = getIntent().getStringExtra("nombreJuego");
        descripcionJuego = getIntent().getStringExtra("descripcionJuego");
        categoria = getIntent().getStringExtra("categoria");
        niveles= getIntent().getIntExtra("nivelesJuego",-1);
        textoInfo.setText(descripcionJuego);

        if (idJuego == -1) {
            Toast.makeText(this, "Error: no se recibió el id del juego", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        if (nombreJuego == null) {
            Toast.makeText(this, "Error: no se recibió el nombre del juego", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        if (niveles == -1) {
            Toast.makeText(this, "Error: no se recibieron los niveles del juego", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        inicializarNavegacionViewPager();

        btnJugar.setOnClickListener(v -> {
            Intent intent = new Intent(DetalleJuegoActivity.this, SimulacionJuegoActivity.class);
            intent.putExtra("nombreJuego", nombreJuego);
            intent.putExtra("idJuego", idJuego);
            intent.putExtra("nivelesJuego",niveles);
            startActivity(intent);
        });

        btnEstadisticas.setOnClickListener(v -> {
            Intent intent = new Intent(DetalleJuegoActivity.this, EstadisticasJuego.class);
            intent.putExtra("idJuego", idJuego);
            startActivity(intent);
        });

        Log.d("DEBUG", "Juegos encontrados: " + juegosMismaCategoria.size());
        Log.d("DEBUG", "Lanzando DetalleJuegoActivity con idJuego: " + idJuego);
    }

    private void inicializarNavegacionViewPager() {
        cargarJuegosMismaCategoria();

        if (juegosMismaCategoria != null && juegosMismaCategoria.size() > 1) {
            juegosPagerAdapter = new JuegosPagerAdapter();
            viewPager.setAdapter(juegosPagerAdapter);
            viewPager.setCurrentItem(posicionActual);


            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    posicionActual = position;
                    actualizarJuegoActual();
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

    private void cargarJuegosMismaCategoria() {
        juegosMismaCategoria = new ArrayList<>();

        DBPartidaHelper dbHelper = new DBPartidaHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String query = "SELECT * FROM " + DBPartidaHelper.TABLE_JUEGOS +
                        " WHERE " + DBPartidaHelper.COLUMN_GENERO + " = ? " +
                        " ORDER BY " + DBPartidaHelper.COLUMN_ID;
        Cursor cursor = db.rawQuery(query, new String[]{categoria});

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DBPartidaHelper.COLUMN_ID));
            String nombre = cursor.getString(cursor.getColumnIndexOrThrow(DBPartidaHelper.COLUMN_NOMBRE));
            String descripcion = cursor.getString(cursor.getColumnIndexOrThrow(DBPartidaHelper.COLUMN_DESCRIPCION));
            String genero = cursor.getString(cursor.getColumnIndexOrThrow(DBPartidaHelper.COLUMN_GENERO));
            int nivelesJuego = cursor.getInt(cursor.getColumnIndexOrThrow(DBPartidaHelper.COLUMN_NIVELES));

            JuegoClass juego = new JuegoClass(id, nombre, descripcion, genero, nivelesJuego, null);
            juegosMismaCategoria.add(juego);

            // Encontrar posición actual
            if (id == idJuego) {
                posicionActual = juegosMismaCategoria.size() - 1;
            }
        }

        cursor.close();
        db.close();
    }

    private void actualizarJuegoActual() {
        if (juegosMismaCategoria != null && posicionActual < juegosMismaCategoria.size()) {
            JuegoClass juegoActual = juegosMismaCategoria.get(posicionActual);
            idJuego = juegoActual.getId();
            nombreJuego = juegoActual.getNombre();
            descripcionJuego = juegoActual.getDescripcion();
            categoria = juegoActual.getGenero();
            niveles = juegoActual.getNiveles();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    public String getCategoria() {
        return categoria;
    }
    private class JuegosPagerAdapter extends androidx.viewpager.widget.PagerAdapter {

        @Override
        public int getCount() {
            return juegosMismaCategoria != null ? juegosMismaCategoria.size() : 0;
        }
        @Override
        public Object instantiateItem(android.view.ViewGroup container, int position) {
            android.view.LayoutInflater inflater = (android.view.LayoutInflater)
                    getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE);

            android.view.View view = inflater.inflate(R.layout.item_juego_detalle, container, false);

            JuegoClass juego = juegosMismaCategoria.get(position);

            TextView textoInfoPager = view.findViewById(R.id.textoInfo);
            Button btnJugarPager = view.findViewById(R.id.btnJugar);
            Button btnEstadisticasPager = view.findViewById(R.id.btnEstadisticas);
            ImageView imagenJuegoPager = view.findViewById(R.id.imagenJuego);

            textoInfoPager.setText(juego.getDescripcion());

            btnJugarPager.setOnClickListener(v -> {
                Intent intent = new Intent(DetalleJuegoActivity.this, SimulacionJuegoActivity.class);
                intent.putExtra("nombreJuego", juego.getNombre());
                intent.putExtra("idJuego", juego.getId());
                intent.putExtra("nivelesJuego", juego.getNiveles());
                startActivity(intent);
            });

            btnEstadisticasPager.setOnClickListener(v -> {
                Intent intent = new Intent(DetalleJuegoActivity.this, EstadisticasJuego.class);
                intent.putExtra("idJuego", juego.getId());
                startActivity(intent);
            });

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
