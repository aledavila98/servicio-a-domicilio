package com.supermercado_a_domicilio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class DetalleCompraActivity extends AppCompatActivity {

    public static ArrayList<DetalleCompra> detalle = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_compra);
    }

    public static void addList(DetalleCompra d){
        detalle.add(d);
    }

    public static void clearList(){
        detalle.clear();
    }

    public static int getTamList(){return detalle.size();}


}
