package com.supermercado_a_domicilio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class UserInformation {

    String nombre, direccion, email;
    int telefono;

    public UserInformation() {}

    public UserInformation(String nombre, int telefono, String dir, String email)
    {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = dir;
        this.email = email;
    }

}

public class UserData extends AppCompatActivity {

    private EditText etNombre, etTelefono, etDireccion, etID;
    private Button registrar;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        Intent intent = getIntent();
        email = intent.getStringExtra(Login.EMAIL);

        etNombre = findViewById(R.id.etName);
        etTelefono = findViewById(R.id.etPhone);
        etDireccion = findViewById(R.id.etAddress);
        registrar = findViewById(R.id.bRegistrar);
        etID = findViewById(R.id.etID);
    }

    protected void registrarInfo(View view)
    {
        String nombre = etNombre.getText().toString();
        int telefono = Integer.parseInt(etTelefono.getText().toString());
        String direccion = etDireccion.getText().toString();
        String id = etID.getText().toString();
        UserInformation userInformation = new UserInformation(nombre,telefono,direccion,email);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("users-data").child(id).setValue(userInformation);

        Toast toast = Toast.makeText(this,"Se registro exitosamente",Toast.LENGTH_SHORT);
        toast.show();
    }
}
