package org.jrvivanco.mascotita;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import org.jrvivanco.mascotita.adapter.MascotaAdaptador;
import org.jrvivanco.mascotita.db.BaseDatos;
import org.jrvivanco.mascotita.menu_opciones.PedirUsuarioInstagram;
import org.jrvivanco.mascotita.menu_opciones.AcercaDeActivity;
import org.jrvivanco.mascotita.menu_opciones.ContactoActivity;
import org.jrvivanco.mascotita.pojo.Mascota;

import java.util.ArrayList;

public class Favoritas extends AppCompatActivity {

    ArrayList<Mascota> mascotasFav;
    private RecyclerView listaMascotasFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritas);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listaMascotasFav= (RecyclerView) findViewById(R.id.rvMascotasFavoritas);


        //GridLayoutManager lm = new GridLayoutManager(this,2);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotasFav.setLayoutManager(lm);
        inicializaListaMascotasFav();
        inicializarAdaptador();


    }

    public void inicializarAdaptador(){
        //crea objeto mascotaAdaptador para que reciba la lista y que pueda hacer lo configurado
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotasFav);
        listaMascotasFav.setAdapter(adaptador);
    }

    public void inicializaListaMascotasFav(){
        mascotasFav = new ArrayList<Mascota>();
        BaseDatos db = new BaseDatos(getApplicationContext());
        mascotasFav = db.obtenerMascotasFavoritas();
    }

    // ToDo -> Menú Opciones - guardar en clase aparte para manejarlo una única vez
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.mContacto:
                Intent intentContacto = new Intent(this, ContactoActivity.class);
                startActivity(intentContacto);
                break;
            case R.id.mAcercaDe:
                Intent intentAcercade= new Intent(this, AcercaDeActivity.class);
                startActivity(intentAcercade);
                break;
            case R.id.mPedirUsuarioInstagram:
                Intent intentPedirUsuarioInstagram = new Intent(this, PedirUsuarioInstagram.class);
                startActivity(intentPedirUsuarioInstagram);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
