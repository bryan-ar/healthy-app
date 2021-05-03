package com.upc.healthyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.upc.healthyapp.R;
import com.upc.healthyapp.models.Sintoma;

import java.util.ArrayList;

public class SintomaAdapter extends RecyclerView.Adapter<SintomaAdapter.MiViewHolder> {
    private Context context;
    private ArrayList<Sintoma> listaSintomas;

    public SintomaAdapter(Context context, ArrayList<Sintoma> listaSintomas){
        this.context = context;
        this.listaSintomas = listaSintomas;
    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_sintoma, parent, false);
        return new MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {
        holder.descripcion.setText(String.valueOf(listaSintomas.get(position).getDescripcion()));
        holder.fecha_hora.setText(listaSintomas.get(position).getFecha() + " " +
                listaSintomas.get(position).getHora());
    }

    @Override
    public int getItemCount() {
        return listaSintomas.size();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder {
        TextView descripcion, fecha_hora;
        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            descripcion = itemView.findViewById(R.id.tvSintoma);
            fecha_hora = itemView.findViewById(R.id.tvHoraSintoma);
        }
    }

    public void actualizarSintomas(ArrayList<Sintoma> loSintomas) {
        this.listaSintomas.clear();
        this.listaSintomas.addAll(loSintomas);
        notifyDataSetChanged();
    }
}
