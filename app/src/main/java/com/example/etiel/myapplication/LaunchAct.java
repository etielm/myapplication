package com.example.etiel.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LaunchAct extends Activity {
    private Button bt_mdid;
    private Button bt_catg1;
    private Button bt_catg2;
    private Button bt_catg3;
    private Button bt_catg4;
    private Button bt_catg5;
    private Button bt_allcat;
    private Button bt_shcat;
    private TextView Texto;//definir textview
    //EditText ET_Num1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        //ET_Num1 = (EditText) findViewById((R.id.ET_Num1));
    }
    public void moduloOnClick(View v){//cargar modulo de identificacion
        //ET_Num1 = Integer.parseInt((ET_Num1.getText().toString()));
        Intent i = new Intent(this,MainActivity.class);
        //i.putExtra("Categoria_id", "5");
        startActivity(i);

    }

    public void shoppingcartOnClick(View v){//cargar carrito de compras
        //ET_Num1 = Integer.parseInt((ET_Num1.getText().toString()));
        Intent i = new Intent(this,Activity_shoppingcart.class);
        //i.putExtra("num1", num1);
        startActivity(i);

    }
    public void cargar_prodOnClick(View v){//cargar lista de productos tvodo en un string
        //ET_Num1 = Integer.parseInt((ET_Num1.getText().toString()));
        Intent i = new Intent(this,MostrarProd.class);
        //i.putExtra("num1", num1);
        startActivity(i);

    }
    public void filtrar_prodOnClick(View v){//cargar lista de productos en un Listview
        //ET_Num1 = Integer.parseInt((ET_Num1.getText().toString()));
        Intent i = new Intent(this,FiltrarActivity.class);
        //i.putExtra("num1", num1);
        startActivity(i);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
