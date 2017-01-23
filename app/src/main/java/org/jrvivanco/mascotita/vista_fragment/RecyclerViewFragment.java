package org.jrvivanco.mascotita.vista_fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import org.jrvivanco.mascotita.MainActivity;
import org.jrvivanco.mascotita.R;
import org.jrvivanco.mascotita.adapter.MascotaAdaptador;
import org.jrvivanco.mascotita.pojo.Mascota;
import org.jrvivanco.mascotita.presentador.IRecyclerViewFragmentPresenter;
import org.jrvivanco.mascotita.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by jrvivanco on 22/12/2016.
 */
public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView {
    ArrayList<Mascota> mascotas;
    private RecyclerView rvListaMascotas;
    private IRecyclerViewFragmentPresenter presentador;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recyclerview, container,false);

        //return super.onCreateView(inflater, container, savedInstanceState);

        rvListaMascotas= (RecyclerView) v.findViewById(R.id.rvMascotas);
        presentador= new RecyclerViewFragmentPresenter(this, getContext());
        return v;

    }


    @Override
    public void generarLinerLayoutVertical() {
        //GridLayoutManager lm = new GridLayoutManager(this,2);
        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        rvListaMascotas.setLayoutManager(lm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        //crea objeto mascotaAdaptador para que reciba la lista y que pueda hacer lo configurado
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRecyclerView(MascotaAdaptador adaptador) {
        rvListaMascotas.setAdapter(adaptador);
    }

}
