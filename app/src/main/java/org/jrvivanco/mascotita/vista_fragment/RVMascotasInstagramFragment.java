package org.jrvivanco.mascotita.vista_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.jrvivanco.mascotita.MainActivity;
import org.jrvivanco.mascotita.R;
import org.jrvivanco.mascotita.adapter.MascotaInstagramAdaptador;
import org.jrvivanco.mascotita.pojo.MascotaInst;
import org.jrvivanco.mascotita.pojo.UsuarioInst;
import org.jrvivanco.mascotita.presentador.IRVMascotasInstagramFragmentPresenter;
import org.jrvivanco.mascotita.presentador.RVMascotasInstagramFragmentPresenter;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jrvivanco on 21/01/2017.
 */
public class RVMascotasInstagramFragment extends Fragment implements IRVMascotasInstagramFragmentView {
    ArrayList<MascotaInst> mascotasInst;
    private RecyclerView rvMascotasInst;
    private IRVMascotasInstagramFragmentPresenter presentador;

    private CircularImageView circularImageView;
    private TextView tvNombre ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mascota, container,false);

        //Para recoger el usuario de la actividad
        MainActivity actividad = (MainActivity) getActivity();
        //Toast.makeText(getContext(), "El usuario dentro fragment es "+actividad.getUsuarioID(), Toast.LENGTH_LONG).show();

        circularImageView = (CircularImageView) v.findViewById(R.id.circularImageView);
        circularImageView.setBorderWidth(5 * (int) getResources().getDisplayMetrics().density);
        circularImageView.setShadowRadius(0);
        //circularImageView.setBorderColor();

        tvNombre = (TextView) v.findViewById(R.id.tvNombreMascotaInst);
        //nombre Completo del perfil de usuario
        tvNombre.setText(actividad.getNomUsuario());
        //imagen
        Picasso.with(actividad)
                .load(actividad.getUrlUsuario())
                .placeholder(R.drawable.dog)
                .into(circularImageView);

        rvMascotasInst= (RecyclerView) v.findViewById(R.id.rvMascotaInst);
        presentador = new RVMascotasInstagramFragmentPresenter(this,getContext(),actividad.getUsuarioID());
        return v;
    }

    @Override
    public void generarGridLayout() {
        GridLayoutManager lm = new GridLayoutManager(getContext(),3);
        rvMascotasInst.setLayoutManager(lm);
    }

    @Override
    public MascotaInstagramAdaptador crearAdaptador(ArrayList<MascotaInst> mascotasInst) {
        //crea objeto mascotaAdaptador para que reciba la lista y que pueda hacer lo configurado
        MascotaInstagramAdaptador adaptadorInst = new MascotaInstagramAdaptador(mascotasInst,getActivity());
        return adaptadorInst;
    }

    @Override
    public void inicializarAdaptadorInstRecyclerView(MascotaInstagramAdaptador adaptador) {
        rvMascotasInst.setAdapter(adaptador);
    }

    @Override
    public UsuarioInst obtenerDatosUsuario(String nomUsuario) {
        return null;
    }


}
