package com.supermercado_a_domicilio;

public class SuperModel {
    public static  String superActual;
    private String title;
    private String direccion;
    private int imageId;

    public SuperModel(String title,String dir,int image){
        this.title=title;
        this.direccion=dir;
        this.imageId=image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

}
