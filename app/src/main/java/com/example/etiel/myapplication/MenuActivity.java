package com.example.etiel.myapplication;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.*;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;
public class MenuActivity extends AppCompatActivity implements ActionBar.TabListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    static Integer cod,cod1,cod2,cod3,cod4,cod5,cod6,cod7;
    String res;
    private static TextView Texto,ET_buscar;
    private static WebView wv_prod;
    private static Button bt_scan;
    private static ListView listado;
    private static CustomAdapter adapter;
    private static ArrayList<String> alistado;
    private static Context cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        alistado=new ArrayList<String>();
        cont=getApplicationContext();

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);



            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
        res="fragment_";
        cod1=getResources().getIdentifier(res+"qr","layout", getPackageName());
        cod2=getResources().getIdentifier(res+"filtrar","layout", getPackageName());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
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

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 7;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
                case 3:
                    return getString(R.string.title_section4).toUpperCase(l);
                case 4:
                    return getString(R.string.title_section5).toUpperCase(l);
                case 5:
                    return getString(R.string.title_section6).toUpperCase(l);
                case 6:
                    return getString(R.string.title_section7).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";



        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            Integer menu=getArguments().getInt(ARG_SECTION_NUMBER);

            switch (menu){
                case 1:
                    cod=cod1;

                    break;
                case 2:
                    cod=cod2;
                    break;
                default:
                    break;
            }
            View rootView = inflater.inflate(cod, container, false);
            switch (menu){
                case 1:
                    Texto=(TextView) rootView.findViewById(R.id.textView);//encontrar por Id. saber cual es el textview
                    wv_prod = (WebView) rootView.findViewById(R.id.wv_prod);

                    //Boton en el XML
                    bt_scan=(Button)rootView.findViewById(R.id.bt_scan);

                    //Añadimos Listener, al clickar...
                    bt_scan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Lanzamos la activity del escaner
                            IntentIntegrator.initiateScan(getActivity());
                        }
                    });
                    break;
                case 2:


                    listado = (ListView)rootView.findViewById(R.id.listView);
                    ET_buscar = (EditText) rootView.findViewById(R.id.ET_buscar);
                    ObtDatos(menu);
                    Log.d("pfin gen", "se genero");
                    ET_buscar.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            Integer textlength = ET_buscar.getText().length();
                            ArrayList<String> array_sort = new ArrayList<String>();

                            for (int i = 0; i < alistado.size(); i++) {
                                if (textlength <= alistado.get(i).length()) {
                                    if (ET_buscar.getText().toString().equalsIgnoreCase((String) alistado.get(i).subSequence(0, textlength))) {
                                        array_sort.add(alistado.get(i));
                                    }
                                }
                            }
                            adapter = //construimos un adapter de String
                                    new CustomAdapter(cont, array_sort);
                            listado.setAdapter(adapter);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    break;
                default:
                    break;
            }

            return rootView;
        }
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
                    Toast.makeText(getApplicationContext(), UPCScanned, Toast.LENGTH_LONG
                    ).show();
                }
                break;
            }
        }
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
    public static void ObtDatos(final int menu){
        AsyncHttpClient client = new AsyncHttpClient();
        String url="http://portalartesanias.ddns.net/consulta.php";

        RequestParams parametros = new RequestParams();
        //parametros.put("Edad",18);      //numero
        //parametros.put("Edad", "18");    //texto
        Log.d("obtdatos","l");
        client.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    //llamar a la funcion
                    Log.d("llamo la carga","l");
                    CargaLista(ObtDatosJSON(new String(responseBody)), menu);

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public static void CargaLista(ArrayList<Producto> datos,int menu){
        alistado.clear();
    for (int i=0;i<datos.size();i++) {
        alistado.add(datos.get(i).getName());
    }
        Log.d("pfin ho,ho,", alistado.get(1));
        switch (menu){
            case 2:
                adapter = //construimos un adapter de String
                        new CustomAdapter(cont,alistado);
                listado.setAdapter(adapter);
                break;
            default:
                break;
        }

        Log.d("pfin gen", "se actualizo");
        /*listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();
                //Intent i = new Intent(getApplicationContext(),Activity_shoppingcart.class);
                //i.putExtra("num1", num1);
                //startActivity(i);
            }
        });*/
        //cuando se va agregando texto vaya cambiando
        /*ET_buscar.addTextChangedListener(new TextWatcher() {
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
        });*/
    }


    public static ArrayList<Producto> ObtDatosJSON(String response){
        ArrayList mlistado= new ArrayList<Producto>();
        try{
            JSONArray jsonArray = new JSONArray(response);
            String texto;
            Producto p;
            for (int i=0;i<jsonArray.length();i++){
                //texto=jsonArray.getJSONObject(i).getString("name")+" "+
                  //      jsonArray.getJSONObject(i).getString("price");
                //mlistado.add(texto);
                p=new Producto(jsonArray.getJSONObject(i).getString("name"),jsonArray.getJSONObject(i).getString("price"),jsonArray.getJSONObject(i).getString("image_file_name"),jsonArray.getJSONObject(i).getString("id"));
                Log.d("pfin ho,ho", p.getName());
                mlistado.add(p);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return mlistado;
    }
}
