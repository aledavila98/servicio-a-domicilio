package com.supermercado_a_domicilio;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class SuperActivity extends AppCompatActivity{

    private String[] menuOptions = { "Ir a carretilla", "Supermercados", "Cerrar sesion" };;
    private ListView sideMenu;
    public static ArrayList<SuperModel> datos = new ArrayList<>();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch(item.getItemId()) {
            case R.id.carretilla:
               // DetalleCompraActivity.calcular();
                intent = new Intent(SuperActivity.this, DetalleCompraActivity.class);
                startActivity(intent);
                return(true);

            case R.id.exit:
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast toast = Toast.makeText(SuperActivity.this,"Sesion cerrada exitosamente",Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        });
                intent = new Intent(SuperActivity.this, Login.class);
                startActivity(intent);
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_super_mercados);
        this.setTitle(R.string.super_mercados);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


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

        private void llamarProductos() {
            //Aqui llenan la lista de los productos obteniendo los datos de la base de datos, por mientras lo dejare con un par de productos
            if(ProductsActivity.getTamList()>0) ProductsActivity.clearList();
            ProductsActivity.addList(new ProductModel("Coca Cola",50,"Tamaño 3 litros",R.drawable.cocacola));
            ProductsActivity.addList(new ProductModel("Pepsi",52,"Tamaño 3 litros",R.drawable.pepsi));
            ProductsActivity.addList(new ProductModel("Pollo entero (lb)", 36, "8 lb", R.drawable.polloentero));
            ProductsActivity.addList(new ProductModel("Maseca (lb)", 10, "1 lb", R.drawable.maseca));
            ProductsActivity.addList(new ProductModel("Chuleta de cerdo (lb)", 50, "Por unidad", R.drawable.chuletadecerdo));
            ProductsActivity.addList(new ProductModel("Arroz Mr. Dieck", 10, "3 lb", R.drawable.arroz));
            System.out.println("Entra");
            Intent i = new Intent(SuperActivity.this, ProductsActivity.class);
            startActivity(i);
            System.out.println("Entra");
        }


        public void callProducts (View view){
            llamarProductos();
        }


}