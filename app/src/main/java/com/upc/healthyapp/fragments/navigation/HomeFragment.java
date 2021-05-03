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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.upc.healthyapp.R;
import com.upc.healthyapp.activities.MainActivity;
import com.upc.healthyapp.adapters.CitasAdapter;
import com.upc.healthyapp.modals.InfoPersonalFragment;
import com.upc.healthyapp.modals.NuevaCitaFragment;
import com.upc.healthyapp.models.CitaModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rvCitas;
    private List<CitaModel> citasModelArrayList;
    CitasAdapter adaptador;
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvCitas.setLayoutManager(linearLayoutManager);
        citasModelArrayList = new ArrayList<>();
        mostrarCitas();
        adaptador = new CitasAdapter(getActivity(), citasModelArrayList);

        rvCitas.setAdapter(adaptador);


        /* citasModelArrayList = new ArrayList<>();
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
*/


        // we are initializing our adapter class and passing our arraylist to it.


        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical

        // in below two lines we are setting layoutmanager and adapter to our recycler view.



        return rootView;
    }

    public void mostrarCitas(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.URL_CITAS),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("CitaModels");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                citasModelArrayList.add(
                                        new CitaModel(
                                                jsonObject1.getString("id_cita"),
                                                jsonObject1.getString("id_paciente"),
                                                jsonObject1.getString("id_doctor"),
                                                jsonObject1.getString("diag_cit"),
                                                jsonObject1.getString("com_cita"),
                                                jsonObject1.getString("dia"),
                                                jsonObject1.getString("mes"),
                                                jsonObject1.getString("anio")
                                        )
                                );
                            }
                            adaptador = new CitasAdapter(getContext(), citasModelArrayList);
                            rvCitas.setAdapter(adaptador);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );
        requestQueue.add(stringRequest);
    }
}