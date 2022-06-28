package com.example.vuelav_app.Fragments.Favoritos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
    private List<Integer> veces;
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
        obdatosSQLite();


        return row;
    }


    public void obdatosSQLite(){

        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(getContext(),"admin",null,1);
        SQLiteDatabase baseDatos=admin.getWritableDatabase();
        Cursor fila=baseDatos.rawQuery("select id_vuelo from guardados where id_usuario="+ TokenController.getId(getContext()),null);
        if(fila.moveToFirst()){
            for (int i = 0; i < fila.getColumnCount(); i++) {

                veces.add(Integer.parseInt(fila.getString(i)));

            }
        }else{

        }

    }

    public void obtVuelos(){
        for (int i = 0; i < veces.size(); i++) {
            Call<VueloResponse> call=vueloService.findById(veces.get(i));
            call.enqueue(new Callback<VueloResponse>() {
                @Override
                public void onResponse(Call<VueloResponse> call, Response<VueloResponse> response) {
                    if(response.isSuccessful()){

                        favorito.add(response.body());

                    }
                }

                @Override
                public void onFailure(Call<VueloResponse> call, Throwable t) {

                }
            });
        }
    }


}