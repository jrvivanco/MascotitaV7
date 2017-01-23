package org.jrvivanco.mascotita.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.jrvivanco.mascotita.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by jrvivanco on 22/12/2016.
 */
public class BaseDatos extends SQLiteOpenHelper{
    private Context contexto;

    public BaseDatos(Context contexto) {
        super(contexto, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.contexto = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaContacto = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTAS + "(" +
                ConstantesBaseDatos.TABLE_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_FOTO + " INTEGER, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_PUNTOS + " INTEGER" +
                ")";
        db.execSQL(queryCrearTablaContacto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_MASCOTAS);
        onCreate(db);
    }

    public ArrayList<Mascota> obtenerTodasLasMascotas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT "+ConstantesBaseDatos.TABLE_MASCOTAS_ID+","
                +ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE+","
                +ConstantesBaseDatos.TABLE_MASCOTAS_FOTO+","
                +ConstantesBaseDatos.TABLE_MASCOTAS_PUNTOS
                +" FROM " + ConstantesBaseDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));
            mascotaActual.setPuntos(registros.getInt(3));
            mascotas.add(mascotaActual);

        }
        registros.close();
        db.close();

        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues){
        //sólo se usará la primera vez
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTAS,null, contentValues);
        db.close();
    }

   public void actualizarPuntosMascota(Mascota mascota){
       SQLiteDatabase db = this.getWritableDatabase();
       String query="UPDATE "+ConstantesBaseDatos.TABLE_MASCOTAS
                +" SET "+ConstantesBaseDatos.TABLE_MASCOTAS_PUNTOS+"="+ConstantesBaseDatos.TABLE_MASCOTAS_PUNTOS+"+1"
                +" WHERE "+ConstantesBaseDatos.TABLE_MASCOTAS_ID+"="+mascota.getId();
       db.execSQL(query);
       db.close();

    }

   public int cuentaMascotas(){
        int total=0;

        String query="select count(*) from "+ConstantesBaseDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);
        while (registros.moveToNext()) {
            total=registros.getInt(0);
        }
        return total;
   }

   public int puntosMascotaId(int id){
       int puntos = 0;
       String query = "SELECT "+ConstantesBaseDatos.TABLE_MASCOTAS_PUNTOS+" as puntos" +
               " FROM " + ConstantesBaseDatos.TABLE_MASCOTAS +
               " WHERE " + ConstantesBaseDatos.TABLE_MASCOTAS_ID + "="+id;

       SQLiteDatabase db = this.getWritableDatabase();
       Cursor registros = db.rawQuery(query, null);
       if (registros.moveToNext()){
           puntos = registros.getInt(0);
       }
       registros.close();
       db.close();
       return puntos;
   }

    public ArrayList<Mascota> obtenerMascotasFavoritas() {
        ArrayList<Mascota> mascotas = new ArrayList<>();
        String query = "SELECT "+ConstantesBaseDatos.TABLE_MASCOTAS_ID+", "
                +ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE+", "
                +ConstantesBaseDatos.TABLE_MASCOTAS_FOTO+", "
                +ConstantesBaseDatos.TABLE_MASCOTAS_PUNTOS
                +" FROM " + ConstantesBaseDatos.TABLE_MASCOTAS
                +" ORDER BY " + ConstantesBaseDatos.TABLE_MASCOTAS_PUNTOS+" DESC"
                +" LIMIT 5"
                ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));
            mascotaActual.setPuntos(registros.getInt(3));
            mascotas.add(mascotaActual);

        }
        registros.close();
        db.close();

        return mascotas;
    }
}
