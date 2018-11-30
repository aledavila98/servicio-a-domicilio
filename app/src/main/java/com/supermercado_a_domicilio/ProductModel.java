package com.supermercado_a_domicilio;

public class ProductModel {

    private String title;
    private double price;
    private String description;
    private int image;


    public ProductModel(String t,double p, String d,int i){
        this.title=t;
        this.description=d;
        this.price=p;
        this.image=i;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
