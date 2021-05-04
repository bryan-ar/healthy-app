package com.upc.healthyapp.modals;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.upc.healthyapp.R;
import com.upc.healthyapp.adapters.CitasAdapter;
import com.upc.healthyapp.adapters.DoctoresAdapter;
import com.upc.healthyapp.models.CitaModel;
import com.upc.healthyapp.models.DoctorModel;
import com.upc.healthyapp.models.Especialidad;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NuevaCitaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NuevaCitaFragment extends DialogFragment {

    Button btPagar, btCerrar;
    EditText etFecha;
    private RecyclerView rvDoctores;
    private ArrayList<DoctorModel> doctoresModelArrayList;
    Spinner spEspecialidades;
    String[] especialidades;
    private ArrayList<Especialidad> especialidadesArrayList;
    String fecha, hora;

    public NuevaCitaFragment() {
        // Required empty public constructor
    }

    public static NuevaCitaFragment newInstance() {
        NuevaCitaFragment fragment = new NuevaCitaFragment();
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(true);
        return inflater.inflate(R.layout.fragment_nueva_cita, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        rvDoctores = view.findViewById(R.id.rvDoctores);
        spEspecialidades = view.findViewById(R.id.spEspecialidades);

        CargarEspecialidadesHTTP();

        spEspecialidades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),"", Toast.LENGTH_LONG).show();
                Especialidad oEspecialidad = obtenerEspecialidad(String.valueOf(adapterView.getSelectedItem()));
                CargarDoctoresHTTP(oEspecialidad);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        etFecha = view.findViewById(R.id.etFecha);
        btPagar = view.findViewById(R.id.bt_pagar);
        btCerrar = view.findViewById(R.id.bt_cerrar);

        etFecha.setOnClickListener(new isOnClickCalendar());
        btPagar.setOnClickListener(new isOnClickPagar());
        btCerrar.setOnClickListener(new isOnClickCerrar());

        super.onViewCreated(view, savedInstanceState);
    }

    private Especialidad obtenerEspecialidad(String especialidad){

        for (Especialidad esp: especialidadesArrayList) {
            if(esp.getEspecialidad() == especialidad){
                return esp;
            }
        }

        return new Especialidad();
    }

    private void CargarEspecialidadesHTTP() {
        String url = "http://healthyupc.atwebpages.com/index.php/especialidades";
        StringRequest peticion = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    especialidades = new String[jsonArray.length()];
                    especialidadesArrayList = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id_especialidad");
                        String especialidad = jsonObject.getString("nom_esp");
                        especialidades[i] = especialidad;
                        Especialidad oEspecialidad = new Especialidad(id, especialidad);

                        especialidadesArrayList.add(oEspecialidad);
                    }

                    ArrayAdapter<String> especialidadesAdapter = new ArrayAdapter<>(getActivity(),R.layout.spinner_item, especialidades);
                    spEspecialidades.setAdapter(especialidadesAdapter);
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

    private void CargarDoctoresHTTP(Especialidad oEspecialidad) {
        String url = "http://healthyupc.atwebpages.com/index.php/doctores";
        StringRequest peticion = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    doctoresModelArrayList = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String sNombreDoctor = jsonObject.getString("nomc_doc");
                        int iEspecialidad = jsonObject.getInt("id_especialidad");
                        String sFotoDoctor = "https://orientacion.universia.edu.pe/imgs2011/imagenes/shuttersto-2016_09_16_190709.jpg";

                        if(iEspecialidad == oEspecialidad.getId_especialidad()){
                            DoctorModel doctor = new DoctorModel(sNombreDoctor,
                                    oEspecialidad.getEspecialidad(), sFotoDoctor);
                            doctoresModelArrayList.add(doctor);
                        }

                    }

                    DoctoresAdapter doctoresAdapter = new DoctoresAdapter(getActivity(), doctoresModelArrayList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                            LinearLayoutManager.HORIZONTAL, false);

                    rvDoctores.setLayoutManager(linearLayoutManager);
                    rvDoctores.setAdapter(doctoresAdapter);
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

    private DatePickerDialog.OnDateSetListener onDate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(final android.widget.DatePicker view, final int year, final int month, final int dayOfMonth) {
            Calendar mCalender = Calendar.getInstance();
            mCalender.set(Calendar.YEAR, year);
            mCalender.set(Calendar.MONTH, month);
            mCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
            fecha = sdf.format(mCalender.getTime());

            TimePicker mTimePickerDialogFragment = new TimePicker();
            mTimePickerDialogFragment.setCallBack(onTime);
            mTimePickerDialogFragment.show(getParentFragmentManager(), "DATE PICK");
        }
    };

    private TimePickerDialog.OnTimeSetListener onTime = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(android.widget.TimePicker timePicker, int hour, int minute) {
            Calendar mCalender = Calendar.getInstance();
            mCalender.set(Calendar.HOUR, hour);
            mCalender.set(Calendar.MINUTE, minute);

            hora = ("00"+ hour).substring(("00"+ hour).length()-2, ("00"+ hour).length()) + ":" + ("00"+ minute).substring(("00"+ minute).length()-2, ("00"+ minute).length());
            etFecha.setText(fecha + " " + hora);
        }
    };

    class isOnClickCalendar implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            DatePicker mDatePickerDialogFragment = new DatePicker();
            mDatePickerDialogFragment.setCallBack(onDate);
            mDatePickerDialogFragment.show(getParentFragmentManager(), "DATE PICK");
        }
    }

    class isOnClickPagar implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            CitaModel cita = new CitaModel();
            cita.setsDia(etFecha.getText().toString().substring(0,2));
            cita.setsMes(ObtenerMes(etFecha.getText().toString().substring(3,5)));
            cita.setsHora(etFecha.getText().toString().substring(6,11));

            RegistrarCitaHTTP(cita);
        }
    }

    private String ObtenerMes(String substring) {
        switch (substring){
            case "01":
                return "Enero";
            case "02":
                return "Febrero";
            case "03":
                return "Marzo";
            case "04":
                return "Abril";
            case "05":
                return "Mayo";
            case "06":
                return "Junio";
            case "07":
                return "Julio";
            case "08":
                return "Agosto";
            case "09":
                return "Setiembre";
            case "10":
                return "Octubre";
            case "11":
                return "Noviembre";
            case "12":
                return "Diciembre";
        }

        return "";
    }

    private void RegistrarCitaHTTP(CitaModel cita) {
        String url = "http://healthyupc.atwebpages.com/index.php/citas";

        StringRequest peticion = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast toast = Toast.makeText(getActivity(), "Se registró correctamente", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                getDialog().dismiss();
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
                parametros.put("id_paciente", String.valueOf(1));
                parametros.put("id_doctor", String.valueOf(4));
                parametros.put("diag_cit", "Tiene síntomas de COVID; sin embargo se debe descartar.");
                parametros.put("est_cit", "0");
                parametros.put("com_cit", "Reposo absoluto. Neumonía leve, resultado COVID negativo");
                parametros.put("dia", String.valueOf(cita.getsDia()));
                parametros.put("mes", String.valueOf(cita.getsMes()));
                parametros.put("anio", String.valueOf(cita.getsHora()));
                parametros.put("total_pagar", String.valueOf(40));
                parametros.put("especialidad", String.valueOf(spEspecialidades.getSelectedItem()));
                return parametros;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(peticion);
    }

    class isOnClickCerrar implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            getDialog().dismiss();
        }
    }
}