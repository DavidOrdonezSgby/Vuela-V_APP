package com.example.vuelav_app.Fragments.Favoritos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.vuelav_app.Logico.Response.ReservaResponse;
import com.example.vuelav_app.R;

import java.util.List;


public class FavoritosAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> favorito;

    public FavoritosAdapter(@NonNull Context context, int resource, List<String> favorito) {
        super(context, resource, favorito);
        this.context = context;
        this.favorito = favorito;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_favoritos, parent, false);
        TextView Nomlugar = (TextView) row.findViewById(R.id.txtNombreLugarF);
        TextView idVuelo = (TextView) row.findViewById(R.id.txtvueloid);
        TextView ciudad = (TextView) row.findViewById(R.id.txtCiudadF);
        TextView precio = (TextView) row.findViewById(R.id.txtPrecioF);



        return row;
    }

}