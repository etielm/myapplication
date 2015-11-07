package com.example.etiel.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.*;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class FiltrarActivity extends AppCompatActivity {
    private ListView listado;
    private EditText ET_buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtrar);
        listado = (ListView)findViewById(R.id.listView);
        ET_buscar = (EditText) findViewById(R.id.ET_buscar);

        ObtDatos();
    }

    public void ObtDatos(){
        AsyncHttpClient client = new AsyncHttpClient();
        String url="http://portalartesanias.ddns.net/consulta.php";

        RequestParams parametros = new RequestParams();
        //parametros.put("Edad",18);      //numero
        //parametros.put("Edad", "18");    //texto

        client.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    //llamar a la funcion
                    CargaLista(ObtDatosJSON(new String (responseBody)));

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public void CargaLista(ArrayList<String> datos){
        final ArrayAdapter<String> adapter = //construimos un adapter de String
                new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datos);
        listado.setAdapter(adapter); //seteamos el adapter
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),Activity_shoppingcart.class);
                //i.putExtra("num1", num1);
                startActivity(i);
            }
        });
        //cuando se va agregando texto vaya cambiando
        ET_buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public ArrayList<String> ObtDatosJSON(String response){
        ArrayList<String> listado= new ArrayList<String>();
        try{
            JSONArray jsonArray = new JSONArray(response);
            String texto;
            for (int i=0;i<jsonArray.length();i++){
                texto=jsonArray.getJSONObject(i).getString("name")+" "+
                         jsonArray.getJSONObject(i).getString("description");
                listado.add(texto);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return listado;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_filtrar, menu);
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
