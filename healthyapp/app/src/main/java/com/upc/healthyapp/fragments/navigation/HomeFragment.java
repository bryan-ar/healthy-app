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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.upc.healthyapp.R;
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
        CargarCitasHTTP();

        return rootView;
    }

    private void CargarCitasHTTP() {
        String url = "http://healthyupc.atwebpages.com/index.php/citas";
        StringRequest peticion = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    citasModelArrayList = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String sNombreDoctor = jsonObject.getString("sNombreDoctor");
                        String sNombrePaciente = jsonObject.getString("sNombrePaciente");
                        String sLinkReunion = jsonObject.getString("sLinkReunion");
                        String sMes = jsonObject.getString("sMes");
                        String sDia = jsonObject.getString("sDia");
                        String sHora = jsonObject.getString("sHora");

                        CitaModel citaModel = new CitaModel(sNombreDoctor, sNombrePaciente,
                                sLinkReunion, sMes, sDia, sHora);

                        citasModelArrayList.add(citaModel);
                    }

                    // we are initializing our adapter class and passing our arraylist to it.
                    CitasAdapter citasAdapter = new CitasAdapter(getActivity(), citasModelArrayList);

                    // below line is for setting a layout manager for our recycler view.
                    // here we are creating vertical list so we will provide orientation as vertical
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

                    // in below two lines we are setting layoutmanager and adapter to our recycler view.
                    rvCitas.setLayoutManager(linearLayoutManager);
                    rvCitas.setAdapter(citasAdapter);
                }
                catch (JSONException e){
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue cola = Volley.newRequestQueue(getActivity());
        cola.add(peticion);
    }
}