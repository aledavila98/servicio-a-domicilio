package com.supermercado_a_domicilio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Intent intent = new Intent(this, Login.class);

                    }
                }
        );
    }

    public void callSuper (View view){
        //Aqui llenan la lista de los SuperMercados obteniendo los datos de la base de datos, por mientras lo dejare con un par de superes
        if(SuperActivity.getTamList()>0) SuperActivity.clearList();

        SuperActivity.addList(new SuperModel("Junior","Bo El centro",R.drawable.sprjunior));
        SuperActivity.addList(new SuperModel("La Colonia","Bo El centro",R.drawable.sprlacolonia));
        SuperActivity.addList(new SuperModel("La Antorcha","Bo El centro",R.drawable.sprlaantorcha));
        Intent i = new Intent(MainActivity.this, SuperActivity.class);
        startActivity(i);
    }
}
