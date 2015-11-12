package com.example.etiel.myapplication;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.apache.http.client.ClientProtocolException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Etiel on 08/11/2015.
 *
 */

public class MostrarProd extends AppCompatActivity {
    private Button bt_mostrar;
    //private Button bt_volver;
    private TextView Nomprod;
    //String newString;//nombre de la variaBLE OBTENIDA

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_prod);
        bt_mostrar=(Button)findViewById(R.id.bt_mostrar);//boton en el xml
        //bt_volver=(Button)findViewById(R.id.bt_volver);
        //Bundle extras = getIntent().getExtras();
        //newString= extras.getString("Categoria_id");
        Nomprod=(TextView) findViewById(R.id.NomProd);
        //Añadimos Listener, al clickar...
        bt_mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new CargarProductos().execute();

            }
        });
    }
    public void Volver1OnClick(View v){
        super.onBackPressed();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mostrar_prod, menu);
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

//    public void cargarproductosOnClick(){
  //      new CargarProductos().execute();
//      }

    private class CargarProductos extends AsyncTask<String, Void, String> {

        private String Nombre1= "";

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... params) {
            StringBuffer Respuesta = new StringBuffer();
            try{
                URL url = new URL("http://portalartesanias.ddns.net/consulta.php");
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                conexion.setReadTimeout(7000);
                conexion.setConnectTimeout(10000);
                conexion.setRequestMethod("POST");
                conexion.setDoInput(true);
                conexion.setDoOutput(true);
                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter(" ", Nombre1);
                String query = builder.build().getEncodedQuery();
                OutputStream Salida = conexion.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Salida, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                Salida.close();
                conexion.connect();
                InputStream Entrada = conexion.getInputStream();
                BufferedReader bufferReader = new BufferedReader(new InputStreamReader(Entrada));
                String line;
                while((line = bufferReader.readLine()) != null) {
                    Respuesta.append(line);
                    Respuesta.append('\r');
                }
                bufferReader.close();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Respuesta.toString();
        }

        protected void onPostExecute(String result) {
            if(result==null) {

            }else {
                //String Resultado1 = result.replace(" ", "");
                ////////el error esta en esta linea
                //String Resultado2 = Resultado1.substring(0, Resultado1.indexOf('<'));//error
                //en esta linea esta el error
                //NombreSinEspacios = Resultado2.replace(" ", "");
                //Nombre = result.substring(0, result.indexOf('<'));
                //String[] Nombres = result.toString().split("-");  //ordenar en array y mostrar por columna
                //Nomprod.setText(Nombres[2]);                      //
                Nomprod.setText(result);
            }
        }
    }
}

