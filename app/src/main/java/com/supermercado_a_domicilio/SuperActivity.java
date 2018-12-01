package com.supermercado_a_domicilio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SuperActivity extends AppCompatActivity{

    private String[] menuOptions = { "Ir a carretilla", "Supermercados", "Cerrar sesion" };;
    private ListView sideMenu;
    public static ArrayList<SuperModel> datos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_super_mercados);
        this.setTitle(R.string.super_mercados);

     /*   if (savedInstanceState == null) {
            Carretilla fragmentCarretilla = (Carretilla)
                    getSupportFragmentManager().findFragmentById(R.id.carretillaFragment);
        }

*/
        ListView lista = findViewById(R.id.ListView_listado);
        //sideMenu = findViewById(R.id.left_drawer);

        //sideMenu.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menuOptions));
        lista.setAdapter(new Lista_adaptador(this, R.layout.model_super, datos) {

            @Override
            public void onEntrada(final Object entrada, View view) {


                TextView titulo = view.findViewById(R.id.textView_titulo);
                titulo.setText(((SuperModel) entrada).getTitle());

                TextView direccion = view.findViewById(R.id.textView_direccion);
                direccion.setText(((SuperModel) entrada).getDireccion());

                ImageView imagen = view.findViewById(R.id.imageView_imagen);
                imagen.setImageResource(((SuperModel) entrada).getImageId());

                Button ir = view.findViewById(R.id.button_ir);

                ir.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        System.out.println("Entra");
                        SuperModel.superActual=((SuperModel)entrada).getTitle();

                        callProducts(v);
                    }
                });

                    }

        });
    }
        public static void addList (SuperModel s){
            datos.add(s);
        }

        public static void clearList () {
            datos.clear();
        }

        public static int getTamList () {
            return datos.size();
        }


        public void callProducts (View view){
            //Aqui llenan la lista de los productos obteniendo los datos de la base de datos, por mientras lo dejare con un par de productos
            if(ProductsActivity.getTamList()>0) ProductsActivity.clearList();
            ProductsActivity.addList(new ProductModel("Coca Cola",50,"Tamaño 3 litros",R.drawable.cocacola));
            ProductsActivity.addList(new ProductModel("Pepsi",52,"Tamaño 3 litros",R.drawable.pepsi));
            System.out.println("Entra");
            Intent i = new Intent(SuperActivity.this, ProductsActivity.class);
            startActivity(i);
            System.out.println("Entra");
        }


}