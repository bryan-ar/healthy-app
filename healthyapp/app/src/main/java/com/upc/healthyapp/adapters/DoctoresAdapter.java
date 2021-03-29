package com.upc.healthyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.upc.healthyapp.R;
import com.upc.healthyapp.models.DoctorModel;

import java.util.ArrayList;

public class DoctoresAdapter extends RecyclerView.Adapter<DoctoresAdapter.Viewholder> {
    private Context context;
    private ArrayList<DoctorModel> doctoresModelArrayList;

    // Constructor
    public DoctoresAdapter(Context context, ArrayList<DoctorModel> doctoresModelArrayList) {
        this.context = context;
        this.doctoresModelArrayList = doctoresModelArrayList;
    }

    @NonNull
    @Override
    public DoctoresAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_doctor, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        DoctorModel model = doctoresModelArrayList.get(position);
        holder.nombreDoctor.setText(model.getsNombreDoctor());
        holder.especialidad.setText(model.getsEspecialidad());
        Picasso.get().load(doctoresModelArrayList.get(position)
                .getsFotoDoctor()).resize(120, 60)
                .into(holder.fotoDoctor);

    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return doctoresModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView nombreDoctor, especialidad;
        private final ImageView fotoDoctor;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            nombreDoctor = itemView.findViewById(R.id.nombre_doctor);
            especialidad = itemView.findViewById(R.id.especialidad);
            fotoDoctor = itemView.findViewById(R.id.foto_doctor);

        }
    }
}
