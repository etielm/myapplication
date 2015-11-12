package com.example.etiel.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DescripcionActivity extends AppCompatActivity {

    Producto producto;
    TextView titulo,descripcion,precio,quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descripcion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        producto = getIntent().getParcelableExtra("userTag");
        producto.setQuantity(1);
        titulo=(TextView)findViewById(R.id.titulo);
        ImageView imagen= (ImageView)findViewById(R.id.imagen);
        descripcion=(TextView)findViewById(R.id.descripcion);
        precio=(TextView)findViewById(R.id.precio);
        quantity=(TextView) findViewById(R.id.quantity);
        Button bmas=(Button) findViewById(R.id.bmas);
        Button bmenos=(Button) findViewById(R.id.bmenos);
        titulo.setText(producto.getName());
        imagen.setImageBitmap(producto.getPic());
        precio.setText("$" + producto.getPrice());
        quantity.setText(Integer.toString(producto.getQuantity()));
        descripcion.setText(producto.getDescription());
        bmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                producto.plusQuantity(1);
                quantity.setText(Integer.toString(producto.getQuantity()));
                precio.setText("$"+Integer.toString(Integer.parseInt(producto.getPrice())*producto.getQuantity()));
            }
        });
        bmenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                producto.lessQuantity(1);
                quantity.setText(Integer.toString(producto.getQuantity()));
                precio.setText("$" + Integer.toString(Integer.parseInt(producto.getPrice()) * producto.getQuantity()));
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean sw = false;
                Integer j=0;
                Log.d("pfin carrito", Integer.toString(MenuActivity.compras.size()));
                for (int i = 0; i < MenuActivity.compras.size(); i++) {
                    Log.d("pfin carrito comp", MenuActivity.compras.get(i).getId());
                    Log.d("pfin carrito prod", producto.getId());
                    if (MenuActivity.compras.get(i).getId().equals(producto.getId())) {
                        switch (MenuActivity.pestaña) {
                            case 11:
                                MenuActivity.compras.get(i).setQuantity(producto.getQuantity());
                                if(MenuActivity.compras.get(i).getQuantity().equals(0)) {
                                    MenuActivity.compras.remove(i);
                                }
                                break;
                            default:
                        MenuActivity.compras.get(i).plusQuantity(producto.getQuantity());
                                break;
                        }
                        sw = true;
                        Log.d("pfin carrito", Boolean.toString(sw));
                    }
                }

                if (!sw) {
                    MenuActivity.compras.add(producto);
                    Log.d("pfin carrito", "se agrego al carrito");
                }


                Handler del = new Handler();
                del.postDelayed(new Runnable() {
                    public void run() {
                        MenuActivity.anuncio(producto);
                    }
                }, 600);
                finish();

            }
        });

    }

}
