package com.example.vuelav_app.Fragments.HomeFragmentModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vuelav_app.actividades.InformacionVuelo;
import com.example.vuelav_app.Logico.Response.VueloResponse;
import com.example.vuelav_app.R;

import java.util.ArrayList;
import java.util.List;

public class RecienteAdaptador extends RecyclerView.Adapter<RecienteAdaptador.RecentsViewHolder> implements Filterable {

    Context context;
    List<VueloResponse> recienteList;
    List<VueloResponse>mDataFiltrable;

    public RecienteAdaptador(Context context, List<VueloResponse> recienteList) {
        this.context = context;
        this.recienteList = recienteList;
        this.mDataFiltrable = recienteList;
    }

    @NonNull
    @Override
    public RecentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recents_row_item,parent,false);
        return new RecentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.ciudadL.setText(mDataFiltrable.get(position).getDestino());
        holder.nombreL.setText(mDataFiltrable.get(position).getOrigen());
        holder.precio.setText(mDataFiltrable.get(position).getPrecio().toString());
        holder.idvuelo.setText(mDataFiltrable.get(position).getIdVuelo().toString());
        if(mDataFiltrable.get(position).getImagen()==null) {
            holder.lugarimg.setImageResource(R.drawable.recentimage1);
        }else{
            holder.lugarimg.setImageBitmap(transformar(mDataFiltrable.get(position).getImagen()));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), InformacionVuelo.class);
                intent.putExtra("idvuelo",mDataFiltrable.get(position).getIdVuelo().toString());
                System.out.println(mDataFiltrable.get(position).getIdVuelo().toString());
                holder.itemView.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataFiltrable.size();
    }

    public Bitmap transformar(String string){

        String base64Image = string.split(",")[1];

        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            return decodedByte;

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String filter = constraint.toString();

                if (filter.isEmpty()){
                    mDataFiltrable = recienteList;
                }else {
                    List<VueloResponse> filtroVuelos = new ArrayList<>();

                    for (VueloResponse row : recienteList){
                        if (row.getOrigen().toLowerCase().contains(filter) || row.getDestino().toLowerCase().contains(filter)){
                            filtroVuelos.add(row);
                        }
                    }
                    mDataFiltrable = filtroVuelos;
                }

                FilterResults results = new FilterResults();
                results.values = mDataFiltrable;

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mDataFiltrable = (ArrayList<VueloResponse>) results.values;
                notifyDataSetChanged();
            }
        };
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
