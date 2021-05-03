package com.upc.healthyapp.fragments.navigation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.annotations.Nullable;
import com.upc.healthyapp.R;
import com.upc.healthyapp.adapters.CitasAdapter;
import com.upc.healthyapp.adapters.RecetaAdapter;
import com.upc.healthyapp.adapters.SintomaAdapter;
import com.upc.healthyapp.models.CitaModel;
import com.upc.healthyapp.models.Receta;
import com.upc.healthyapp.models.Sintoma;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecetaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecetaFragment extends Fragment {

    private RecyclerView rvRecetas;
    private ArrayList<Receta> recetasModelArrayList;

    public RecetaFragment() {
        // Required empty public constructor
    }

    public static RecetaFragment newInstance() {
        RecetaFragment fragment = new RecetaFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_receta, container, false);
        rvRecetas = rootView.findViewById(R.id.rvRecetas);
        CargarRecetasHTTP();

        return rootView;
    }

    private void CargarRecetasHTTP() {
        String url = "http://healthyupc.atwebpages.com/index.php/recetas?";
        StringRequest peticion = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    recetasModelArrayList = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        int id_receta = jsonObject.getInt("id_receta");
                        int id_cita = jsonObject.getInt("id_cita");
                        String receta1 = jsonObject.getString("receta1");
                        String receta2 = jsonObject.getString("receta2");
                        String fecha = jsonObject.getString("fecha");
                        String hora = jsonObject.getString("hora");

                        Receta recetaModel = new Receta(id_receta, id_cita,
                                receta1, receta2, fecha, hora);

                        recetasModelArrayList.add(recetaModel);
                    }

                    // we are initializing our adapter class and passing our arraylist to it.
                    RecetaAdapter recetasAdapter = new RecetaAdapter(getActivity(), recetasModelArrayList);

                    // below line is for setting a layout manager for our recycler view.
                    // here we are creating vertical list so we will provide orientation as vertical
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

                    // in below two lines we are setting layoutmanager and adapter to our recycler view.
                    rvRecetas.setLayoutManager(linearLayoutManager);
                    rvRecetas.setAdapter(recetasAdapter);
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