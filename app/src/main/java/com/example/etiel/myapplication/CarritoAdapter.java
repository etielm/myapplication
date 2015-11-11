package com.example.etiel.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by Etiel on 08/11/2015.
 */
public class CarritoAdapter extends BaseAdapter {

    private static final String TAG="AS_ListView";
    private Context context;
    private List<Producto> values;
    private final Object mLock = new Object();



    public CarritoAdapter(Context context, List<Producto> values){
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
    public View getView(final int position, View view, ViewGroup parent) {
        String nombre=values.get(position).getName();
        String precio=(Integer.toString(Integer.parseInt(values.get(position).getPrice()) * values.get(position).getQuantity()));
        String imagen=values.get(position).getImage();
        String id=values.get(position).getId();
        Bitmap snap=values.get(position).getPic();
        Integer cantidad=values.get(position).getQuantity();
        if(view==null){
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(R.layout.my_carrito_item,null);
        }
        URL imageUrl = null;

        TextView texto=(TextView) view.findViewById(R.id.dispres);
        ImageView pic=(ImageView) view.findViewById(R.id.pic);
        pic.setImageBitmap(snap);
        Integer a=view.getResources().getIdentifier("pic", "id", MenuActivity.class.getPackage().getName());
        Log.d("pfin yeha",Integer.toString(a));
        texto.setText(nombre +"\nCant:"+Integer.toString(cantidad)+"\n$" + precio);
        //pic.setImageResource(R.drawable.abc_btn_check_material);
        view.setTag(nombre);
        String url="https://s3.amazonaws.com/producpic/products/images/000/000/";
        //url https://s3.amazonaws.com/producpic/products/images/000/000/002/thumb/marimonda_carnaval.jpg
        url=url+String.format("%03d",Integer.parseInt(id))+"/thumb/"+imagen;
       // new DownloadImageTask(pic)
         //       .execute(url);
        texto.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, DescripcionActivity.class);
                intent.putExtra("userTag",values.get(position));
                Log.d("Desarrollo", "Se abrio la descripcion");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        return view;
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        Integer n;

        public DownloadImageTask(ImageView bmImage,Integer n) {
            this.bmImage = bmImage;
            this.n=n;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new URL(urldisplay).openStream();
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