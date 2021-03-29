package com.upc.healthyapp.fragments.navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.upc.healthyapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CitaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CitaFragment extends Fragment {


    public CitaFragment() {
        // Required empty public constructor
    }

    public static CitaFragment newInstance() {
        CitaFragment fragment = new CitaFragment();
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
        return inflater.inflate(R.layout.fragment_cita, container, false);
    }
}