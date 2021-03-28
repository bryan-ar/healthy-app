package com.upc.healthyapp.fragments.navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.upc.healthyapp.R;
import com.upc.healthyapp.adapters.CitasAdapter;
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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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

        return rootView;
    }
}