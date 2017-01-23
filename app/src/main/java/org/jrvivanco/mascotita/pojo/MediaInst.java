package org.jrvivanco.mascotita.pojo;

/**
 * Created by jrvivanco on 14/01/2017.
 */
public class MediaInst {
    //aunque podría contener todos los datos de un elemento de Instagram, sólo quiero el usuario y su foto de perfil
    String idUsuario;
    String fotoUSuario;

    public MediaInst(String idUsuario, String fotoUSuario) {
        this.idUsuario = idUsuario;
        this.fotoUSuario= fotoUSuario;
    }

    public MediaInst() {
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFotoUSuario() {
        return fotoUSuario;
    }

    public void setFotoUSuario(String fotoUSuario) {
        this.fotoUSuario = fotoUSuario;
    }
}
