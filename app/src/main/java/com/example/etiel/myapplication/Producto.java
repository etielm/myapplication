package com.example.etiel.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * Created by Etiel on 08/11/2015.
 */
public class Producto implements Parcelable{
    private String name,price,image,id;
    private Bitmap pic;
    private int quantity=0;

    public Producto(String nombre,String precio,String imagen,String id){
        this.name=nombre;
        this.price=precio;
        this.image=imagen;
        this.id=id;
    }

    public String getName(){
        return this.name;
    }
    public String getPrice(){
        return this.price;
    }
    public String getImage(){
        return this.image;
    }
    public Bitmap getPic(){return this.pic;}
    public String getId(){return this.id;}
    public void setPic(Bitmap pic){this.pic=pic;}
    public void plusQuantity(int cantidad){this.quantity=(this.quantity+cantidad);}

    public void lessQuantity(int cantidad) {
        if (this.quantity > 0) {
            this.quantity = (this.quantity - cantidad);
        }
    }

    public Producto(Parcel in){
        String[] data= new String[6];

        in.readStringArray(data);
        this.name= data[0];
        this.price= data[1];
        this.image= data[2];
        this.id=data[3];
        byte [] encodeByte= Base64.decode(data[4], Base64.DEFAULT);
        Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        this.pic=bitmap;
        this.quantity=Integer.parseInt(data[5]);
    }
    @Override
    public int describeContents() {
// TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
// TODO Auto-generated method stub
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        this.pic.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp=Base64.encodeToString(b, Base64.DEFAULT);
        dest.writeStringArray(new String[]{this.name,this.price,this.image,this.id, temp,String.valueOf(this.quantity)});
    }

    public static final Parcelable.Creator<Producto> CREATOR= new Parcelable.Creator<Producto>() {

        @Override
        public Producto createFromParcel(Parcel source) {
// TODO Auto-generated method stub
            return new Producto(source);  //using parcelable constructor
        }

        @Override
        public Producto[] newArray(int size) {
// TODO Auto-generated method stub
            return new Producto[size];
        }
    };
}
