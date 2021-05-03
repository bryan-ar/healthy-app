package com.upc.healthyapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.upc.healthyapp.R;

public class ContactUsActivity extends AppCompatActivity {

    private CardView cvLlamanos, cvEscribenos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        Toolbar toolbar = findViewById(R.id.tb_comunicate);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        toolbar.setNavigationOnClickListener(v -> finish());

        cvLlamanos = findViewById(R.id.cv_llamanos);
        cvEscribenos = findViewById(R.id.cv_escribenos);

        cvLlamanos.setOnClickListener(new isOnClickLlamar());
        cvEscribenos.setOnClickListener(new isOnClickWhatsapp());

    }

    class isOnClickLlamar implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String sNumero = "tel:" + "962337356";
            Intent call = new Intent(Intent.ACTION_DIAL);
            call.setData(Uri.parse(sNumero));
            if (call.resolveActivity(getPackageManager()) != null) {
                startActivity(call);
            }
        }
    }

    class isOnClickWhatsapp implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (verificarWhatsApp()) {
                String nCelular = "51" + "962337356";
                String sMensaje = "Mensaje desde Healthy App.";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + nCelular + "&text=" + sMensaje));
                startActivity(intent);
            } else {
                Toast.makeText(ContactUsActivity.this,"WhatsApp no está instalado en su dispositivo.", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    private boolean verificarWhatsApp() {
        PackageManager packageManager = getApplicationContext().getPackageManager();
        try {
            packageManager.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}