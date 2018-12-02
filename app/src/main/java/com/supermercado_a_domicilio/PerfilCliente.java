package com.supermercado_a_domicilio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PerfilCliente extends AppCompatActivity {

    private TextView nombre, id, correo, telefono, direccion;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("users-data");
    private final String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_cliente);

        nombre = findViewById(R.id.tbName);
        id = findViewById(R.id.tbId);
        correo = findViewById(R.id.tbCorreo);
        direccion = findViewById(R.id.tbDireccion);

    }

    protected void onStart() {
        super.onStart();
        jalarDatos();
    }

    private void jalarDatos(){
        myRef.child(getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String user = (String) dataSnapshot.child("nombre").getValue();
                nombre.setText(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private String getId() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String id = user.getDisplayName();
            return id;
        }
        return null;
    }
}
