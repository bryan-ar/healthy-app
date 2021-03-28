package com.upc.healthyapp.modals;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.upc.healthyapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NuevaCitaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NuevaCitaFragment extends DialogFragment
        implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    Button btCerrar;
    EditText etFecha;

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
        boolean setFullScreen = false;

        if (getArguments() != null) {
            setFullScreen = getArguments().getBoolean("fullScreen");
        }
        if (setFullScreen)
            setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(true);
        return inflater.inflate(R.layout.fragment_nueva_cita, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        etFecha = view.findViewById(R.id.etFecha);
        btCerrar = view.findViewById(R.id.bt_cerrar);

        etFecha.setOnClickListener(new isOnClickCalendar());
        btCerrar.setOnClickListener(new isOnClickCerrar());

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar mCalender = Calendar.getInstance();
        mCalender.set(Calendar.YEAR, year);
        mCalender.set(Calendar.MONTH, month);
        mCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalender.getTime());
        etFecha.setText(selectedDate);
    }

    class isOnClickCalendar implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            DatePicker mDatePickerDialogFragment = new DatePicker();
            mDatePickerDialogFragment.show(getParentFragmentManager(), "DATE PICK");
        }
    }

    class isOnClickCerrar implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            getDialog().dismiss();
        }
    }

    @Override
    public void onClick(View view) {

    }
}