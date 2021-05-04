package com.upc.healthyapp.fragments.navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.upc.healthyapp.R;
import com.upc.healthyapp.adapters.CitasAdapter;
import com.upc.healthyapp.models.CitaModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiagnosticFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiagnosticFragment extends Fragment {

    private TextView idPaciente, nombrePaciente, idDoctor, nombreDoctor, especialidadDoctor, diagnostico;

    public DiagnosticFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DiagnosticFragment newInstance() {
        DiagnosticFragment fragment = new DiagnosticFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_diagnostic, container, false);

        idPaciente = rootView.findViewById(R.id.idPaciente);
        nombrePaciente = rootView.findViewById(R.id.nombrePaciente);
        idDoctor = rootView.findViewById(R.id.idDoctor);
        nombreDoctor = rootView.findViewById(R.id.nombreDoctor);
        especialidadDoctor = rootView.findViewById(R.id.especialidadDoctor);
        diagnostico = rootView.findViewById(R.id.diagnostico);

        ConsultarDiagnosticoHTTP();

        return rootView;
    }

    private void ConsultarDiagnosticoHTTP() {
        String url = "http://healthyupc.atwebpages.com/index.php/diagnostico/1";
        StringRequest peticion = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray jsonArray = new JSONArray(response);

                    if(jsonArray.length() != 0){
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        idPaciente.setText(jsonObject.getString("id_paciente"));
                        nombrePaciente.setText(jsonObject.getString("nom_pac"));
                        idDoctor.setText(jsonObject.getString("id_doctor"));
                        nombreDoctor.setText(jsonObject.getString("nomc_doc"));
                        especialidadDoctor.setText(jsonObject.getString("especialidad"));
                        diagnostico.setText(jsonObject.getString("com_cit"));
                    }
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