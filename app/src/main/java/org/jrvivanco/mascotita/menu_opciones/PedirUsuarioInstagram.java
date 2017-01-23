package org.jrvivanco.mascotita.menu_opciones;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import org.jrvivanco.mascotita.MainActivity;
import org.jrvivanco.mascotita.R;
import org.jrvivanco.mascotita.pojo.UsuarioInst;
import org.jrvivanco.mascotita.restAPI.EndPointsAPI;
import org.jrvivanco.mascotita.restAPI.adapter.RestAPIAdapter;
import org.jrvivanco.mascotita.restAPI.modelo.UsuarioResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PedirUsuarioInstagram extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_configurar_cuenta);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //quito imagen 5 estrellas
        //ImageView img5Stars= (ImageView) findViewById(R.id.imgFiveStarts);
        //img5Stars.setVisibility(View.INVISIBLE);
   }

    public void guardaCuentaInstagram(View v)
    {
        EditText edtUsuario = (EditText) findViewById(R.id.edtPideUsuario);
        if(edtUsuario.getText().toString().trim().length() >0) {
            final String nomUsuario = edtUsuario.getText().toString();
            //busca los datos del usuario en el WebService
            buscaDatosUsrInst(nomUsuario);

            Toast.makeText(PedirUsuarioInstagram.this, "Hay usuario", Toast.LENGTH_LONG).show();
        }else
        {
            Toast toast=Toast.makeText(PedirUsuarioInstagram.this, "Hay que poner un usuario", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
    }

    public void buscaDatosUsrInst(String nombreUsuario){
        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        Gson gsonDatosUsuario = restAPIAdapter.construyeGsonDeserializadorDatosUsuario();
        EndPointsAPI endPointsAPI = restAPIAdapter.establecerConexionRestAPIInstagram(gsonDatosUsuario);
        Call<UsuarioResponse> usuarioResponseCall = endPointsAPI.getDatosUsuario(nombreUsuario);

        Toast.makeText(getBaseContext(), "El usuario " + nombreUsuario + " existe", Toast.LENGTH_LONG).show();
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                String nuevoUsuario = "";
                String nuevoID = "";
                String nuevaURL = "";

                UsuarioResponse usuarioResponse = response.body(); //data JSON
                UsuarioInst usuarioInst = usuarioResponse.getUsuarioInst();
                Intent intent = new Intent(PedirUsuarioInstagram.this, MainActivity.class);

    //                    if (usuarioInst.getId().trim().length()>0) {
    //                        Toast.makeText(getBaseContext(), "El usuario " + nomUsuario + " existe", Toast.LENGTH_LONG).show();
                nuevoUsuario = usuarioInst.getFullName();
                nuevoID = usuarioInst.getId();
                nuevaURL = usuarioInst.getUrlFotoPerfil();
    //                    } else {
    //                        Toast.makeText(getBaseContext(), "El usuario " + nomUsuario + " no existe", Toast.LENGTH_LONG).show();
    //                        nuevoUsuario = "";
    //                        nuevoID = "self";
    //                        nuevaURL = "";
    //                    }
                intent.putExtra("usuario", nuevoUsuario);
                intent.putExtra("usuarioID", nuevoID);
                intent.putExtra("usuarioURL", nuevaURL);

                startActivity(intent);
                //Toast.makeText(PedirUsuarioInstagram.this, nuevoUsuario+" - "+nuevoID, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Toast toast=Toast.makeText(getBaseContext(), "¡Error conectando! ¿Existe el usuario?", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
                //Log.e("Falló conexión WS", t.toString());
            }
        });
    }
}
