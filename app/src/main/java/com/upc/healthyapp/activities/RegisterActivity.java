package com.upc.healthyapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.upc.healthyapp.R;

public class RegisterActivity extends AppCompatActivity {
    EditText et_email, et_passwordr;
    Button btn_registrar;

    FirebaseAuth firebaseAuth;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.et_email, Patterns.EMAIL_ADDRESS,R.string.mail_invalido);
        awesomeValidation.addValidation(this,R.id.et_passwordr,".{6,}", R.string.password_invalido);
        et_email = (EditText)findViewById(R.id.et_email);
        et_passwordr = (EditText)findViewById(R.id.et_passwordr);
        btn_registrar = (Button)findViewById(R.id.btn_registrar);
        //validación de usuario activo
        if (firebaseAuth.getCurrentUser()!=null){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
           finish();
        }
        //fin de validación de usario activo


        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = et_email.getText().toString();
                String password = et_passwordr.getText().toString();

                if (awesomeValidation.validate()){
                    firebaseAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(RegisterActivity.this, "Usuario registrado con exito", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            }else{
                                Toast.makeText(RegisterActivity.this, "Ocurrió un error", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }else{
                    Toast.makeText(RegisterActivity.this,"Completa todos los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}