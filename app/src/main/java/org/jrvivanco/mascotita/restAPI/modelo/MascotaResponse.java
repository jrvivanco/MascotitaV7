package org.jrvivanco.mascotita.restAPI.modelo;

import org.jrvivanco.mascotita.pojo.Mascota;
import org.jrvivanco.mascotita.pojo.MascotaInst;

import java.util.ArrayList;

/**
 * Created by jrvivanco on 21/01/2017.
 */
public class MascotaResponse {
    ArrayList<MascotaInst> mascotas;

    public ArrayList<MascotaInst> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<MascotaInst> mascotas) {
        this.mascotas = mascotas;
    }
}
