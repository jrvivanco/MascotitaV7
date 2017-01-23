package org.jrvivanco.mascotita.pojo;

public class Mascota {

    private int id;
    private int foto;
    private String nombre;
    private int puntos;

    public Mascota(){}
    public Mascota(int foto, String nombre) {
        this.foto = foto;
        this.nombre = nombre;
    }

    public Mascota(int foto, String nombre, int puntos) {
        this.foto = foto;
        this.nombre = nombre;
        this.puntos = puntos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }


}
