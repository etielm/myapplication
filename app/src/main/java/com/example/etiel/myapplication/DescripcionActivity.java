package com.example.etiel.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DescripcionActivity extends AppCompatActivity {

    Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        producto = getIntent().getParcelableExtra("userTag");
        TextView titulo=(TextView)findViewById(R.id.titulo);
        ImageView imagen= (ImageView)findViewById(R.id.imagen);
        TextView decripcion=(TextView)findViewById(R.id.descripcion);
        TextView precio=(TextView)findViewById(R.id.precio);
        titulo.setText(producto.getName());
        imagen.setImageBitmap(producto.getPic());
        precio.setText(producto.getPrice());



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
