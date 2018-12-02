package com.supermercado_a_domicilio;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

class UserInformation {

    String id, nombre, direccion, email;
    int telefono;

    public UserInformation() {}

    public UserInformation(String id, String nombre, int telefono, String dir, String email)
    {
        this.id = id;
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
    public static final String EMAIL = "email";
    private static final String TAG = "UserData";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);
        setTitle(R.string.userData);
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
        if(nombre.equals("") || direccion.equals("") ||
                etID.getText().toString().equals("") || etTelefono.getText().toString().equals(""))
        {
            Toast.makeText(UserData.this, "Por favor complete los campos.",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            UserInformation userInformation = new UserInformation(id, nombre, telefono, direccion, email);

            setFirebaseProfile(id);

            databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("users-data").child(id).setValue(userInformation);

            Toast toast = Toast.makeText(this, "Se registro exitosamente", Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = new Intent(UserData.this, SuperActivity.class);
            intent.putExtra(EMAIL,email);
            startActivity(intent);
        }
    }

    private void setFirebaseProfile(String id) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(id)
                .build();
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User profile updated.");
                        }
                    }
                });
    }

    public void callBack(View view) {
        this.finish();
    }
}
