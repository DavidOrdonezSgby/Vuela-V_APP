package com.example.vuelav_app.Fragments.Favoritos;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.example.vuelav_app.Fragments.NotificationFragmentModel.NotificacionesAdaptador;
import com.example.vuelav_app.Logico.Apis;
import com.example.vuelav_app.Logico.Response.VueloResponse;
import com.example.vuelav_app.Logico.SQLite.AdminSQLiteOpenHelper;
import com.example.vuelav_app.Logico.Service.VueloService;
import com.example.vuelav_app.Logico.Token.TokenController;
import com.example.vuelav_app.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Favoritos extends AppCompatActivity {

    ArrayList<VueloResponse> responseArrayList = new ArrayList<>();

    private List<VueloResponse> rv = new ArrayList<VueloResponse>();
    private ListView listviewFavoritos = null;
    FavoritosAdapter adaptador = null;
    VueloService vueloService= Apis.getVueloService();
    private List<String> veces = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        adaptador = new FavoritosAdapter(getApplicationContext(), R.layout.item_favoritos, rv);
        listviewFavoritos = findViewById(R.id.favoritos_list);
        listviewFavoritos.setAdapter(adaptador);

        obdatosSQLite();
        obtVuelos();
    }

    public void obdatosSQLite(){

        AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(getApplicationContext(),"admin",null,1);
        SQLiteDatabase baseDatos=admin.getWritableDatabase();
        Cursor fila=baseDatos.rawQuery("select id_vuelo from guardados where id_usuario="+ TokenController.getId(getApplicationContext()),null);
        if(fila.moveToFirst()){
            for (int i = 0; i < fila.getColumnCount(); i++) {
                System.out.println("Veces"+ fila.getString(i));
                veces.add(fila.getString(i));

            }
        }else{


        }
        baseDatos.close();

    }

    public void obtVuelos(){
        for (int i = 0; i < veces.size(); i++) {
            Call<VueloResponse> call=vueloService.findById(Integer.parseInt(veces.get(i)));
            call.enqueue(new Callback<VueloResponse>() {
                @Override
                public void onResponse(Call<VueloResponse> call, Response<VueloResponse> response) {
                    if(response.isSuccessful()){

                        rv.add(response.body());
                        adaptador.notifyDataSetChanged();

                    }
                }

                @Override
                public void onFailure(Call<VueloResponse> call, Throwable t) {

                }
            });
        }
    }

}