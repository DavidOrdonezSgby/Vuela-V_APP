package com.example.vuelav_app.Fragments.Favoritos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.vuelav_app.Logico.Apis;
import com.example.vuelav_app.Logico.Response.ReservaResponse;
import com.example.vuelav_app.Logico.Response.VueloResponse;
import com.example.vuelav_app.Logico.SQLite.AdminSQLiteOpenHelper;
import com.example.vuelav_app.Logico.Service.VueloService;
import com.example.vuelav_app.Logico.Token.TokenController;
import com.example.vuelav_app.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FavoritosAdapter extends ArrayAdapter<VueloResponse> {

    private Context context;
    private List<VueloResponse> favorito;
    VueloService vueloService= Apis.getVueloService();

    public FavoritosAdapter(@NonNull Context context, int resource, List<VueloResponse> favorito) {
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
        ImageView lugarimg = (ImageView) row.findViewById(R.id.LugarImgF);

        Nomlugar.setText(favorito.get(position).getDestino());
        idVuelo.setText(favorito.get(position).getIdVuelo().toString());
        ciudad.setText(favorito.get(position).getDestino().toString());
        precio.setText(favorito.get(position).getPrecio().toString());

        if(favorito.get(position).getImagen()==null) {
            lugarimg.setImageResource(R.drawable.recentimage1);
        }else{
            lugarimg.setImageBitmap(transformar(favorito.get(position).getImagen()));
        }



        return row;
    }

    public Bitmap transformar(String string){

        String base64Image = string.split(",")[1];

        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;

    }


}