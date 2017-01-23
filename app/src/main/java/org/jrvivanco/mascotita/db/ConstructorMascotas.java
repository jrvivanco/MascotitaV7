package org.jrvivanco.mascotita.db;

import android.content.ContentValues;
import android.content.Context;

import org.jrvivanco.mascotita.R;
import org.jrvivanco.mascotita.db.BaseDatos;
import org.jrvivanco.mascotita.db.ConstantesBaseDatos;
import org.jrvivanco.mascotita.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by jrvivanco on 22/12/2016.
 */
public class ConstructorMascotas {
    private Context contexto;
    public ConstructorMascotas(Context contexto){
        this.contexto=contexto;

    }


    public ArrayList<Mascota> obtenerDatos(){
        BaseDatos db = new BaseDatos(contexto);
        insertarMascotas(db);
        return db.obtenerTodasLasMascotas();
    }

    public void insertarMascotas(BaseDatos db){
        //sólo las inserto si no existen
        if(db.cuentaMascotas()==0) {

            ContentValues contentValues;
            contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Scooby");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.mascota1);
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_PUNTOS, 0);
            db.insertarMascota(contentValues);


            contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Infantil");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.mascota2);
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_PUNTOS, 0);
            db.insertarMascota(contentValues);

            contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Hamster");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.mascota3);
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_PUNTOS, 0);
            db.insertarMascota(contentValues);

            contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Brownie");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.mascota4);
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_PUNTOS, 0);
            db.insertarMascota(contentValues);

            contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Tristón");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.mascota5);
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_PUNTOS, 0);
            db.insertarMascota(contentValues);

            contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Player");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.mascota6);
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_PUNTOS, 0);
            db.insertarMascota(contentValues);
        }
    }

    public void darPuntoMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(contexto);
        db.actualizarPuntosMascota(mascota);
    }

    public int obtenerPuntosMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(contexto);
        return db.puntosMascotaId(mascota.getId());
    }

    /*
    public ArrayList<Mascota> obtenerFavoritas(){
        BaseDatos db = new BaseDatos(contexto);
        return db.obtenerMascotasFavoritas();
    }
    */
}
