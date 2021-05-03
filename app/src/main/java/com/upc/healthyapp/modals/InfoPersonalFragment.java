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

import com.upc.healthyapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoPersonalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoPersonalFragment extends DialogFragment {

    Button btCerrar;

    public static InfoPersonalFragment newInstance() {
        InfoPersonalFragment fragment = new InfoPersonalFragment();
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        return inflater.inflate(R.layout.fragment_info_personal, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

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
}