package com.upc.healthyapp.modals;

import android.app.DatePickerDialog;
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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.upc.healthyapp.R;
import com.upc.healthyapp.adapters.CitasAdapter;
import com.upc.healthyapp.adapters.DoctoresAdapter;
import com.upc.healthyapp.models.CitaModel;
import com.upc.healthyapp.models.DoctorModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
    String[] especialidades = new String[]{"Seleccione", "Medicina Interna", "Cardiología", "Traumatología"};

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

        doctoresModelArrayList = new ArrayList<>();
        doctoresModelArrayList.add(new DoctorModel("Doctor 1", "Medicina Interna",
                "https://media.istockphoto.com/photos/friendly-doctor-at-the-hospital-picture-id511583494?k=6&m=511583494&s=612x612&w=0&h=-8azV9sjiTx9dPBAIxy0I9g15pUVs4gTVT7gGsjx9J4="));
        doctoresModelArrayList.add(new DoctorModel("Doctor 2", "Medicina Interna",
                "https://orientacion.universia.edu.pe/imgs2011/imagenes/shuttersto-2016_09_16_190709.jpg"));
        doctoresModelArrayList.add(new DoctorModel("Doctor 3", "Medicina Interna",
                "https://st.depositphotos.com/1281717/1353/i/950/depositphotos_13532382-stock-photo-portrait-of-happy-smiling-young.jpg"));
        doctoresModelArrayList.add(new DoctorModel("Doctor 4", "Medicina Interna",
                "https://fm105.com.mx/wp-content/uploads/2018/05/las-mejores-anecdotas-de-la-relacion-entre-pacientes-y-medicos.jpg"));
        doctoresModelArrayList.add(new DoctorModel("Doctor 5", "Medicina Interna",
                "https://k60.kn3.net/taringa/A/5/4/0/7/2/unwerfron/02C.png"));
        doctoresModelArrayList.add(new DoctorModel("Doctor 6", "Medicina Interna",
                "https://st2.depositphotos.com/1011434/5480/i/950/depositphotos_54809145-stock-photo-beautiful-young-female-doctor.jpg"));

        DoctoresAdapter doctoresAdapter = new DoctoresAdapter(getActivity(), doctoresModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        rvDoctores.setLayoutManager(linearLayoutManager);
        rvDoctores.setAdapter(doctoresAdapter);

        ArrayAdapter<String> especialidadesAdapter = new ArrayAdapter<>(getActivity(),R.layout.spinner_item, especialidades);
        //especialidadesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spEspecialidades.setAdapter(especialidadesAdapter);

        etFecha = view.findViewById(R.id.etFecha);
        btPagar = view.findViewById(R.id.bt_pagar);
        btCerrar = view.findViewById(R.id.bt_cerrar);

        etFecha.setOnClickListener(new isOnClickCalendar());
        btPagar.setOnClickListener(new isOnClickPagar());
        btCerrar.setOnClickListener(new isOnClickCerrar());

        super.onViewCreated(view, savedInstanceState);
    }

    private DatePickerDialog.OnDateSetListener onDate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(final android.widget.DatePicker view, final int year, final int month, final int dayOfMonth) {
            Calendar mCalender = Calendar.getInstance();
            mCalender.set(Calendar.YEAR, year);
            mCalender.set(Calendar.MONTH, month);
            mCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String selectedDate = sdf.format(mCalender.getTime());
            etFecha.setText(selectedDate);
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
            getDialog().dismiss();
        }
    }

    class isOnClickCerrar implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            getDialog().dismiss();
        }
    }
}