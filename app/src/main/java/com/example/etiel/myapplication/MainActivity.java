package com.example.etiel.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends Activity {
    //declaramos nuestro boton
    private Button bt_scan;
    private TextView Texto;//definir textview
    //declaramos nuestro webview
    WebView wv_prod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Texto=(TextView) findViewById(R.id.textView);//encontrar por Id. saber cual es el textview
        wv_prod = (WebView) findViewById(R.id.wv_prod);

        //Boton en el XML
        bt_scan=(Button)findViewById(R.id.bt_scan);

        //Añadimos Listener, al clickar...
        bt_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Lanzamos la activity del escaner
                IntentIntegrator.initiateScan(MainActivity.this);
            }
        });
    }
    //metodo boton buscar web view
    public void buscarOnClick(View v){
        //la url no sea un string vacio
        if (Texto.getText().toString().length()== 0){
            Toast.makeText(this, "Debe cargar una URL leida", Toast.LENGTH_SHORT).show();
        }else {
            WebSettings conf = wv_prod.getSettings();
            conf.setJavaScriptEnabled(true);
            //carga del url
            wv_prod.loadUrl(Texto.getText().toString());
        }
    }
    //boton volver
    public void VolverOnClick(View v){
        super.onBackPressed();


    }


    //Marcamos lo que queremos que haga una vez haya leido el código
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case IntentIntegrator.REQUEST_CODE:
            {
                if (resultCode == RESULT_CANCELED){
                }
                else
                {
                    //Recogemos los datos   que nos envio el código Qr/codigo de barras
                    IntentResult scanResult = IntentIntegrator.parseActivityResult(
                            requestCode, resultCode, data);
                    String UPCScanned = scanResult.getContents();
                    //COMO ES SOLO UN EJEMPLO LO SACAREMOS POR PANTALLA.

                    Texto.setText(UPCScanned);//agregado por chan. mostrar pantalla
                    Toast.makeText(getApplicationContext(),UPCScanned,Toast.LENGTH_LONG
                    ).show();
                }
                break;
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Toast.makeText(this, "creado por Etielm", Toast.LENGTH_LONG).show();
        }
        if (id == R.id.action_borrard) {
            Texto.setText("");
            wv_prod.loadUrl("about:blank");
        }

        return super.onOptionsItemSelected(item);
    }
}

