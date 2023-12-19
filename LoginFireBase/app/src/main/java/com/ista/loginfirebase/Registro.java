package com.ista.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registro extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText editTextEmail, editTextContra;
    private Button btnRegistro;
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent menu = new Intent(Registro.this, MainActivity.class);
            startActivity(menu);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();
        editTextEmail = findViewById(R.id.txtemail);
        editTextContra = findViewById(R.id.txtcontra);
        btnRegistro = findViewById(R.id.btnRegistrar);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo, contra;
                correo = String.valueOf(editTextEmail.getText());
                contra = String.valueOf(editTextContra.getText());
                if (!correo.isEmpty() && !contra.isEmpty()) {
                    mAuth.createUserWithEmailAndPassword(correo, contra)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(Registro.this, "Registro exitoso",
                                                Toast.LENGTH_SHORT).show();
                                        Intent login = new Intent(Registro.this, MainActivity.class);
                                        startActivity(login);
                                        finish();
                                    } else {
                                        Toast.makeText(Registro.this, "Error al registrar",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }else{
                    Toast.makeText(Registro.this, "Llene todos los campos por favor",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}