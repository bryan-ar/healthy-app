package com.upc.healthyapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.upc.healthyapp.R;
import com.upc.healthyapp.utilities.UtlFunciones;

public class LoginActivity extends AppCompatActivity {
    EditText et_username, et_password;

    Button btLogin, btContactUs, btRegis;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        et_username =(EditText) findViewById(R.id.et_email);
        et_password =(EditText) findViewById(R.id.et_passwordr);
        btLogin = (Button) findViewById(R.id.btn_login);
        btContactUs = (Button)findViewById(R.id.btn_comunicate);
        btRegis = (Button)findViewById(R.id.btn_registrar);
        mAuth = FirebaseAuth.getInstance();

        //LOGIN

        /*btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_username.getText().toString();
                String password = et_password.getText().toString();

                //validación de usuario activo
                if (mAuth.getCurrentUser()!=null){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }
                //fin de validación de usario activo


                if (TextUtils.isEmpty(email)){
                    et_username.setError("Escribe el correo");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    et_password.setError("Escribe el password");
                    return;
                }
                if (password.length()<6){
                    et_password.setError("El password debe tener más de 6 caracteres");
                    return;
                }
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Ingresaste", Toast.LENGTH_SHORT).show();
                            //startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(LoginActivity.this, "Ocurrio un error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });*/



        btLogin.setOnClickListener(view -> {
            UtlFunciones.IniciarActividad(this, MenuPriActivity.class, true);
        });


        //--------------
        btContactUs.setOnClickListener(view -> {
            UtlFunciones.IniciarActividad(this, ContactUsActivity.class, false);
        });

        btRegis.setOnClickListener(view ->{
            UtlFunciones.IniciarActividad(this, RegisterActivity.class, false);
        });
    }



}