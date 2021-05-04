package com.upc.healthyapp.fragments.navigation;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.upc.healthyapp.R;
import com.upc.healthyapp.adapters.SintomaAdapter;
import com.upc.healthyapp.modals.NuevaCitaFragment;
import com.upc.healthyapp.modals.NuevoSintomaFragment;
import com.upc.healthyapp.models.DAOSintoma;
import com.upc.healthyapp.models.Sintoma;
import com.upc.healthyapp.utilities.RecyclerItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CitaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CitaFragment extends Fragment implements NuevoSintomaFragment.InterfaceCita {

    ImageView imgFoto1;
    TextView tvNuevoSintoma;
    public static final int RequestPermissionCode = 1;
    RecyclerView recyclerView;
    Button btEnviar;

    DAOSintoma daoSintoma;
    ArrayList<Sintoma> listaSintomas = new ArrayList<>();
    SintomaAdapter sintomaAdapter;

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

        daoSintoma= new DAOSintoma(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cita, container, false);

        tvNuevoSintoma = rootView.findViewById(R.id.tvNuevoSintoma);
        imgFoto1 = rootView.findViewById(R.id.imgFoto1);

        btEnviar = rootView.findViewById(R.id.btEnviarCita);

        recyclerView = rootView.findViewById(R.id.rvSintomas);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        listaSintomas = daoSintoma.listarSintomas();

        sintomaAdapter = new SintomaAdapter(getActivity(), listaSintomas);
        recyclerView.setAdapter(sintomaAdapter);

        listarSintomas();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        int id = listaSintomas.get(position).getId();

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        NuevoSintomaFragment mNuevoSintomaFragment = NuevoSintomaFragment.newInstance(id);
                        mNuevoSintomaFragment.setTargetFragment(CitaFragment.this, 100);
                        mNuevoSintomaFragment.show(fm, "fragment_dialog");
                    }

                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                        // context menu
                        view.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                            @Override
                            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                                int id = listaSintomas.get(position).getId();

                                contextMenu.setHeaderTitle("SELECCIONE UNA OPCIÓN");
                                contextMenu.add(0, id, 0, "Eliminar");
                            }
                        });
                    }
                })
        );

        tvNuevoSintoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                NuevoSintomaFragment mNuevoSintomaFragment = NuevoSintomaFragment.newInstance(0);
                mNuevoSintomaFragment.setTargetFragment(CitaFragment.this, 100);
                mNuevoSintomaFragment.show(fm, "fragment_dialog");
            }
        });

       EnableRuntimePermission();

        imgFoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 7);
            }
        });

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (Sintoma sintoma : listaSintomas) {
                    RegistrarSintomaHTTP(sintoma);
                }
            }
        });

        return rootView;
    }

    private void RegistrarSintomaHTTP(Sintoma sintoma) {
        String url = "http://healthyupc.atwebpages.com/index.php/sintomas";

        StringRequest peticion = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast toast = Toast.makeText(getActivity(), "Se registró correctamente", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("id_cita", String.valueOf(1));
                parametros.put("desc_sint", String.valueOf(sintoma.getDescripcion()));
                parametros.put("fec_sint", String.valueOf(sintoma.getFecha()));
                parametros.put("hora", String.valueOf(sintoma.getHora()));
                parametros.put("tipo_sint", String.valueOf(1));
                parametros.put("foto_sint", "");
                return parametros;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(peticion);
    }

    private JSONArray parsearSintomasJson() {
        JSONArray array = new JSONArray();

        for (Sintoma sintoma :listaSintomas) {
            JSONObject obj=new JSONObject();
            try {
                obj.put("oSintoma",sintoma);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            array.put(obj);
        }

        return array;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        daoSintoma.eliminarSintoma(id);
        try {
            actualizarLista();
            Toast.makeText(getContext(), "Síntoma eliminado", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    private void listarSintomas(){
        listaSintomas = daoSintoma.listarSintomas();
        sintomaAdapter = new SintomaAdapter(getActivity(), listaSintomas);
        recyclerView.setAdapter(sintomaAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void EnableRuntimePermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.CAMERA)) {
            Toast.makeText(getActivity(),"CAMERA permission allows us to Access CAMERA app",     Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(getActivity(),new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            switch (requestCode){
                case 7:
                    imgFoto1.setImageBitmap(bitmap);
                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] result) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (result.length > 0 && result[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity(), "Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void actualizarLista() throws Exception {
        listaSintomas = daoSintoma.listarSintomas();
        sintomaAdapter.actualizarSintomas(listaSintomas);
    }
}