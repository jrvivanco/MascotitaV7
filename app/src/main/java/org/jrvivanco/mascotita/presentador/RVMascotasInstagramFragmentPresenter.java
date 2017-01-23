package org.jrvivanco.mascotita.presentador;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import org.jrvivanco.mascotita.R;
import org.jrvivanco.mascotita.db.ConstructorMascotasInst;
import org.jrvivanco.mascotita.pojo.MascotaInst;
import org.jrvivanco.mascotita.restAPI.ConstantesRestAPI;
import org.jrvivanco.mascotita.restAPI.EndPointsAPI;
import org.jrvivanco.mascotita.restAPI.adapter.RestAPIAdapter;
import org.jrvivanco.mascotita.restAPI.modelo.MascotaResponse;
import org.jrvivanco.mascotita.vista_fragment.IRVMascotasInstagramFragmentView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jrvivanco on 21/01/2017.
 */
public class RVMascotasInstagramFragmentPresenter implements IRVMascotasInstagramFragmentPresenter{
    private IRVMascotasInstagramFragmentView irvMascotasInstagramFragmentView;
    private Context context;
    private ConstructorMascotasInst constructorMascotasInst;
    private ArrayList<MascotaInst> mascotasInst;

    public RVMascotasInstagramFragmentPresenter(IRVMascotasInstagramFragmentView irvMascotasInstagramFragmentView, Context context, String usuarioID){
        this.irvMascotasInstagramFragmentView=irvMascotasInstagramFragmentView;
        this.context=context;
        //obtenerMascotasInstagram();

        obtenerMediosRecientes(usuarioID);
    }

    @Override
    public void obtenerMediosRecientes(String userID) {
        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        Gson gsonMediaRecent = restAPIAdapter.construyeGsonDeserializadorMediaRecent();
        EndPointsAPI endPointsAPI= restAPIAdapter.establecerConexionRestAPIInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endPointsAPI.getRecentMedia(userID);

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse= response.body(); //contiene la data del JSON
                mascotasInst = mascotaResponse.getMascotas();
                mostrarMascotasInstRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "¡Error conectanto! Inténtalo de nuevo", Toast.LENGTH_LONG).show();
                Log.e("Falló conexión WS",t.toString());
            }
        });

    }

    @Override
    public void mostrarMascotasInstRV() {
        irvMascotasInstagramFragmentView.inicializarAdaptadorInstRecyclerView(irvMascotasInstagramFragmentView.crearAdaptador(mascotasInst));
        irvMascotasInstagramFragmentView.generarGridLayout();
    }
}

