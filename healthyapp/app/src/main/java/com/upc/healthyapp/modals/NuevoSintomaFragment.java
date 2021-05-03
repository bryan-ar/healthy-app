package com.upc.healthyapp.modals;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.upc.healthyapp.R;
import com.upc.healthyapp.models.DAOSintoma;
import com.upc.healthyapp.models.Sintoma;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NuevoSintomaFragment extends DialogFragment {
    private InterfaceCita interfaceCita;
    Button btGrabar, btCerrar;
    EditText etDescripcion, etFechaHora;
    String fecha, hora;

    private DAOSintoma daoSintoma;
    int codigoSintoma = 0;

    public NuevoSintomaFragment() {
        // Required empty public constructor
    }

    public static NuevoSintomaFragment newInstance(int codigoSintoma) {
        NuevoSintomaFragment fragment = new NuevoSintomaFragment();
        Bundle args = new Bundle();
        args.putInt("codigo_sintoma", codigoSintoma);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            interfaceCita = (InterfaceCita) getTargetFragment();
        } catch (ClassCastException exp) {
            exp.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        daoSintoma = new DAOSintoma(getContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(true);
        return inflater.inflate(R.layout.fragment_nuevo_sintoma, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        etDescripcion = view.findViewById(R.id.etDescripcion);
        etFechaHora = view.findViewById(R.id.etFechaHora);
        btGrabar = view.findViewById(R.id.bt_grabar);
        btCerrar = view.findViewById(R.id.bt_cerrar);

        PintarCampos();

        etFechaHora.setOnClickListener(new isOnClickCalendar());
        btGrabar.setOnClickListener(new isOnClickGrabar());
        btCerrar.setOnClickListener(new isOnClickCerrar());

        super.onViewCreated(view, savedInstanceState);
    }

    private void PintarCampos() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            codigoSintoma = bundle.getInt("codigo_sintoma", 0);
            Sintoma oSintoma = daoSintoma.obtenerSintoma(codigoSintoma);

            if(codigoSintoma != 0){
                etDescripcion.setText(oSintoma.getDescripcion());
                etFechaHora.setText(oSintoma.getFecha() + " " + oSintoma.getHora());
            }
        }
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
            etFechaHora.setText(fecha + " " + hora);
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

    public interface InterfaceCita {
        void actualizarLista() throws Exception;
    }

    class isOnClickGrabar implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            try {
                Sintoma oSintoma = new Sintoma();
                oSintoma.setDescripcion(etDescripcion.getText().toString());
                oSintoma.setFecha(etFechaHora.getText().toString().substring(0, 5));
                oSintoma.setHora(etFechaHora.getText().toString().substring(6, 11));

                if(codigoSintoma == 0){
                    daoSintoma.insertarSintoma(oSintoma);
                }else{
                    oSintoma.setId(codigoSintoma);
                    daoSintoma.actualizarSintoma(oSintoma);
                }

                interfaceCita.actualizarLista();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
