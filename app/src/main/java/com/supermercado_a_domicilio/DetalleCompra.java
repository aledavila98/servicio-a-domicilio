package com.supermercado_a_domicilio;

public class DetalleCompra {
    private String superMercado;
    private ProductModel producto;
    private int cantidad;

    public DetalleCompra(String superMercado, ProductModel p,int c){
        this.cantidad=c;
        this.producto=p;
        this.superMercado=superMercado;
    }

    public String getSuperMercado() {
        return superMercado;
    }

    public void setSuperMercado(String superMercado) {
        this.superMercado = superMercado;
    }

    public ProductModel getProducto() {
        return producto;
    }

    public void setProducto(ProductModel producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
