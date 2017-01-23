package org.jrvivanco.mascotita.restAPIFirebase.model;

/**
 * Created by jrvivanco on 09/01/2017.
 */
public class UsrIDInstTokenResponse {
    private String id;
    private String id_dispositivo; // token
    private String id_usuario_instagram;
    private String nombre_usuario_instagram;

    public UsrIDInstTokenResponse(String id, String id_dispositivo, String id_usuario_instagram, String nombre_usuario_instagram) {
        this.id = id;
        this.id_dispositivo = id_dispositivo;
        this.id_usuario_instagram = id_usuario_instagram;
        this.nombre_usuario_instagram = nombre_usuario_instagram;
    }


    //nuevo constructor para poder buscar por id_usr_inst + token
    public UsrIDInstTokenResponse(String id_dispositivo, String id_usuario_instagram) {
        this.id_dispositivo = id_dispositivo;
        this.id_usuario_instagram = id_usuario_instagram;
    }

    public UsrIDInstTokenResponse(String id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(String id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }

    public String getId_usuario_instagram() {
        return id_usuario_instagram;
    }

    public void setId_usuario_instagram(String id_usuario_instagram) {
        this.id_usuario_instagram = id_usuario_instagram;
    }

    public String getNombre_usuario_instagram() {
        return nombre_usuario_instagram;
    }

    public void setNombre_usuario_instagram(String nombre_usuario_instagram) {
        this.nombre_usuario_instagram = nombre_usuario_instagram;
    }
}

