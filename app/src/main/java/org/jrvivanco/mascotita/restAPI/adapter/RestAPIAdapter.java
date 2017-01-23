package org.jrvivanco.mascotita.restAPI.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jrvivanco.mascotita.restAPI.ConstantesRestAPI;
import org.jrvivanco.mascotita.restAPI.EndPointsAPI;
import org.jrvivanco.mascotita.restAPI.deserializador.LikeDeserializador;
import org.jrvivanco.mascotita.restAPI.deserializador.MascotaDeserializador;
import org.jrvivanco.mascotita.restAPI.deserializador.MediaDeserializador;
import org.jrvivanco.mascotita.restAPI.deserializador.UsuarioInstDeserializador;
import org.jrvivanco.mascotita.restAPI.modelo.LikeResponse;
import org.jrvivanco.mascotita.restAPI.modelo.MascotaResponse;
import org.jrvivanco.mascotita.restAPI.modelo.MediaResponse;
import org.jrvivanco.mascotita.restAPI.modelo.UsuarioResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jrvivanco on 21/01/2017.
 */
public class RestAPIAdapter {

    public EndPointsAPI establecerConexionRestAPIInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestAPI.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndPointsAPI.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorDatosUsuario(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UsuarioResponse.class, new UsuarioInstDeserializador());
        return gsonBuilder.create();
    }

    public Gson contruyeGsonDeserializadorLike(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LikeResponse.class, new LikeDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorMediaUsr(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MediaResponse.class, new MediaDeserializador());
        return gsonBuilder.create();
    }

}
