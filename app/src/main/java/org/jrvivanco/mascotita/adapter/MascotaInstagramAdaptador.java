package org.jrvivanco.mascotita.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import org.jrvivanco.mascotita.MainActivity;
import org.jrvivanco.mascotita.R;
import org.jrvivanco.mascotita.pojo.LikeInst;
import org.jrvivanco.mascotita.pojo.MascotaInst;
import org.jrvivanco.mascotita.pojo.MediaInst;
import org.jrvivanco.mascotita.restAPI.ConstantesRestAPI;
import org.jrvivanco.mascotita.restAPI.EndPointsAPI;
import org.jrvivanco.mascotita.restAPI.JSONKeys;
import org.jrvivanco.mascotita.restAPI.adapter.RestAPIAdapter;
import org.jrvivanco.mascotita.restAPI.modelo.LikeResponse;
import org.jrvivanco.mascotita.restAPI.modelo.MediaResponse;
import org.jrvivanco.mascotita.restAPIFirebase.EndPointFirebase;
import org.jrvivanco.mascotita.restAPIFirebase.adapter.RestAPIFirebaseAdapter;
import org.jrvivanco.mascotita.restAPIFirebase.model.UsrIDInstTokenResponse;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jrvivanco on 21/01/2017.
 */
public class MascotaInstagramAdaptador extends RecyclerView.Adapter<MascotaInstagramAdaptador.MascotaInstagramViewHolder>{
    ArrayList<MascotaInst> mascotasInst;

    Activity actividad;

    String currentToken = FirebaseInstanceId.getInstance().getToken();

    public MascotaInstagramAdaptador(ArrayList<MascotaInst> mascotasInst, Activity actividad){
        //contructor para pasarle la lista de mascotas
        this.mascotasInst=mascotasInst;
        this.actividad=actividad;
    }


