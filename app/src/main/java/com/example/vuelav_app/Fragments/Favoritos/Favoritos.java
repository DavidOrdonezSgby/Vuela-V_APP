package com.example.vuelav_app.Fragments.Favoritos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.vuelav_app.Fragments.NotificationFragmentModel.NotificacionesAdaptador;
import com.example.vuelav_app.Logico.Models.Usuario.Vuelo;
import com.example.vuelav_app.Logico.Response.ReservaResponse;
import com.example.vuelav_app.Logico.Response.VueloResponse;
import com.example.vuelav_app.R;

import java.util.ArrayList;
import java.util.List;

public class Favoritos extends AppCompatActivity {

    //ArrayList<String> strings = new ArrayList<>();

    List<VueloResponse> rv = new ArrayList<VueloResponse>();
    private ListView listviewFavoritos = null;
    FavoritosAdapter adaptador = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        adaptador = new NotificacionesAdaptador(getApplicationContext(), R.layout.item_favoritos, rv);
        listviewFavoritos = findViewById(R.id.favoritos_list);
    }
}