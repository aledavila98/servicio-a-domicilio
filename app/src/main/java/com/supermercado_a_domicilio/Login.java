package com.supermercado_a_domicilio;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;

public class Login extends AppCompatActivity {

    private static final String TAG = "Logjn";
    private Button bLogin;
    private TextView bCreate;
    private EditText etEmail, etPassword;
    private FirebaseAuth mAuth;
    public static final String EMAIL = "Email";
    private DatabaseReference ref;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        this.setTitle(R.string.title_activity_login);
        bCreate = findViewById(R.id.bCrear);
        bLogin = findViewById(R.id.bLogin);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart(){
       super.onStart();
       FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    protected void registrar(View view) {

            final String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();

        if(email.equals("") || password.equals(""))
        {
            Toast.makeText(Login.this, "Por favor complete los campos.",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent i = new Intent(Login.this, UserData.class);
                                i.putExtra(EMAIL, email);
                                startActivity(i);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Login.this, "Autenticación fallida.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    protected void entrar(View view) {
            final String email = etEmail.getText().toString();
            final String password = etPassword.getText().toString();
        if(email.equals("")|| password.equals(""))
        {
            Toast.makeText(Login.this, "Por favor complete los campos",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                callSuper(email);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(Login.this, "Autenticación fallida.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }
    }

    public void callSuper (String email){
        //Aqui llenan la lista de los SuperMercados obteniendo los datos de la base de datos, por mientras lo dejare con un par de superes
        if(SuperActivity.getTamList()>0)
            SuperActivity.clearList();
        SuperActivity.addList(new SuperModel("Los Andes","Dirección: 15 Avenida 6 Calle NO. Barrio Los Andes,21102 San Pedro Sula Cortes" +
                "\nTeléfono: 25454500" ,R.drawable.losandes));
        SuperActivity.addList(new SuperModel("El Colonial","Dirección: Avenida Circunvalacion, 21101 San Pedro Sula, Cortes" +
                "\nTeléfono: 25456440",R.drawable.colonial));
        SuperActivity.addList(new SuperModel("La Colonia","Dirección: Plaza 105 Brigada, Las Acacias Neighborhood, San Pedro Sula 21102 \nTeléfono: 2216-1900",R.drawable.sprlacolonia));
        SuperActivity.addList(new SuperModel("La Antorcha","Dirección: Bulevar del Este, San Pedro Sula 21103 \nTeléfono: 2544-0475",R.drawable.antorhca));
        Intent i = new Intent(Login.this, SuperActivity.class);
        i.putExtra(EMAIL,email);
        startActivity(i);
    }
}