    @Override
    public MascotaInstagramViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Infla nuestro layout cardview y lo pasa al ViewHolder para que obtenga cada elemento (los views)
        View v;
        /*if(actividad != null){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota_fav,parent,false);
        }else{*/
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_grid_mascota,parent,false);
        //}

        return new MascotaInstagramViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaInstagramViewHolder mascotaInstagramViewHolder, final int position) {
        //Asocia cada elemento de la lista con cada view
        final MascotaInst mascotaInst = mascotasInst.get(position);

        Picasso.with(actividad)
                .load(mascotaInst.getUrlImagen())
                .placeholder(R.drawable.dog)
                .into(mascotaInstagramViewHolder.imgFotoInst);
        mascotaInstagramViewHolder.tvLikesInst.setText(String.valueOf(mascotaInst.getLikes()));

        //para lanzar peticiones al hacer like
        mascotaInstagramViewHolder.imgFotoInst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("LIKE_DADO_A",mascotaInst.getId());
                //Primero, EndPoint a Instagram para hacer el like
                RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
                Gson gsonLike = restAPIAdapter.contruyeGsonDeserializadorLike();
                EndPointsAPI endPointsAPI = restAPIAdapter.establecerConexionRestAPIInstagram(gsonLike);
                Call<LikeResponse> likeResponseCall = endPointsAPI.setLikeMedia(mascotaInst.getId(), ConstantesRestAPI.ACCESS_TOKEN);
                likeResponseCall.enqueue(new Callback<LikeResponse>() {
                    @Override
                    public void onResponse(Call<LikeResponse> call, Response<LikeResponse> response) {
                        int codigo=0;
                        LikeResponse likeResponse = response.body();

                        LikeInst likeInst = likeResponse.getLikeInst();
                        codigo=likeInst.getCode();
                        Log.e("LIKE_CODIGO_RESP",String.valueOf(codigo));

                        if(codigo==200) { //si el código ==200 -> Instagram devuelve ok al like



                            //notifica(tokenActual, usuario dueño de foto) envía notificacion
                            //mediaUserID llama a la función que devuelve el dueño de la foto
                            //notifica("",mediaUserID(mascotaInst.getId()));
                            //String idUsuario=mediaUserID(mascotaInst.getId());
                            Log.e("idFoto",mascotaInst.getId());
                            //Recarga la actividad, para se que actualicen los likes.
                            actividad.recreate();
                        }else{
                            Toast.makeText(actividad, "El webservice devolvió error code="+ String.valueOf(codigo), Toast.LENGTH_LONG).show();
                            Log.e("ERROR_LIKE_DADO_A",response.body().toString());
                        }

                    }

                    @Override
                    public void onFailure(Call<LikeResponse> call, Throwable t) {
                        Log.e("ERROR",t.toString());
                        Toast toast=Toast.makeText(actividad, "¡Error al hacer like!", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER,0,0);
                        toast.show();
                    }
                });

                //Segundo, EndPoint a Firebase para añadir el like-generado; pasando primero por el Endpoint que averigua el propietario de la foto
                mediaUserID(mascotaInst.getId()); //averigua el propietario de la foto y lanza el endpoint para añadir like-generado

            }
        });


    }

    @Override
    public int getItemCount() { //Cantidad de elementos que contiene mi lista
        return mascotasInst.size();
    }

    public static class MascotaInstagramViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFotoInst;
        private TextView tvLikesInst;

        public MascotaInstagramViewHolder(View itemView) {
            super(itemView);
            imgFotoInst     =(ImageView) itemView.findViewById(R.id.imgFotoInst);
            tvLikesInst     =(TextView) itemView.findViewById(R.id.tvLikesInst);

        }
    }

    public void mediaUserID(final String fotoID){

        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        Gson gsonMediaUsr = restAPIAdapter.construyeGsonDeserializadorMediaUsr();
        EndPointsAPI endPointsAPI = restAPIAdapter.establecerConexionRestAPIInstagram(gsonMediaUsr);

        Call<MediaResponse> mediaResponseCall = endPointsAPI.getMediaUser(fotoID);
        mediaResponseCall.enqueue(new Callback<MediaResponse>() {
            @Override
            public void onResponse(Call<MediaResponse> call, Response<MediaResponse> response) {
                MediaResponse mediaResponse=response.body();
                MediaInst mediaInst=mediaResponse.getMediaInst();
                //Log.e("usuario:",mediaInst.getIdUsuario());
                //Toast.makeText(actividad, "La foto es de: "+mediaInst.getIdUsuario(), Toast.LENGTH_LONG).show();
                //lanza endPoint para añadir like-generado
                darLikeFB(currentToken,mediaInst.getIdUsuario(),fotoID);
                //lanza notificación
                notifica(currentToken,mediaInst.getIdUsuario(),mediaInst.getFotoUSuario());
            }

            @Override
            public void onFailure(Call<MediaResponse> call, Throwable t) {
                Log.e("response.error",t.toString());
                Toast.makeText(actividad, "Error conexión buscando dueño de foto", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void notifica(String idTokenActual, String usrIDReceptor, String urlFotoPerfil){

        //UsrIDInstTokenResponse usuarioANotificar = new UsrIDInstTokenResponse(idTokenActual,usrIDReceptor);
        RestAPIFirebaseAdapter restAPIFirebaseAdapter = new RestAPIFirebaseAdapter();
        EndPointFirebase endPointFirebase = restAPIFirebaseAdapter.establecerConexionRestAPIFirebase();

        //Call<UsrIDInstTokenResponse> usrIDInstTokenResponseCall = endPointFirebase.notificacionUsuario(usuarioANotificar.getId_dispositivo(),usuarioANotificar.getId_usuario_instagram());
        Call<UsrIDInstTokenResponse> usrIDInstTokenResponseCall = endPointFirebase.notificacionUsuario(idTokenActual,usrIDReceptor,urlFotoPerfil);
        usrIDInstTokenResponseCall.enqueue(new Callback<UsrIDInstTokenResponse>() {
            @Override
            public void onResponse(Call<UsrIDInstTokenResponse> call, Response<UsrIDInstTokenResponse> response) {
                UsrIDInstTokenResponse registroDevuelto = response.body();
                //Log.e("DEVUELTO_ID",registroDevuelto.getId());
                //Log.e("DEVUELTO_ID_TOKEN",registroDevuelto.getId_dispositivo());
                Log.e("DEVUELTO_ID_USR_INT",registroDevuelto.getId_usuario_instagram());
                //Log.e("DEVUELTO_NOM_USR_INS",registroDevuelto.getNombre_usuario_instagram());

            }

            @Override
            public void onFailure(Call<UsrIDInstTokenResponse> call, Throwable t) {
                Log.e("error.notifica",t.toString());
                Toast.makeText(actividad, "Error enviando notificación", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void darLikeFB(String idToken, String idUser, String idFoto){
        Log.e("likeFB.tok",idToken);
        Log.e("likeFB.usrid",idUser);
        Log.e("likeFB.idfoto",idFoto);
        RestAPIFirebaseAdapter restAPIFirebaseAdapter = new RestAPIFirebaseAdapter();
        EndPointFirebase endPointFirebase = restAPIFirebaseAdapter.establecerConexionRestAPIFirebase();
        Call<UsrIDInstTokenResponse> tokenDevueltoCall = endPointFirebase.darLike(idToken,idUser, idFoto);
        tokenDevueltoCall.enqueue(new Callback<UsrIDInstTokenResponse>() {
            @Override
            public void onResponse(Call<UsrIDInstTokenResponse> call, Response<UsrIDInstTokenResponse> response) {
               UsrIDInstTokenResponse usrIDInstTokenResponse = response.body();
               //Log.e("TOKENDEVUELTO", usrIDInstTokenResponse.getId_dispositivo());

            }

            @Override
            public void onFailure(Call<UsrIDInstTokenResponse> call, Throwable t) {
                Log.e("error.darLikeFB",t.toString());
                Toast.makeText(actividad, "Error añadiendo like-generado en FB", Toast.LENGTH_LONG).show();
            }
        });

    }
}
