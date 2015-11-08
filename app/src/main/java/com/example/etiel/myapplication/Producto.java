package com.example.etiel.myapplication;

import java.util.List;

/**
 * Created by Etiel on 08/11/2015.
 */
public class Producto {
    private String name,price,image,id;

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
    public String getId(){
        return this.id;
    }
}
