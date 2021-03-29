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

public class HomeFragment extends Fragment {

    private RecyclerView rvCitas;
    private ArrayList<CitaModel> citasModelArrayList;

    public HomeFragment() {
        // Required empty public constructor
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

        rvCitas = rootView.findViewById(R.id.rvCitas);

        citasModelArrayList = new ArrayList<>();
        citasModelArrayList.add(new CitaModel("Doctor 1", "Paciente 1", "meet.google.com/grk-dghr-mdk",
                "Marzo", "15", "10:00"));
        citasModelArrayList.add(new CitaModel("Doctor 2", "Paciente 2", "meet.google.com/grk-dghr-mdk",
                "Marzo", "03", "14:00"));
        citasModelArrayList.add(new CitaModel("Doctor 3", "Paciente 1", "meet.google.com/grk-dghr-mdk",
                "Marzo", "01", "08:30"));
        citasModelArrayList.add(new CitaModel("Doctor 4", "Paciente 1", "meet.google.com/grk-dghr-mdk",
                "Febrero", "14", "09:30"));
        citasModelArrayList.add(new CitaModel("Doctor 5", "Paciente 2", "meet.google.com/grk-dghr-mdk",
                "Febrero", "08", "16:00"));
        citasModelArrayList.add(new CitaModel("Doctor 6", "Paciente 3", "meet.google.com/grk-dghr-mdk",
                "Enero", "18", "11:15"));

        // we are initializing our adapter class and passing our arraylist to it.
        CitasAdapter citasAdapter = new CitasAdapter(getActivity(), citasModelArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        rvCitas.setLayoutManager(linearLayoutManager);
        rvCitas.setAdapter(citasAdapter);

        return rootView;
    }
}