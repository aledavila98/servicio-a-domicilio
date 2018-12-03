package com.supermercado_a_domicilio;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class DetalleCompraActivity extends AppCompatActivity {

    public static ArrayList<DetalleCompra> detalles = new ArrayList<>();
    public static double total;
    public static TextView txtTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_compra);

        this.setTitle(R.string.detalle_compra);
        txtTotal = (TextView) findViewById(R.id.textViewTotal);

        TextView txtTime = (TextView) findViewById(R.id.textViewEntrega);
        txtTime.setText(" Tiempo de entrega: 1 h");
        ListView lista = findViewById(R.id.listDetalle);
        lista.setAdapter(new Lista_adaptador(this, R.layout.model_detalle, detalles){

            @Override
            public void onEntrada(final Object entrada, View view) {

                TextView txtSuper= view.findViewById(R.id.lblSUPER);
                TextView txtCantidad = view.findViewById(R.id.lblCANT);
                TextView txtDescripcion = view.findViewById(R.id.lblDESC);
                TextView txtImporte = view.findViewById(R.id.lblIMPORTE);
                txtSuper.setText(String.valueOf(((DetalleCompra) entrada).getSuperMercado()));
                txtCantidad.setText(((DetalleCompra) entrada).getCantidad());
                txtDescripcion.setText(((DetalleCompra) entrada).getProducto().getTitle() + ((DetalleCompra) entrada).getProducto().getDescription());
                double price = ((DetalleCompra) entrada).getProducto().getPrice();
                txtCantidad.setText(Double.toString((price)));
                double importe = price * ((DetalleCompra) entrada).getCantidad();
                txtImporte.setText(Double.toString(importe));




                /*checkA.setOnClickListener( new View.OnClickListener() {

                    public void onClick(View v) {
                        if (cantidad == 0) {
                            //  Toast toast= Toast.makeText()
                        }
                        CheckBox check = (CheckBox) v;
                        Toast toast = Toast.makeText(Adaptador.this, ((Lista_entrada) entrada).get_textoEncima() + " - Pulsado" + check.isChecked(), Toast.LENGTH_LONG);
                        toast.show();
                    }

                });*/
            }


        });
    }

    public static void addList(DetalleCompra d){
        detalles.add(d);
    }

    public static void clearList(){
        detalles.clear();
    }

    public static int getTamList(){return detalles.size();}

    public static void calcular(){
        total=0;
        for (DetalleCompra detalle:detalles) {
            total+= detalle.getCantidad() * detalle.getProducto().getPrice();
        }
        txtTotal.setText("Total a pagar: "+ total);
    }
}
