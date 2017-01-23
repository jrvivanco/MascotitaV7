package org.jrvivanco.mascotita.menu_opciones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import org.jrvivanco.mascotita.R;

public class AcercaDeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        /*Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarSub);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(AcercaDeActivity.this, "Mensaje de prueba", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        //quito imagen 5 estrellas
        //ImageView img5Stars= (ImageView) findViewById(R.id.imgFiveStarts);
        //img5Stars.setVisibility(View.INVISIBLE);
    }
}
