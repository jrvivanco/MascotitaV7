package org.jrvivanco.mascotita.presentador;

import android.content.Context;

import org.jrvivanco.mascotita.db.ConstructorMascotas;
import org.jrvivanco.mascotita.pojo.Mascota;
import org.jrvivanco.mascotita.vista_fragment.IRecyclerViewFragmentView;

import java.util.ArrayList;

/**
 * Created by jrvivanco on 07/01/2017.
 */
public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context contexto;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context contexto) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.contexto=contexto;
        obtenerMascotasBaseDatos();
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(contexto);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarContactosRV();
    }

    @Override
    public void mostrarContactosRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRecyclerView(iRecyclerViewFragmentView.crearAdaptador(mascotas));
        iRecyclerViewFragmentView.generarLinerLayoutVertical();
    }
}
