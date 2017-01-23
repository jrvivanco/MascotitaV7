package org.jrvivanco.mascotita;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.iid.FirebaseInstanceId;

import org.jrvivanco.mascotita.adapter.PageAdapter;
import org.jrvivanco.mascotita.menu_opciones.PedirUsuarioInstagram;
import org.jrvivanco.mascotita.restAPI.ConstantesRestAPI;
import org.jrvivanco.mascotita.restAPIFirebase.EndPointFirebase;
import org.jrvivanco.mascotita.restAPIFirebase.adapter.RestAPIFirebaseAdapter;
import org.jrvivanco.mascotita.restAPIFirebase.model.UsrIDInstTokenResponse;
import org.jrvivanco.mascotita.vista_fragment.RVMascotasInstagramFragment;
import org.jrvivanco.mascotita.menu_opciones.AcercaDeActivity;
import org.jrvivanco.mascotita.menu_opciones.ContactoActivity;
import org.jrvivanco.mascotita.vista_fragment.RecyclerViewFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String usuarioID;
    private String nomUsuario;
    private String urlUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        toolbar = (Toolbar) findViewById(R.id.miActionBar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setUpViewPager();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        //para evitar que se pulse en "Favoritas", lo quito:
        //quito imagen 5 estrellas
        //ImageView img5Stars= (ImageView) findViewById(R.id.imgFiveStarts);
        //img5Stars.setVisibility(View.INVISIBLE);



        //si recibe parámetro de cuenta de Instagram, lanzo el presentador con esa cuenta
        if (getIntent().getExtras() != null) {
            Bundle parametros = getIntent().getExtras();
            usuarioID = parametros.getString("usuarioID");
            nomUsuario = parametros.getString("usuario");
            urlUsuario = parametros.getString("usuarioURL");

        }

        if (usuarioID == null) {
            //si no, con el usuario por defecto
            usuarioID = ConstantesRestAPI.SELF_USER;
            nomUsuario = ConstantesRestAPI.DEFAULT_USERNAME;
            urlUsuario = ConstantesRestAPI.DEFAULT_URL_PROFILE_IMG;

        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Snackbar.make(v, "Mi FloatingActionButton haciendo una accion",Snackbar.LENGTH_LONG).setAction("Accion", null).show();
            }
        });
    }


    public String getUsuarioID() {
        return usuarioID;
    }
    public String getNomUsuario() {
        return nomUsuario;
    }
    public String getUrlUsuario() {
        return urlUsuario;
    }

    //Menú Opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mContacto:
                Intent intentContacto = new Intent(this, ContactoActivity.class);
                startActivity(intentContacto);
                break;
            case R.id.mAcercaDe:
                Intent intentAcercade = new Intent(this, AcercaDeActivity.class);
                startActivity(intentAcercade);
                break;
            case R.id.mPedirUsuarioInstagram:
                Intent intentPedirUsuarioInstagram = new Intent(this, PedirUsuarioInstagram.class);
                startActivity(intentPedirUsuarioInstagram);
                break;
            case R.id.mRecibirNotificaciones:
                enviarDatos();
                break;

        }

        return super.onOptionsItemSelected(item);
    }


    public void irActividadFavoritos(View v) {
        Intent intent = new Intent(MainActivity.this, Favoritas.class);
        //intent.putExtra("listado",mascotas);
        startActivity(intent);
    }

    private ArrayList<Fragment> agregarFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RVMascotasInstagramFragment());
        fragments.add(new RecyclerViewFragment()); // se quita para que no haya confusión al corregir

        return fragments;
    }

    private void setUpViewPager() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_mascotas);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_perfil);
    }


    public void enviarDatos() {
        String token = FirebaseInstanceId.getInstance().getToken();
        enviarRegistro(token, usuarioID, nomUsuario);
    }

    private void enviarRegistro(String id_dispositivo, String id_usuario_instagram, String nombre_usuario_instagram) {
        Log.e("TOKEN", id_dispositivo);
        Log.e("TOKEN", id_usuario_instagram);
        Log.e("TOKEN", nombre_usuario_instagram);
        RestAPIFirebaseAdapter restAPIFirebaseAdapter = new RestAPIFirebaseAdapter();
        EndPointFirebase endPointFirebase = restAPIFirebaseAdapter.establecerConexionRestAPIFirebase();
        Call<UsrIDInstTokenResponse> usrIDTokenResponseCall = endPointFirebase.registrarTokenID(id_dispositivo, id_usuario_instagram, nombre_usuario_instagram);

        usrIDTokenResponseCall.enqueue(new Callback<UsrIDInstTokenResponse>() {
            @Override
            public void onResponse(Call<UsrIDInstTokenResponse> call, Response<UsrIDInstTokenResponse> response) {
                UsrIDInstTokenResponse usrIDInstTokenResponse = response.body();
                //el JSON sólo me devuelve el token
                Log.d("FIREBASE_ID", usrIDInstTokenResponse.getId());
                Log.e("FIREBASE_TOKEN", usrIDInstTokenResponse.getId_dispositivo());
                Log.d("FIREBASE_IDUSR", usrIDInstTokenResponse.getId_usuario_instagram());
                Log.d("FIREBASE_NOMUSR", usrIDInstTokenResponse.getNombre_usuario_instagram());
            }

            @Override
            public void onFailure(Call<UsrIDInstTokenResponse> call, Throwable t) {

            }
        });
    }

}

