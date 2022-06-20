package com.example.vuelav_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuelav_app.Logico.Apis;
import com.example.vuelav_app.Logico.Response.VueloResponse;
import com.example.vuelav_app.Logico.Service.VueloService;
import com.example.vuelav_app.Logico.Token.TokenController;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformacionVuelo extends AppCompatActivity {
    TextView viewidvuelo, viewdestinovuelo, viewestadovuelo,viewfechaidavuelo,viewfechavueltavuelo, viewhorallegadavuelo,
            viewhorasalidavuelo, viewpreciovuelo,viewtipovuelo,viewavionvuelo;
    ImageView imagenvuelo;
    VueloService vueloService = Apis.getVueloService();
    int datoLong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_vuelo2);
        viewidvuelo = findViewById(R.id.viewid_informacionvuelo);
        viewdestinovuelo = findViewById(R.id.viewdestino_informacionvuelo);
        viewestadovuelo = findViewById(R.id.viewestado_informacionvuelo);
        viewfechaidavuelo = findViewById(R.id.viewFechaIda_InformacionVuelo);
        viewfechavueltavuelo = findViewById(R.id.viewFechaVuelta_InformacionVuelo);
        viewhorallegadavuelo = findViewById(R.id.viewHoraLlegada_InformacionVuelo);
        viewhorasalidavuelo = findViewById(R.id.viewHorasalida__InformacionVuelo);
        viewpreciovuelo = findViewById(R.id.viewPrecio_InformacionVuelo);
        viewtipovuelo = findViewById(R.id.ViewTipoVuelo_InformacionVuelo);
        viewavionvuelo = findViewById(R.id.viewAvion_InformacionVuelo);
        imagenvuelo = findViewById(R.id.img_informacionvuelo);

        datoLong = Integer.parseInt(getIntent().getStringExtra("idvuelo"));
        viewidvuelo.setText(getIntent().getStringExtra("idvuelo"));
        Obtenerdatos(datoLong);
        System.out.println(getIntent().getStringExtra("idvuelo"));

        findViewById(R.id.btnReservar_InformacionVuelo).setOnClickListener(l -> irReserva());

    }
    private void Obtenerdatos(int aLong){
        Call<VueloResponse> vueloResponseCall= vueloService.findById(aLong);
        vueloResponseCall.enqueue(new Callback<VueloResponse>() {
            @Override
            public void onResponse(Call<VueloResponse> call, Response<VueloResponse> response) {
                if(response.isSuccessful()){
                    viewdestinovuelo.setText(response.body().getDestino());
                    viewestadovuelo.setText(response.body().getEstado().toString());
                    viewfechaidavuelo.setText(response.body().getFechaIda().toString());
                    viewfechavueltavuelo.setText(response.body().getFechaVuelta().toString());
                    viewhorallegadavuelo.setText(response.body().getHoraLlegada());
                    viewhorasalidavuelo.setText(response.body().getHoraSalida());
                    viewpreciovuelo.setText(response.body().getPrecio().toString());
                    viewtipovuelo.setText(response.body().getIdTipoVuelo().toString());
                    viewavionvuelo.setText(response.body().getIdAvion().toString());
                    imagenvuelo.setImageResource(R.drawable.img2);

                    System.out.println("CONSIGUIO LOS DATOS");
                }else {
                    System.out.println("No Consiguio los datos");
                    System.out.println(response.code());
                }
            }

            @Override
            public void onFailure(Call<VueloResponse> call, Throwable t) {
                System.out.println(t.toString()+"<- Este es el error ");
            }
        });
    }
    private void irReserva(){
        if (TokenController.getToken(InformacionVuelo.this).equals("")) {

            Toast.makeText(this,"Para la Reserva, Necesita Iniciar Sesion",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(getApplicationContext(), Reserva.class);
            intent.putExtra("id",datoLong);
            System.out.println("Info -> " + datoLong);
            startActivity(intent);
        }
    }
}