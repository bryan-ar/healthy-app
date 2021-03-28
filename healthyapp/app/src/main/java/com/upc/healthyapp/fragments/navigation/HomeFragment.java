package com.upc.healthyapp.fragments.navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.upc.healthyapp.R;
import com.upc.healthyapp.adapters.CitasAdapter;
import com.upc.healthyapp.modals.InfoPersonalFragment;
import com.upc.healthyapp.modals.NuevaCitaFragment;
import com.upc.healthyapp.models.CitaModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView courseRV;
    private ArrayList<CitaModel> citaModelArrayList;
    private Button btNuevaCita;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        courseRV = rootView.findViewById(R.id.rvCitas);

        citaModelArrayList = new ArrayList<>();
        citaModelArrayList.add(new CitaModel("Doctor 1", "Paciente 1", "meet.google.com/grk-dghr-mdk",
                "Marzo", "15", "10:00"));
        citaModelArrayList.add(new CitaModel("Doctor 2", "Paciente 2", "meet.google.com/grk-dghr-mdk",
                "Marzo", "03", "14:00"));
        citaModelArrayList.add(new CitaModel("Doctor 3", "Paciente 1", "meet.google.com/grk-dghr-mdk",
                "Marzo", "01", "08:30"));
        citaModelArrayList.add(new CitaModel("Doctor 4", "Paciente 1", "meet.google.com/grk-dghr-mdk",
                "Febrero", "14", "09:30"));
        citaModelArrayList.add(new CitaModel("Doctor 5", "Paciente 2", "meet.google.com/grk-dghr-mdk",
                "Febrero", "08", "16:00"));
        citaModelArrayList.add(new CitaModel("Doctor 6", "Paciente 3", "meet.google.com/grk-dghr-mdk",
                "Enero", "18", "11:15"));

        // we are initializing our adapter class and passing our arraylist to it.
        CitasAdapter citasAdapter = new CitasAdapter(getActivity(), citaModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        courseRV.setLayoutManager(linearLayoutManager);
        courseRV.setAdapter(citasAdapter);

        btNuevaCita = rootView.findViewById(R.id.btn_nueva_cita);
        btNuevaCita.setOnClickListener(view -> {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            NuevaCitaFragment mNuevaCitaFragment = NuevaCitaFragment.newInstance();
            //mInfoPersonalFragment.setTargetFragment(ActividadPrincipalFragment.this, 100);
            mNuevaCitaFragment.show(fm, "fragment_dialog");
        });

        return rootView;
    }
}