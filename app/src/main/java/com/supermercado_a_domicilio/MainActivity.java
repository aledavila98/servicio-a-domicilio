package com.supermercado_a_domicilio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callSuper (View view){
        //Aqui llenan la lista de los SuperMercados obteniendo los datos de la base de datos, por mientras lo dejare con un par de superes
        if(SuperActivity.getTamList()>0) SuperActivity.clearList();

        SuperActivity.addList(new SuperModel("Super mercado Junior","Bo El centro",R.drawable.sprjunior));
        SuperActivity.addList(new SuperModel("Super mercado La Colonia","Bo El centro",R.drawable.sprlacolonia));
        SuperActivity.addList(new SuperModel("Super mercado La Antorcha","Bo El centro",R.drawable.sprlaantorcha));
        Intent i = new Intent(this, SuperActivity.class);
        startActivity(i);
    }
}
