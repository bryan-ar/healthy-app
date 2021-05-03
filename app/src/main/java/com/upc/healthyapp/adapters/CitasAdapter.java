package com.upc.healthyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.upc.healthyapp.R;
import com.upc.healthyapp.models.CitaModel;

import java.util.ArrayList;
import java.util.List;

public class CitasAdapter extends RecyclerView.Adapter<CitasAdapter.Viewholder> {
    private Context context;
    private List<CitaModel> citasModelArrayList;

    // Constructor


     public CitasAdapter(Context context, List<CitaModel> citasModelArrayList) {
         this.context = context;
         this.citasModelArrayList = citasModelArrayList;
     }

    @NonNull
    @Override
    public CitasAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_citas, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CitasAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        CitaModel model = citasModelArrayList.get(position);
        holder.citaDoctor.setText(model.getsNombreDoctor());
        holder.citaPaciente.setText(model.getsNombrePaciente());
        holder.citaLink.setText(model.getsLinkReunion());
        holder.citaMes.setText(model.getsMes());
        holder.citaDia.setText("" + model.getsDia());
        holder.citaHora.setText(model.getsHora());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return citasModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView citaDoctor, citaPaciente, citaLink;
        private TextView citaMes, citaDia, citaHora;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            citaDoctor = itemView.findViewById(R.id.tvNombreDoctor);
            citaPaciente = itemView.findViewById(R.id.tvNombrePaciente);
            citaLink = itemView.findViewById(R.id.tvLinkReunion);
            citaMes = itemView.findViewById(R.id.tvMes);
            citaDia = itemView.findViewById(R.id.tvDia);
            citaHora = itemView.findViewById(R.id.tvHora);
        }
    }
}
