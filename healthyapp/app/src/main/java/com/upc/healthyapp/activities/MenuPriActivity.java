package com.upc.healthyapp.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.FragmentManagerNonConfig;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.upc.healthyapp.Interfaces.IComunicaFragments;
import com.upc.healthyapp.R;
import com.upc.healthyapp.fragments.navigation.CitaFragment;
import com.upc.healthyapp.fragments.navigation.DiagnosticFragment;
import com.upc.healthyapp.fragments.navigation.MapsFragment;
import com.upc.healthyapp.fragments.navigation.RecetaFragment;
import com.upc.healthyapp.utilities.UtlFunciones;

public class MenuPriActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView btn_citas, btn_recetas, btn_sintomas, btn_comentarios, btn_cm, btn_call;

    IComunicaFragments iComunicaFragments;
    View vista;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pri);

        btn_citas = (CardView) findViewById(R.id.btn_citas);
        btn_recetas =(CardView)  findViewById(R.id.btn_recetas);
        btn_sintomas = (CardView) findViewById(R.id.btn_sintomas);
        btn_comentarios = (CardView) findViewById(R.id.btn_comentarios);
        btn_cm = (CardView) findViewById(R.id.btn_cm);
        btn_call = (CardView) findViewById(R.id.btn_call);

        btn_citas.setOnClickListener(this);
        btn_recetas.setOnClickListener(this);
        btn_sintomas.setOnClickListener(this);
        btn_comentarios.setOnClickListener(this);
        btn_cm.setOnClickListener(this);
        btn_call.setOnClickListener(this);


    }




    @Override
    public void onClick(View v) {

        Intent i;
        switch (v.getId()){
            case R.id.btn_citas  :
                i = new Intent(this, MainActivity.class );
                i.putExtra("codigoFragment", 1);
                startActivity(i);
                break;
            case R.id.btn_recetas :
                i = new Intent(this, MainActivity.class);
                i.putExtra("codigoFragment", 2);
                startActivity(i);
                break;
            case R.id.btn_sintomas :
                i = new Intent(this, MainActivity.class);
                i.putExtra("codigoFragment", 3);
                startActivity(i);
                break;
            case R.id.btn_cm :
                i = new Intent(this, MainActivity.class);
                i.putExtra("codigoFragment", 4);
                startActivity(i);
                break;
            case R.id.btn_call : i = new Intent(this, ContactUsActivity.class); startActivity(i); break;
            //cambiar clase
            case R.id.btn_comentarios
               : i = new Intent(this, RecetaFragment.class);
                i.putExtra("codigoFragment", 5);
            startActivity(i);
            break;
            default:break;
        }

    }


}