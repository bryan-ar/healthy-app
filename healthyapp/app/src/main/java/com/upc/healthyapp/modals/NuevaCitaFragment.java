package com.upc.healthyapp.modals;

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
import android.widget.Toast;

import com.upc.healthyapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NuevaCitaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NuevaCitaFragment extends DialogFragment
        implements View.OnClickListener {

    Calendar calendar;
    CalendarView calendarView;
    Button btCerrar;

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
        getDialog().setCanceledOnTouchOutside(false);
        return inflater.inflate(R.layout.fragment_nueva_cita, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, Calendar.NOVEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 9);
        calendar.set(Calendar.YEAR, 2012);


        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.YEAR, 1);


        calendarView = view.findViewById(R.id.calendarView);

        Button btnRange = view.findViewById(R.id.btnRange);
        btnRange.setOnClickListener(this);

        Button btnWithoutAnim = view.findViewById(R.id.btnWithoutAnim);
        btnWithoutAnim.setOnClickListener(this);

        Button btnWithAnim = view.findViewById(R.id.btnWithAnim);
        btnWithAnim.setOnClickListener(this);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                String msg = "Selected date Day: " + i2 + " Month : " + (i1 + 1) + " Year " + i;
                Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();


            }
        });

        btCerrar = view.findViewById(R.id.bt_cerrar);
        btCerrar.setOnClickListener(new isOnClickCerrar());

        super.onViewCreated(view, savedInstanceState);
    }

    class isOnClickCerrar implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            getDialog().dismiss();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnWithAnim:
                calendarView.setDate(calendar.getTimeInMillis(), true, true);
                break;

            case R.id.btnWithoutAnim:
                calendar.set(Calendar.DAY_OF_MONTH, 12);
                calendar.set(Calendar.YEAR, 2016);
                calendar.add(Calendar.MONTH, Calendar.MARCH);
                calendarView.setDate(calendar.getTimeInMillis(), false, false);
                break;

            case R.id.btnRange:

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
                calendar = Calendar.getInstance();
                calendar.set(Calendar.DATE, 25);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                long endOfMonth = calendar.getTimeInMillis();
                calendar = Calendar.getInstance();
                calendar.set(Calendar.DATE, 5);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                long startOfMonth = calendar.getTimeInMillis();
                calendarView.setMaxDate(endOfMonth);
                calendarView.setMinDate(startOfMonth);


                String minDateString = new SimpleDateFormat("MM/dd/yyyy").format(new Date(calendarView.getMinDate()));
                String maxDateString = new SimpleDateFormat("MM/dd/yyyy").format(new Date(calendarView.getMaxDate()));

                Toast.makeText(getActivity(), "MMDDYYYY Min date - " + minDateString + " Max Date is " + maxDateString, Toast.LENGTH_LONG).show();

                break;


        }
    }
}