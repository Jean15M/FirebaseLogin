package com.ista.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button btnCerrar;
    private TextView txtDetalle;
    private FirebaseUser usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        btnCerrar = findViewById(R.id.btnSalir);
        txtDetalle = findViewById(R.id.txtUsuario);
        usuario = mAuth.getCurrentUser();

        if(usuario==null){
            Intent login = new Intent(MainActivity.this, Login.class);
            startActivity(login);
            finish();
        }else{
            txtDetalle.setText(usuario.getEmail());
        }

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent login = new Intent(MainActivity.this, Login.class);
                startActivity(login);
                finish();
            }
        });


    }



}