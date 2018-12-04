package com.supermercado_a_domicilio;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    public static ArrayList<ProductModel> datos = new ArrayList<>();
    public  int cantidad=0;

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
                DetalleCompraActivity.calcular();
                intent = new Intent(ProductsActivity.this, DetalleCompraActivity.class);
                startActivity(intent);
                return(true);

            case R.id.exit:
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast toast = Toast.makeText(ProductsActivity.this,"Sesion cerrada exitosamente",Toast.LENGTH_SHORT);
                                toast.show();
                            }
                        });
                intent = new Intent(ProductsActivity.this, Login.class);
                startActivity(intent);
                return(true);
        }
        return(super.onOptionsItemSelected(item));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        this.setTitle(SuperModel.superActual);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ListView lista = findViewById(R.id.ListView_products);
        lista.setAdapter(new Lista_adaptador(this, R.layout.model_product, datos) {

            @Override
            public void onEntrada(final Object entrada, View view) {

                TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView_superior);
                texto_superior_entrada.setText(((ProductModel) entrada).getTitle());

                TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textView_inferior);
                String descripcion = ((ProductModel)entrada).getDescription()+ "\n"+ "Precio: "+((ProductModel)entrada).getPrice() + " LPS";
                texto_inferior_entrada.setText(descripcion);

                ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen);
                imagen_entrada.setImageResource(((ProductModel) entrada).getImage());

                Button carrito= (Button) view.findViewById(R.id.btn_addCarrito);

                final EditText textCant= (EditText) view.findViewById(R.id.txtCantidad);

                textCant.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        cantidad=0;
                        cantidad=Integer.valueOf(editable.toString());

                    }
                });

                carrito.setOnClickListener(new View.OnClickListener() {


                    public void onClick(View v) {
                        Toast toast;
                        if(cantidad==0){
                            toast = Toast.makeText(ProductsActivity.this, "Porfavor ingrese una cantidad valida", Toast.LENGTH_LONG);
                        }
                        else{
                            addToCar(cantidad,((ProductModel)entrada));
                            toast = Toast.makeText(ProductsActivity.this, "Se ha añadido el producto "+ ((ProductModel) entrada).getTitle() +
                                    " con el precio de "+ ((ProductModel) entrada).getPrice() + " y cantidad de "+cantidad, Toast.LENGTH_LONG);
                            cantidad=0;
                        }
                        toast.show();
                    }

                });
            }


        });
    }

        public static void addList(ProductModel p){
            datos.add(p);
        }

        public static void clearList(){
            datos.clear();
        }

        public static int getTamList(){return datos.size();}


        public void addToCar(int cant, ProductModel p) {
            DetalleCompraActivity.addList(new DetalleCompra(SuperModel.superActual,p,cant));
        }
}
