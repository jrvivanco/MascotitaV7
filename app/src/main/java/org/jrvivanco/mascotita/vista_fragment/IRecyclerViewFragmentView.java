package org.jrvivanco.mascotita.vista_fragment;

import org.jrvivanco.mascotita.adapter.MascotaAdaptador;
import org.jrvivanco.mascotita.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by jrvivanco on 22/12/2016.
 */
public interface IRecyclerViewFragmentView {

    public void generarLinerLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRecyclerView(MascotaAdaptador adaptador);

}
