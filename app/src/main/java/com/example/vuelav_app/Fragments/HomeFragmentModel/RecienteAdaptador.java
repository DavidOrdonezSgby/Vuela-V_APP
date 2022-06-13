package com.example.vuelav_app.Fragments.HomeFragmentModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vuelav_app.R;

import java.util.List;

public class RecienteAdaptador extends RecyclerView.Adapter<RecienteAdaptador.RecentsViewHolder> {

    Context context;
    List<Reciente> recienteList;

    public RecienteAdaptador(Context context, List<Reciente> recienteList) {
        this.context = context;
        this.recienteList = recienteList;
    }

    @NonNull
    @Override
    public RecentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recents_row_item,parent,false);
        return new RecentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentsViewHolder holder, int position) {
        holder.ciudadL.setText(recienteList.get(position).getCiudadLugar());
        holder.nombreL.setText(recienteList.get(position).getNombreLugar());
        holder.precio.setText(recienteList.get(position).getPrecio());
        holder.lugarimg.setImageResource(recienteList.get(position).getImgUrl());
    }

    @Override
    public int getItemCount() {
        return recienteList.size();
    }

    public static final class RecentsViewHolder extends RecyclerView.ViewHolder{

        ImageView lugarimg;
        TextView nombreL, ciudadL, precio;

        public RecentsViewHolder(View itemView){
            super(itemView);

            lugarimg = itemView.findViewById(R.id.LugarImg);
            nombreL = itemView.findViewById(R.id.txtNombreLugar);
            ciudadL = itemView.findViewById(R.id.txtCiudad);
            precio = itemView.findViewById(R.id.txtPrecio);

        }
    }
}
