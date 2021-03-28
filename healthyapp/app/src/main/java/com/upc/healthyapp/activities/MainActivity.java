package com.upc.healthyapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.upc.healthyapp.R;
import com.upc.healthyapp.fragments.navigation.CitaFragment;
import com.upc.healthyapp.fragments.navigation.DiagnosticFragment;
import com.upc.healthyapp.fragments.navigation.HomeFragment;
import com.upc.healthyapp.fragments.navigation.MapsFragment;
import com.upc.healthyapp.fragments.navigation.RecetaFragment;
import com.upc.healthyapp.modals.InfoPersonalFragment;
import com.upc.healthyapp.utilities.UtlFunciones;

public class MainActivity extends AppCompatActivity{
    SpaceNavigationView navigationView;
    private CitaFragment citaFragment;
    private DiagnosticFragment diagnosticFragment;
    private HomeFragment homeFragment;
    private MapsFragment mapsFragment;
    private RecetaFragment recetaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.tb_titulo);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_exit_to_app_24);
        toolbar.setTitle(R.string.app_name);

        toolbar.setNavigationOnClickListener(v ->
                UtlFunciones.IniciarActividad(this, LoginActivity.class, true));

        setSupportActionBar(toolbar);

        citaFragment = new CitaFragment();
        diagnosticFragment = new DiagnosticFragment();
        homeFragment = new HomeFragment();
        mapsFragment = new MapsFragment();
        recetaFragment = new RecetaFragment();

        navigationView = findViewById(R.id.space);

        navigationView.initWithSaveInstanceState(savedInstanceState);
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_baseline_video_call_24));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_baseline_event_note_24));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_baseline_map_24));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_baseline_fact_check_24));

        navigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                setFragment(homeFragment);
                Toast.makeText(MainActivity.this,"onCentreButtonClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                Toast.makeText(MainActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();

                switch (itemIndex){
                    case 0:
                        setFragment(citaFragment);
                        return;
                    case 1:
                        setFragment(recetaFragment);
                        return;
                    case 2:
                        setFragment(mapsFragment);
                        return;
                    case 3:
                        setFragment(diagnosticFragment);
                        return;
                    default:
                        setFragment(homeFragment);
                        return;
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                Toast.makeText(MainActivity.this, itemIndex + " " + itemName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int iId = item.getItemId();
        switch (iId) {
            case R.id.menu_info:
                FragmentManager fm = this.getSupportFragmentManager();
                InfoPersonalFragment mInfoPersonalFragment = InfoPersonalFragment.newInstance();
                //mInfoPersonalFragment.setTargetFragment(ActividadPrincipalFragment.this, 100);
                mInfoPersonalFragment.show(fm, "fragment_dialog");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}