package com.example.etiel.myapplication;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;

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
    static Integer cod,cod1,cod2,cod3,cod4,cod5,cod6,cod7,cod8,cod9,cod10,cod11,codc;
    String res;
    private static TextView Texto,ET_buscar2,ET_buscar3,ET_buscar4,ET_buscar5,ET_buscar6,ET_buscar7,ET_buscar8,ET_buscar9,ET_buscar10,ET_buscar11,ET_buscarc;
    private static WebView wv_prod;
    private static Button bt_scan;
    public static ListView listado2,listado3,listado4,listado5,listado6,listado7,listado8,listado9,listado10,listado11,listadoc;
    private static CustomAdapter adapter;
    static CarritoAdapter adapterc;
    private static ArrayList<Producto> alistado2,alistado3,alistado4,alistado5,alistado6,alistado7,alistado8,alistado9,alistado10,alistado11;
    public static Producto qr;
    public static Context cont;
    static Dialog dialog = null;
    public static ArrayList<Producto> compras;
    private static View myview;
    static Integer pestaña;
    static IniciarSesion a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        myview=findViewById(R.id.pager);
        compras=new ArrayList<Producto>();


        alistado2=new ArrayList<Producto>();
        alistado3=new ArrayList<Producto>();
        alistado4=new ArrayList<Producto>();
        alistado5=new ArrayList<Producto>();
        alistado6=new ArrayList<Producto>();
        alistado7=new ArrayList<Producto>();
        alistado8=new ArrayList<Producto>();
        alistado9=new ArrayList<Producto>();
        alistado10=new ArrayList<Producto>();
        alistado11=new ArrayList<Producto>();
        cont=getApplicationContext();

        // Set up the action bar.
        final ActionBar actionBar = getSupportActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowHomeEnabled(true);

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
        cod3=getResources().getIdentifier(res+"filtrar","layout",getPackageName());
        cod4=getResources().getIdentifier(res+"filtrar","layout",getPackageName());
        cod5=getResources().getIdentifier(res+"filtrar","layout",getPackageName());
        cod6=getResources().getIdentifier(res+"filtrar","layout",getPackageName());
        cod7=getResources().getIdentifier(res+"filtrar","layout",getPackageName());
        cod8=getResources().getIdentifier(res+"filtrar","layout",getPackageName());
        cod9=getResources().getIdentifier(res+"filtrar","layout",getPackageName());
        cod10=getResources().getIdentifier(res+"filtrar","layout",getPackageName());
        cod11=getResources().getIdentifier(res+"filtrar","layout",getPackageName());
        codc=getResources().getIdentifier(res+"carrito","layout",getPackageName());
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
        pestaña=tab.getPosition();
        switch (pestaña) {
            case 11:
            MenuActivity.adapterc = //construimos un adapter de String
                    new CarritoAdapter(MenuActivity.cont, MenuActivity.compras);
            MenuActivity.listadoc.setAdapter(MenuActivity.adapterc);
                break;
        }
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
            return 12;
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
                case 7:
                    return getString(R.string.title_section8).toUpperCase(l);
                case 8:
                    return getString(R.string.title_section9).toUpperCase(l);
                case 9:
                    return getString(R.string.title_section10).toUpperCase(l);
                case 10:
                    return getString(R.string.title_section11).toUpperCase(l);
                case 11:
                    return getString(R.string.title_section12).toUpperCase(l);
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
                case 3:
                    cod=cod3;
                    break;
                case 4:
                    cod=cod4;
                    break;
                case 5:
                    cod=cod5;
                    break;
                case 6:
                    cod=cod6;
                    break;
                case 7:
                    cod=cod7;
                    break;
                case 8:
                    cod=cod8;
                    break;
                case 9:
                    cod=cod9;
                    break;
                case 10:
                    cod=cod10;
                    break;
                case 11:
                    cod=cod11;
                    break;
                case 12:
                    cod=codc;
                    break;
                default:
                    break;
            }
            final View rootView = inflater.inflate(cod, container, false);
            switch (menu){
                case 1:
                    final View a=rootView.findViewById(R.id.textView);
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
                    Texto.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            MenuActivity.buscarOnClick(a);

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            String id = "";
                            String txt = String.valueOf(Texto.getText());
                            String rev;
                            for (int i = txt.length(); i > (txt.length() - 4); i--) {
                                rev = String.valueOf(txt.subSequence(i - 1, i));
                                if (rev.equals("/")) {
                                    id = String.valueOf(txt.subSequence(i, txt.length()));
                                }
                            }
                            Snackbar.make(myview, id, Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            ObtDatos(id);
                        }
                    });
                    FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            boolean sw = false;
                            Producto producto=qr;
                            //Log.d("pfin carrito", Integer.toString(MenuActivity.compras.size()));
                            for (int i = 0; i < MenuActivity.compras.size(); i++) {
                                //Log.d("pfin carrito comp", MenuActivity.compras.get(i).getId());
                                //Log.d("pfin carrito prod", producto.getId());
                                if (MenuActivity.compras.get(i).getId().equals(producto.getId())) {
                                    MenuActivity.compras.get(i).plusQuantity(producto.getQuantity());
                                    sw = true;
                                    //Log.d("pfin carrito", Boolean.toString(sw));
                                }
                            }
                            if (!sw) {
                                MenuActivity.compras.add(producto);
                                //Log.d("pfin carrito", "se agrego al carrito");
                            }

                        }
                    });
                    break;
                case 2:
                    listado2 = (ListView)rootView.findViewById(R.id.listView);
                    ET_buscar2 = (EditText) rootView.findViewById(R.id.ET_buscar);
                    ObtDatos(menu);
                    //Log.d("pfin gen", "se genero");
                    ET_buscar2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                            Integer textlength = ET_buscar2.getText().length();
                            ArrayList<Producto> array_sort = new ArrayList<Producto>();

                            for (int i = 0; i < alistado2.size(); i++) {
                                if (textlength <= alistado2.get(i).getName().length()) {
                                    if (ET_buscar2.getText().toString().equalsIgnoreCase((String) alistado2.get(i).getName().subSequence(0, textlength))) {
                                        array_sort.add(alistado2.get(i));
                                    }
                                }
                            }
                            adapter = //construimos un adapter de String
                                    new CustomAdapter(cont, array_sort);
                            listado2.setAdapter(adapter);

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    break;
                case 3:
                    listado3 = (ListView)rootView.findViewById(R.id.listView);
                    ET_buscar3 = (EditText) rootView.findViewById(R.id.ET_buscar);
                    ObtDatos(menu);
                   // Log.d("pfin gen", "se genero");
                    ET_buscar3.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                            Integer textlength = ET_buscar3.getText().length();
                            ArrayList<Producto> array_sort = new ArrayList<Producto>();

                            for (int i = 0; i < alistado3.size(); i++) {
                                if (textlength <= alistado3.get(i).getName().length()) {
                                    if (ET_buscar3.getText().toString().equalsIgnoreCase((String) alistado3.get(i).getName().subSequence(0, textlength))) {
                                        array_sort.add(alistado3.get(i));
                                    }
                                }
                            }
                            adapter = //construimos un adapter de String
                                    new CustomAdapter(cont, array_sort);
                            listado3.setAdapter(adapter);

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    break;
                case 4:
                    listado4 = (ListView)rootView.findViewById(R.id.listView);
                    ET_buscar4 = (EditText) rootView.findViewById(R.id.ET_buscar);
                    ObtDatos(menu);
                    //Log.d("pfin gen", "se genero");
                    ET_buscar4.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                            Integer textlength = ET_buscar4.getText().length();
                            ArrayList<Producto> array_sort = new ArrayList<Producto>();

                            for (int i = 0; i < alistado4.size(); i++) {
                                if (textlength <= alistado4.get(i).getName().length()) {
                                    if (ET_buscar4.getText().toString().equalsIgnoreCase((String) alistado4.get(i).getName().subSequence(0, textlength))) {
                                        array_sort.add(alistado4.get(i));
                                    }
                                }
                            }
                            adapter = //construimos un adapter de String
                                    new CustomAdapter(cont, array_sort);
                            listado4.setAdapter(adapter);

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    break;
                case 5:
                    listado5 = (ListView)rootView.findViewById(R.id.listView);
                    ET_buscar5 = (EditText) rootView.findViewById(R.id.ET_buscar);
                    ObtDatos(menu);
                    //Log.d("pfin gen", "se genero");
                    ET_buscar5.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                            Integer textlength = ET_buscar5.getText().length();
                            ArrayList<Producto> array_sort = new ArrayList<Producto>();

                            for (int i = 0; i < alistado5.size(); i++) {
                                if (textlength <= alistado5.get(i).getName().length()) {
                                    if (ET_buscar5.getText().toString().equalsIgnoreCase((String) alistado5.get(i).getName().subSequence(0, textlength))) {
                                        array_sort.add(alistado5.get(i));
                                    }
                                }
                            }
                            adapter = //construimos un adapter de String
                                    new CustomAdapter(cont, array_sort);
                            listado5.setAdapter(adapter);

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    break;
                case 6:
                    listado6 = (ListView)rootView.findViewById(R.id.listView);
                    ET_buscar6 = (EditText) rootView.findViewById(R.id.ET_buscar);
                    ObtDatos(menu);
                    //Log.d("pfin gen", "se genero");
                    ET_buscar6.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                            Integer textlength = ET_buscar6.getText().length();
                            ArrayList<Producto> array_sort = new ArrayList<Producto>();

                            for (int i = 0; i < alistado6.size(); i++) {
                                if (textlength <= alistado6.get(i).getName().length()) {
                                    if (ET_buscar6.getText().toString().equalsIgnoreCase((String) alistado6.get(i).getName().subSequence(0, textlength))) {
                                        array_sort.add(alistado6.get(i));
                                    }
                                }
                            }
                            adapter = //construimos un adapter de String
                                    new CustomAdapter(cont, array_sort);
                            listado6.setAdapter(adapter);

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    break;
                case 7:
                    listado7 = (ListView)rootView.findViewById(R.id.listView);
                    ET_buscar7 = (EditText) rootView.findViewById(R.id.ET_buscar);
                    ObtDatos(menu);
                    //Log.d("pfin gen", "se genero");
                    ET_buscar7.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                            Integer textlength = ET_buscar7.getText().length();
                            ArrayList<Producto> array_sort = new ArrayList<Producto>();

                            for (int i = 0; i < alistado7.size(); i++) {
                                if (textlength <= alistado7.get(i).getName().length()) {
                                    if (ET_buscar7.getText().toString().equalsIgnoreCase((String) alistado7.get(i).getName().subSequence(0, textlength))) {
                                        array_sort.add(alistado7.get(i));
                                    }
                                }
                            }
                            adapter = //construimos un adapter de String
                                    new CustomAdapter(cont, array_sort);
                            listado7.setAdapter(adapter);

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    break;
                case 8:
                    listado8 = (ListView)rootView.findViewById(R.id.listView);
                    ET_buscar8 = (EditText) rootView.findViewById(R.id.ET_buscar);
                    ObtDatos(menu);
                    //Log.d("pfin gen", "se genero");
                    ET_buscar8.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                            Integer textlength = ET_buscar8.getText().length();
                            ArrayList<Producto> array_sort = new ArrayList<Producto>();

                            for (int i = 0; i < alistado8.size(); i++) {
                                if (textlength <= alistado8.get(i).getName().length()) {
                                    if (ET_buscar8.getText().toString().equalsIgnoreCase((String) alistado8.get(i).getName().subSequence(0, textlength))) {
                                        array_sort.add(alistado8.get(i));
                                    }
                                }
                            }
                            adapter = //construimos un adapter de String
                                    new CustomAdapter(cont, array_sort);
                            listado8.setAdapter(adapter);

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    break;
                case 9:
                    listado9 = (ListView)rootView.findViewById(R.id.listView);
                    ET_buscar9 = (EditText) rootView.findViewById(R.id.ET_buscar);
                    ObtDatos(menu);
                    //Log.d("pfin gen", "se genero");
                    ET_buscar9.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                            Integer textlength = ET_buscar9.getText().length();
                            ArrayList<Producto> array_sort = new ArrayList<Producto>();

                            for (int i = 0; i < alistado9.size(); i++) {
                                if (textlength <= alistado9.get(i).getName().length()) {
                                    if (ET_buscar9.getText().toString().equalsIgnoreCase((String) alistado9.get(i).getName().subSequence(0, textlength))) {
                                        array_sort.add(alistado9.get(i));
                                    }
                                }
                            }
                            adapter = //construimos un adapter de String
                                    new CustomAdapter(cont, array_sort);
                            listado9.setAdapter(adapter);

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    break;
                case 10:
                    listado10 = (ListView)rootView.findViewById(R.id.listView);
                    ET_buscar10 = (EditText) rootView.findViewById(R.id.ET_buscar);
                    ObtDatos(menu);
                    //Log.d("pfin gen", "se genero");
                    ET_buscar10.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                            Integer textlength = ET_buscar10.getText().length();
                            ArrayList<Producto> array_sort = new ArrayList<Producto>();

                            for (int i = 0; i < alistado10.size(); i++) {
                                if (textlength <= alistado10.get(i).getName().length()) {
                                    if (ET_buscar10.getText().toString().equalsIgnoreCase((String) alistado10.get(i).getName().subSequence(0, textlength))) {
                                        array_sort.add(alistado10.get(i));
                                    }
                                }
                            }
                            adapter = //construimos un adapter de String
                                    new CustomAdapter(cont, array_sort);
                            listado10.setAdapter(adapter);

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    break;
                case 11:
                    listado11 = (ListView)rootView.findViewById(R.id.listView);
                    ET_buscar11 = (EditText) rootView.findViewById(R.id.ET_buscar);
                    ObtDatos(menu);
                    //Log.d("pfin gen", "se genero");
                    ET_buscar11.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                            Integer textlength = ET_buscar11.getText().length();
                            ArrayList<Producto> array_sort = new ArrayList<Producto>();

                            for (int i = 0; i < alistado11.size(); i++) {
                                if (textlength <= alistado11.get(i).getName().length()) {
                                    if (ET_buscar11.getText().toString().equalsIgnoreCase((String) alistado11.get(i).getName().subSequence(0, textlength))) {
                                        array_sort.add(alistado11.get(i));
                                    }
                                }
                            }
                            adapter = //construimos un adapter de String
                                    new CustomAdapter(cont, array_sort);
                            listado11.setAdapter(adapter);

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                    break;
                case 12:
                    listadoc = (ListView)rootView.findViewById(R.id.listView);
                    FloatingActionButton comprar=(FloatingActionButton) rootView.findViewById(R.id.comprar);
                    //ObtDatos(menu);
                    comprar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Login(view, getActivity());


                        }
                    });
                    adapterc = //construimos un adapter de String
                            new CarritoAdapter(cont, compras);
                    listadoc.setAdapter(adapterc);
                    Log.d("pfin gen", "se genero");
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
    public static void buscarOnClick(View v){
        //la url no sea un string vacio
        if (Texto.getText().toString().length()== 0){
            Toast.makeText(cont, "Debe cargar una URL leida", Toast.LENGTH_SHORT).show();
        }else {
            WebSettings conf = wv_prod.getSettings();
            conf.setJavaScriptEnabled(true);
            //carga del url
            wv_prod.loadUrl(Texto.getText().toString());
        }
    }
    public static void ObtDatos(final int menu){
        AsyncHttpClient client = new AsyncHttpClient();
        String url="";
        switch (menu){
            case 2:
                url="http://portalartesanias.ddns.net/consulta.php";
                break;
            case 3:
                url="http://portalartesanias.ddns.net/madera.php";
                break;
            case 4:
                url="http://portalartesanias.ddns.net/bisuteria.php";
                break;
            case 5:
                url="http://portalartesanias.ddns.net/tejidos.php";
                break;
            case 6:
                url="http://portalartesanias.ddns.net/orfebreria.php";
                break;
            case 7:
                url="http://portalartesanias.ddns.net/ceramica.php";
                break;
            case 8:
                url="http://portalartesanias.ddns.net/porcelanicron.php";
                break;
            case 9:
                url="http://portalartesanias.ddns.net/pintura.php";
                break;
            case 10:
                url="http://portalartesanias.ddns.net/marroquineria.php";
                break;
            case 11:
                url="http://portalartesanias.ddns.net/tela.php";
                break;
            default:
            break;
        }


        RequestParams parametros = new RequestParams();
        //parametros.put("Edad",18);      //numero
        //parametros.put("Edad", "18");    //texto
        //Log.d("obtdatos", "l");
        client.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    //llamar a la funcion
                    //Log.d("llamo la carga", "l");
                    CargaLista(ObtDatosJSON(new String(responseBody)), menu);

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public static void ObtDatos(final String a){
        final Integer menu=1;
        AsyncHttpClient client = new AsyncHttpClient();
        String url="http://portalartesanias.ddns.net/modid.php";

        RequestParams parametros = new RequestParams();
        parametros.put("id",a);      //numero
        //parametros.put("Edad", "18");    //texto
        //Log.d("obtdatos", "l");
        client.post(url, parametros, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    //llamar a la funcion
                    //Log.d("llamo la carga", "l");
                    CargaLista(ObtDatosJSON(new String(responseBody)), menu);

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }
    public static void CargaLista(ArrayList<Producto> datos,int menu){

        switch (menu){

            case 1:
                qr=datos.get(0);
                qr.setQuantity(1);
                Integer j=0;
                String imagens=qr.getImage();
                String ids=qr.getId();
                String urls="https://s3.amazonaws.com/producpic/products/images/000/000/";
                //url https://s3.amazonaws.com/producpic/products/images/000/000/002/thumb/marimonda_carnaval.jpg
                urls=urls+String.format("%03d",Integer.parseInt(ids))+"/thumb/"+imagens;
                new DownloadImageTask(j,menu)
                        .execute(urls);

                break;
            case 2:
                alistado2=datos;
                //adapter = //construimos un adapter de String
                  //      new CustomAdapter(cont,alistado);
                //listado.setAdapter(adapter);

                for (int i = 0; i < alistado2.size(); i++){
                    String imagen=alistado2.get(i).getImage();
                    String id=alistado2.get(i).getId();
                    String url="https://s3.amazonaws.com/producpic/products/images/000/000/";
                    //url https://s3.amazonaws.com/producpic/products/images/000/000/002/thumb/marimonda_carnaval.jpg
                    url=url+String.format("%03d",Integer.parseInt(id))+"/thumb/"+imagen;
                    new DownloadImageTask(i,menu)
                       .execute(url);
                }

                break;
            case 3:
                alistado3=datos;
                //adapter = //construimos un adapter de String
                //      new CustomAdapter(cont,alistado);
                //listado.setAdapter(adapter);

                for (int i = 0; i < alistado3.size(); i++){
                    String imagen=alistado3.get(i).getImage();
                    String id=alistado3.get(i).getId();
                    String url="https://s3.amazonaws.com/producpic/products/images/000/000/";
                    //url https://s3.amazonaws.com/producpic/products/images/000/000/002/thumb/marimonda_carnaval.jpg
                    url=url+String.format("%03d",Integer.parseInt(id))+"/thumb/"+imagen;
                    new DownloadImageTask(i,menu)
                            .execute(url);
                }
                break;
            case 4:
                alistado4=datos;
                //adapter = //construimos un adapter de String
                //      new CustomAdapter(cont,alistado);
                //listado.setAdapter(adapter);

                for (int i = 0; i < alistado4.size(); i++){
                    String imagen=alistado4.get(i).getImage();
                    String id=alistado4.get(i).getId();
                    String url="https://s3.amazonaws.com/producpic/products/images/000/000/";
                    //url https://s3.amazonaws.com/producpic/products/images/000/000/002/thumb/marimonda_carnaval.jpg
                    url=url+String.format("%03d",Integer.parseInt(id))+"/thumb/"+imagen;
                    new DownloadImageTask(i,menu)
                            .execute(url);
                }
                break;
            case 5:
                alistado5=datos;
                //adapter = //construimos un adapter de String
                //      new CustomAdapter(cont,alistado);
                //listado.setAdapter(adapter);

                for (int i = 0; i < alistado5.size(); i++){
                    String imagen=alistado5.get(i).getImage();
                    String id=alistado5.get(i).getId();
                    String url="https://s3.amazonaws.com/producpic/products/images/000/000/";
                    //url https://s3.amazonaws.com/producpic/products/images/000/000/002/thumb/marimonda_carnaval.jpg
                    url=url+String.format("%03d",Integer.parseInt(id))+"/thumb/"+imagen;
                    new DownloadImageTask(i,menu)
                            .execute(url);
                }
                break;
            case 6:
                alistado6=datos;
                //adapter = //construimos un adapter de String
                //      new CustomAdapter(cont,alistado);
                //listado.setAdapter(adapter);

                for (int i = 0; i < alistado6.size(); i++){
                    String imagen=alistado6.get(i).getImage();
                    String id=alistado6.get(i).getId();
                    String url="https://s3.amazonaws.com/producpic/products/images/000/000/";
                    //url https://s3.amazonaws.com/producpic/products/images/000/000/002/thumb/marimonda_carnaval.jpg
                    url=url+String.format("%03d",Integer.parseInt(id))+"/thumb/"+imagen;
                    new DownloadImageTask(i,menu)
                            .execute(url);
                }
                break;
            case 7:
                alistado7=datos;
                //adapter = //construimos un adapter de String
                //      new CustomAdapter(cont,alistado);
                //listado.setAdapter(adapter);

                for (int i = 0; i < alistado7.size(); i++){
                    String imagen=alistado7.get(i).getImage();
                    String id=alistado7.get(i).getId();
                    String url="https://s3.amazonaws.com/producpic/products/images/000/000/";
                    //url https://s3.amazonaws.com/producpic/products/images/000/000/002/thumb/marimonda_carnaval.jpg
                    url=url+String.format("%03d",Integer.parseInt(id))+"/thumb/"+imagen;
                    new DownloadImageTask(i,menu)
                            .execute(url);
                }
                break;
            case 8:
                alistado8=datos;
                //adapter = //construimos un adapter de String
                //      new CustomAdapter(cont,alistado);
                //listado.setAdapter(adapter);

                for (int i = 0; i < alistado8.size(); i++){
                    String imagen=alistado8.get(i).getImage();
                    String id=alistado8.get(i).getId();
                    String url="https://s3.amazonaws.com/producpic/products/images/000/000/";
                    //url https://s3.amazonaws.com/producpic/products/images/000/000/002/thumb/marimonda_carnaval.jpg
                    url=url+String.format("%03d",Integer.parseInt(id))+"/thumb/"+imagen;
                    new DownloadImageTask(i,menu)
                            .execute(url);
                }
                break;
            case 9:
                alistado9=datos;
                //adapter = //construimos un adapter de String
                //      new CustomAdapter(cont,alistado);
                //listado.setAdapter(adapter);

                for (int i = 0; i < alistado9.size(); i++){
                    String imagen=alistado9.get(i).getImage();
                    String id=alistado9.get(i).getId();
                    String url="https://s3.amazonaws.com/producpic/products/images/000/000/";
                    //url https://s3.amazonaws.com/producpic/products/images/000/000/002/thumb/marimonda_carnaval.jpg
                    url=url+String.format("%03d",Integer.parseInt(id))+"/thumb/"+imagen;
                    new DownloadImageTask(i,menu)
                            .execute(url);
                }
                break;
            case 10:
                alistado10=datos;
                //adapter = //construimos un adapter de String
                //      new CustomAdapter(cont,alistado);
                //listado.setAdapter(adapter);

                for (int i = 0; i < alistado10.size(); i++){
                    String imagen=alistado10.get(i).getImage();
                    String id=alistado10.get(i).getId();
                    String url="https://s3.amazonaws.com/producpic/products/images/000/000/";
                    //url https://s3.amazonaws.com/producpic/products/images/000/000/002/thumb/marimonda_carnaval.jpg
                    url=url+String.format("%03d",Integer.parseInt(id))+"/thumb/"+imagen;
                    new DownloadImageTask(i,menu)
                            .execute(url);
                }
                break;
            case 11:
                alistado11=datos;
                //adapter = //construimos un adapter de String
                //      new CustomAdapter(cont,alistado);
                //listado.setAdapter(adapter);

                for (int i = 0; i < alistado11.size(); i++){
                    String imagen=alistado11.get(i).getImage();
                    String id=alistado11.get(i).getId();
                    String url="https://s3.amazonaws.com/producpic/products/images/000/000/";
                    //url https://s3.amazonaws.com/producpic/products/images/000/000/002/thumb/marimonda_carnaval.jpg
                    url=url+String.format("%03d",Integer.parseInt(id))+"/thumb/"+imagen;
                    new DownloadImageTask(i,menu)
                            .execute(url);
                }
                break;
            default:
                break;
        }

        //Log.d("pfin gen", "se actualizo");
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
                p=new Producto(jsonArray.getJSONObject(i).getString("name"),jsonArray.getJSONObject(i).getString("price"),jsonArray.getJSONObject(i).getString("image_file_name"),jsonArray.getJSONObject(i).getString("id"),jsonArray.getJSONObject(i).getString("description"));
                p.setQuantity(jsonArray.getJSONObject(i).getInt("cuantity"));
                //("pfin ho,ho", p.getName());
                mlistado.add(p);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return mlistado;
    }
    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        Integer n,menu;

        public DownloadImageTask(Integer n,Integer menu) {
            this.n=n;
            this.menu=menu;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            //bmImage.setImageBitmap(result);
            switch (menu) {
                case 1:
                    qr.setPic(result);
                    break;
                case 2:
                alistado2.get(n).setPic(result);
                if (n == (alistado2.size() - 1)) {
                    adapter = //construimos un adapter de String
                            new CustomAdapter(cont, alistado2);
                    listado2.setAdapter(adapter);
                }
                    break;
                case 3:
                    alistado3.get(n).setPic(result);
                    if (n == (alistado3.size() - 1)) {
                        adapter = //construimos un adapter de String
                                new CustomAdapter(cont, alistado3);
                        listado3.setAdapter(adapter);
                    }
                    break;
                case 4:
                    alistado4.get(n).setPic(result);
                    if (n == (alistado4.size() - 1)) {
                        adapter = //construimos un adapter de String
                                new CustomAdapter(cont, alistado4);
                        listado4.setAdapter(adapter);
                    }
                    break;
                case 5:
                    alistado5.get(n).setPic(result);
                    if (n == (alistado5.size() - 1)) {
                        adapter = //construimos un adapter de String
                                new CustomAdapter(cont, alistado5);
                        listado5.setAdapter(adapter);
                    }
                    break;
                case 6:
                    alistado6.get(n).setPic(result);
                    if (n == (alistado6.size() - 1)) {
                        adapter = //construimos un adapter de String
                                new CustomAdapter(cont, alistado6);
                        listado6.setAdapter(adapter);
                    }
                    break;
                case 7:
                    alistado7.get(n).setPic(result);
                    if (n == (alistado7.size() - 1)) {
                        adapter = //construimos un adapter de String
                                new CustomAdapter(cont, alistado7);
                        listado7.setAdapter(adapter);
                    }
                    break;
                case 8:
                    alistado8.get(n).setPic(result);
                    if (n == (alistado8.size() - 1)) {
                        adapter = //construimos un adapter de String
                                new CustomAdapter(cont, alistado8);
                        listado8.setAdapter(adapter);
                    }
                    break;
                case 9:
                    alistado9.get(n).setPic(result);
                    if (n == (alistado9.size() - 1)) {
                        adapter = //construimos un adapter de String
                                new CustomAdapter(cont, alistado9);
                        listado9.setAdapter(adapter);
                    }
                    break;
                case 10:
                    alistado10.get(n).setPic(result);
                    if (n == (alistado10.size() - 1)) {
                        adapter = //construimos un adapter de String
                                new CustomAdapter(cont, alistado10);
                        listado10.setAdapter(adapter);
                    }
                    break;
                case 11:
                    alistado11.get(n).setPic(result);
                    if (n == (alistado11.size() - 1)) {
                        adapter = //construimos un adapter de String
                                new CustomAdapter(cont, alistado11);
                        listado11.setAdapter(adapter);
                    }
                    break;
                default:
                    break;
            }
        }
    }
    public static void anuncio(Producto producto) {
        switch (pestaña) {
            case 11:

                break;
            default:
            Snackbar.make(myview, "Se ha agregado " + producto.getQuantity() + " " + producto.getName() + " al carrito", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
                break;
        }
    }
    public static class IniciarSesion extends AsyncTask<String, Void, String> {
        private ProgressDialog loadingDialog;
        private Context cont;
        private String user,password,id,cantidad;
        private Integer n;

        public IniciarSesion(Context cont,String user,String password,Integer n) {
            this.cont=cont;
            this.user=user;
            this.id=user;
            this.password=password;
            this.cantidad=password;
            this.n=n;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loadingDialog = new ProgressDialog(this.cont);
            switch (n) {
                case 0:
                loadingDialog.setTitle("Por favor espere");
                loadingDialog.setMessage("Iniciando sesión...");

                default:
                    loadingDialog.setTitle("Por favor espere");
                    loadingDialog.setMessage("Comprando articulos...");
                    break;
            }
            loadingDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            loadingDialog.setCancelable(false);
            loadingDialog.setButton("Cancelar", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    loadingDialog.cancel();
                    IniciarSesion.this.cancel(true);
                }
            });
            loadingDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            StringBuffer Respuesta = new StringBuffer();
            try{
                URL url;
                switch (n) {
                    case 0:
                        url = new URL("http://portalartesanias.ddns.net/login.php");
                        break;
                    default:
                        url = new URL("http://portalartesanias.ddns.net/compra.php");
                        break;
                }
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
                conexion.setReadTimeout(7000);
                conexion.setConnectTimeout(10000);
                conexion.setRequestMethod("POST");
                conexion.setDoInput(true);
                conexion.setDoOutput(true);
                Uri.Builder builder;
                switch (n) {
                    case 0:
                    builder = new Uri.Builder()
                            .appendQueryParameter("email", user)
                            .appendQueryParameter("contraseña", password);
                        break;
                    default:
                        builder = new Uri.Builder()
                                .appendQueryParameter("id", id)
                                .appendQueryParameter("cantidad", cantidad);
                        break;

                }
                String query = builder.build().getEncodedQuery();
                OutputStream Salida = conexion.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(Salida, "ISO-8859-1"));
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

        @Override
        protected void onCancelled() {
            super.onCancelled();
            loadingDialog.dismiss();

        }

        protected void onPostExecute(String result) {
            loadingDialog.dismiss();
            if(result==null){
            }else {
                String respPHP = result.trim().replaceAll("[^0-1]", "");
                Toast.makeText(MenuActivity.cont, result, Toast.LENGTH_LONG).show();
                if(respPHP.equalsIgnoreCase("1")){
                    Toast.makeText(MenuActivity.cont, "Exito", Toast.LENGTH_LONG).show();
                    switch (n){
                        default:
                            break;
                        case 0:
                            for (int i = 0; i < compras.size(); i++) {
                                String sid= compras.get(i).getId();
                                String scantidad=Integer.toString(compras.get(i).getQuantity());
                                a = new IniciarSesion(cont, sid, scantidad, i+1);
                                a.execute();
                            }
                            compras=new ArrayList<Producto>();
                            break;
                    }
                }else {
                    //Toast.makeText(MenuActivity.cont, "Usuario y/o contraseña incorrectos!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public static void Login(View view, final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater =(LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );;
        builder.setTitle("Valida tus datos para comprar");
        builder.setView(inflater.inflate(R.layout.dialog_signin, null));
        builder.setNegativeButton("Ahora no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        builder = builder.setPositiveButton("Validar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TextView user=(TextView)dialog.findViewById(R.id.username);
                TextView password=(TextView)dialog.findViewById(R.id.password);
                String tuser=user.getText().toString();
                String tpassword=password.getText().toString();
                //Log.d("Password",tpassword);

                a=new IniciarSesion(context,tuser,tpassword,0);
                a.execute();

                return;
            }
        });
        dialog = builder.create();

        dialog.show();

    }


}
