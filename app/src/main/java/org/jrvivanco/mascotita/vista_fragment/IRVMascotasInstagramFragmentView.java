package org.jrvivanco.mascotita.vista_fragment;

import org.jrvivanco.mascotita.adapter.MascotaInstagramAdaptador;
import org.jrvivanco.mascotita.pojo.MascotaInst;
import org.jrvivanco.mascotita.pojo.UsuarioInst;

import java.util.ArrayList;

/**
 * Created by jrvivanco on 21/01/2017.
 */
public interface IRVMascotasInstagramFragmentView {

    public void generarGridLayout();

    public MascotaInstagramAdaptador crearAdaptador(ArrayList<MascotaInst> mascotas);

    public void inicializarAdaptadorInstRecyclerView(MascotaInstagramAdaptador adaptador);

    public UsuarioInst obtenerDatosUsuario(String nomUsuario);
}
