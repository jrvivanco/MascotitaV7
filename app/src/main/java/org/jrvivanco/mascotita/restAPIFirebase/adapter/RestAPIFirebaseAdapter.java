package org.jrvivanco.mascotita.restAPIFirebase.adapter;

import org.jrvivanco.mascotita.restAPIFirebase.ConstantesAPIFirebase;
import org.jrvivanco.mascotita.restAPIFirebase.EndPointFirebase;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jrvivanco on 09/01/2017.
 */
public class RestAPIFirebaseAdapter {

    public EndPointFirebase establecerConexionRestAPIFirebase(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesAPIFirebase.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;
        return retrofit.create(EndPointFirebase.class);
    }
}
