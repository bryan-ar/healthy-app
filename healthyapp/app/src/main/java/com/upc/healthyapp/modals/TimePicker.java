package com.upc.healthyapp.modals;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.upc.healthyapp.R;

import java.util.Calendar;

public class TimePicker extends DialogFragment {
    TimePickerDialog.OnTimeSetListener onTimeSet;

    public static TimePicker newInstance(){
        TimePicker frag = new TimePicker();
        return frag;
    }

    public TimePicker(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar mCalender = Calendar.getInstance();
        int hour = mCalender.get(Calendar.HOUR);
        int minute = mCalender.get(Calendar.MINUTE);
        return new TimePickerDialog(getActivity(), R.style.DialogTheme, onTimeSet, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

    public void setCallBack(TimePickerDialog.OnTimeSetListener onTime) {
        onTimeSet = onTime;
    }
}
