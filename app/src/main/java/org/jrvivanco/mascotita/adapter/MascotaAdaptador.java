package org.jrvivanco.mascotita.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.jrvivanco.mascotita.db.ConstructorMascotas;
import org.jrvivanco.mascotita.pojo.Mascota;
import org.jrvivanco.mascotita.R;

import java.util.ArrayList;


/**
 * Created by jrvivanco on 21/01/2017.
 */
public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder>{

    ArrayList<Mascota> mascotas;
    Activity actividad;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity actividad){
        //contructor para pasarle la lista de mascotas
        this.mascotas=mascotas;
        this.actividad=actividad;
    }


    // /mantengo el constructor antiguo para las MAscotasFav
    public MascotaAdaptador(ArrayList<Mascota> mascotas){
        //contructor para pasarle la lista de mascotas
        this.mascotas=mascotas;

    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Infla nuestro layout cardview y lo pasa al ViewHolder para que obtenga cada elemento (los views)
        View v;
        /*if(actividad != null){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota_fav,parent,false);
        }else{*/
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota,parent,false);
        //}

        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position) {
        //Asocia cada elemento de la lista con cada view
        final Mascota mascota = mascotas.get(position);

        mascotaViewHolder.imgFotoCV.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvNombreCV.setText(mascota.getNombre());
        mascotaViewHolder.tvPuntosCV.setText(String.valueOf(mascota.getPuntos()));

        mascotaViewHolder.btPuntos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(actividad, "Diste like a "+mascota.getNombre(), Toast.LENGTH_SHORT).show();

                //actualiza en bbdd
                ConstructorMascotas constructorMascotas = new ConstructorMascotas(actividad);
                constructorMascotas.darPuntoMascota(mascota);
                //refresca view
                mascotaViewHolder.tvPuntosCV.setText(String.valueOf(constructorMascotas.obtenerPuntosMascota(mascota)));
            }
        });
        //si no se ha pasado actividad, es porque estoy en Favoritos, así que no muestro el hueso para aumentar puntuación
        if(actividad == null){
            mascotaViewHolder.btPuntos.setVisibility(View.INVISIBLE);
        }



    }

    @Override
    public int getItemCount() { //Cantidad de elementos que contiene mi lista
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFotoCV;
        private TextView tvNombreCV;
        private TextView tvPuntosCV;
        private ImageButton btPuntos;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFotoCV   =(ImageView) itemView.findViewById(R.id.imgFotoCV);
            tvNombreCV  =(TextView) itemView.findViewById(R.id.tvNombreCV);
            tvPuntosCV  =(TextView) itemView.findViewById(R.id.tvPuntosCV);
            btPuntos = (ImageButton) itemView.findViewById(R.id.imgHuesoCV);

        }
    }
}
