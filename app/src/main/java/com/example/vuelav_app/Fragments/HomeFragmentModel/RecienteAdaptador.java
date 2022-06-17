package com.example.vuelav_app.Fragments.HomeFragmentModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vuelav_app.InformacionVuelo;
import com.example.vuelav_app.Logico.Response.VueloResponse;
import com.example.vuelav_app.R;

import java.util.List;

public class RecienteAdaptador extends RecyclerView.Adapter<RecienteAdaptador.RecentsViewHolder> {

    Context context;
    List<VueloResponse> recienteList;

    public RecienteAdaptador(Context context, List<VueloResponse> recienteList) {
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
    public void onBindViewHolder(@NonNull RecentsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.ciudadL.setText(recienteList.get(position).getDestino());
        holder.nombreL.setText(recienteList.get(position).getOrigen());
        holder.precio.setText(recienteList.get(position).getPrecio().toString());
        holder.idvuelo.setText(recienteList.get(position).getIdVuelo().toString());
        holder.lugarimg.setImageResource(R.drawable.recentimage1);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), InformacionVuelo.class);
                intent.putExtra("idvuelo",recienteList.get(position).getIdVuelo().toString());
                System.out.println(recienteList.get(position).getIdVuelo().toString());
                holder.itemView.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return recienteList.size();
    }

    public static final class RecentsViewHolder extends RecyclerView.ViewHolder{

        ImageView lugarimg;
        TextView nombreL, ciudadL, precio, idvuelo;

        public RecentsViewHolder(View itemView){
            super(itemView);

            lugarimg = itemView.findViewById(R.id.LugarImg);
            nombreL = itemView.findViewById(R.id.txtNombreLugar);
            ciudadL = itemView.findViewById(R.id.txtCiudad);
            precio = itemView.findViewById(R.id.txtPrecio);
            idvuelo=itemView.findViewById(R.id.txtvueloid);

        }
    }
}
