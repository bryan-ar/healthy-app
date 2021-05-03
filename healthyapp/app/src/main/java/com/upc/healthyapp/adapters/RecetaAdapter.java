package com.upc.healthyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.upc.healthyapp.R;
import com.upc.healthyapp.models.CitaModel;
import com.upc.healthyapp.models.Receta;
import com.upc.healthyapp.models.Sintoma;

import java.util.ArrayList;

public class RecetaAdapter extends RecyclerView.Adapter<RecetaAdapter.Viewholder>{
    private Context context;
    private ArrayList<Receta> recetasModelArrayList;

    // Constructor
    public RecetaAdapter(Context context, ArrayList<Receta> recetasModelArrayList) {
        this.context = context;
        this.recetasModelArrayList = recetasModelArrayList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_receta, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        Receta receta = recetasModelArrayList.get(position);
        holder.receta1.setText(receta.getReceta1());
        holder.receta2.setText(receta.getReceta2());
        holder.fecha.setText(receta.getFecha());
        holder.hora.setText(receta.getHora());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return recetasModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView receta1, receta2, fecha, hora;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            receta1 = itemView.findViewById(R.id.receta1);
            receta2 = itemView.findViewById(R.id.receta2);
            fecha = itemView.findViewById(R.id.fechaReceta);
            hora = itemView.findViewById(R.id.horaReceta);
        }
    }
}
