package com.example.etiel.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Etiel on 08/11/2015.
 */
public class CustomAdapter extends BaseAdapter {

    private static final String TAG="AS_ListView";
    private Context context;
    private List<Producto> values;
    private final Object mLock = new Object();


    public CustomAdapter(Context context,List<Producto> values){
        this.context= context;
        this.values=values;
    }



    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        String nombre=values.get(position).getName();
        String precio=values.get(position).getPrice();
        String imagen=values.get(position).getImage();
        String id=values.get(position).getId();
        if(view==null){
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.my_list_item,null);
        }
        URL imageUrl = null;

        TextView texto=(TextView) view.findViewById(R.id.dispres);
        ImageView pic=(ImageView) view.findViewById(R.id.pic);
        texto.setText(nombre + "\n" + precio);
        //pic.setImageResource(R.drawable.abc_btn_check_material);
        view.setTag(nombre);
        String url="https://s3.amazonaws.com/producpic/products/images/000/000/";
        url=url+String.format("%03d",Integer.parseInt(id))+"/thumb/"+imagen;
        new DownloadImageTask(pic)
                .execute(url);
        return view;
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
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
            bmImage.setImageBitmap(result);
        }
    }
}