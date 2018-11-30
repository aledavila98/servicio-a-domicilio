package com.supermercado_a_domicilio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetalleCompraActivity extends AppCompatActivity {

    public static ArrayList<DetalleCompra> detalles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_compra);

        this.setTitle(R.string.detalle_compra);



        ListView lista = (ListView) findViewById(R.id.listDetalle);
        lista.setAdapter(new Lista_adaptador(this, R.layout.model_detalle, detalles){

            @Override
            public void onEntrada(final Object entrada, View view) {
                TextView txtSuper= (TextView) view.findViewById(R.id.lblSUPER);
                txtSuper.setText(String.valueOf(((DetalleCompra) entrada).getSuperMercado()));

                TextView txtCantidad = (TextView) view.findViewById(R.id.lblCANT);
                txtCantidad.setText(((DetalleCompra) entrada).getCantidad());

                TextView txtDescripcion = (TextView) view.findViewById(R.id.lblDESC);
                txtDescripcion.setText(((DetalleCompra) entrada).getProducto().getTitle() + ((DetalleCompra) entrada).getProducto().getDescription() );

                TextView txtPrecio = (TextView) view.findViewById(R.id.lblPRICE);
                double price =((DetalleCompra) entrada).getProducto().getPrice();
                txtCantidad.setText(Double.toString((price)));

                TextView txtImporte = (TextView) view.findViewById(R.id.lblIMPORTE);
                double importe = price * ((DetalleCompra)entrada).getCantidad();
                txtCantidad.setText(Double.toString(importe));



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


}
