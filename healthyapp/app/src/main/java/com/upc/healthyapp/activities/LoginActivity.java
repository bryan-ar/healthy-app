package com.upc.healthyapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.upc.healthyapp.R;
import com.upc.healthyapp.utilities.UtlFunciones;

public class LoginActivity extends AppCompatActivity {

    Button btLogin, btContactUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btLogin = findViewById(R.id.btn_login);
        btContactUs = findViewById(R.id.btn_comunicate);

        btLogin.setOnClickListener(view -> {
            UtlFunciones.IniciarActividad(this, MainActivity.class, true);
        });

        btContactUs.setOnClickListener(view -> {
            UtlFunciones.IniciarActividad(this, ContactUsActivity.class, false);
        });
    }
}