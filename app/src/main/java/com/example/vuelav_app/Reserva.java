package com.example.vuelav_app;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuelav_app.Logico.Apis;
import com.example.vuelav_app.Logico.Request.ReservaRequest;
import com.example.vuelav_app.Logico.Response.ReservaResponse;
import com.example.vuelav_app.Logico.Response.VueloResponse;
import com.example.vuelav_app.Logico.Service.ReservaService;
import com.example.vuelav_app.Logico.Service.VueloService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Reserva extends AppCompatActivity {
TextView idreserva, destinoreserva, estadoreserva,fechaidareserva,fechavueltareserva, horallegadareserva,
        fechareserva,horasalidareserva, observacionvuelo,origenreserva,pagoreserva;;
private int idvuelo;
        VueloService vueloService = Apis.getVueloService();
        ReservaService reservaService=Apis.getReservaService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        idreserva= findViewById(R.id.viewid_reserva);
        destinoreserva = findViewById(R.id.viewDestinovuelo);
        estadoreserva = findViewById(R.id.viewestadoreserva);
        fechaidareserva = findViewById(R.id.viewFechaIda_Vuelo);
        fechareserva = findViewById(R.id.viewFechareserva_Vuelo);
        fechavueltareserva = findViewById(R.id.viewFechaVuelta_Vuelo);
        horallegadareserva = findViewById(R.id.viewHoraLlegada_Vuelo);
        horasalidareserva = findViewById(R.id.viewHorasalida_Vuelo);
        observacionvuelo = findViewById(R.id.viewobservación_vuelo);
        origenreserva = findViewById(R.id.viewOrigen_Vuelo);
        System.out.println("edi gay -> "+getIntent().getIntExtra("id",0));
        Obtenervuelo();




        findViewById(R.id.btnReservar_vuelo).setOnClickListener(l->crearReserva());
    }

    private void IniciarAccion() {
        Toast.makeText(Reserva.this,"Debe Aceptar los Términos y Condiciones",Toast.LENGTH_LONG).show();
    }

    private void Obtenervuelo(){
        Call<VueloResponse> vueloResponseCall=vueloService.findById(getIntent().getIntExtra("id",0));
        vueloResponseCall.enqueue(new Callback<VueloResponse>() {
            @Override
            public void onResponse(Call<VueloResponse> call, Response<VueloResponse> response) {
                if(response.isSuccessful()){
                    idvuelo = Integer.parseInt(response.body().getIdVuelo().toString());
                    destinoreserva.setText(response.body().getDestino());
                    estadoreserva.setText(response.body().getEstado().toString());
                    fechaidareserva.setText(response.body().getFechaIda().toString());
                    //fechareserva.setText(response.body().ge);
                    fechavueltareserva.setText(response.body().getFechaVuelta().toString());
                    horallegadareserva.setText(response.body().getHoraLlegada());
                    horasalidareserva.setText(response.body().getHoraSalida());
                    //observacionvuelo.setText(response.body().);
                    origenreserva.setText(response.body().getOrigen());
                }else{

                }
            }
            @Override
            public void onFailure(Call<VueloResponse> call, Throwable t) {

            }
        });
    }


    private void crearReserva(){
        ReservaRequest reservaRequest= new ReservaRequest();
        reservaRequest.setIdVuelo(idvuelo);
        reservaRequest.setDestino(destinoreserva.getText().toString());
        reservaRequest.setEstado(Long.valueOf(estadoreserva.getText().toString()));
        //reservaRequest.setFechaIda();
        reservaRequest.setHoraLlegada(horallegadareserva.getText().toString());
        reservaRequest.setHoraSalida(horasalidareserva.getText().toString());
        reservaRequest.setOrigen(origenreserva.getText().toString());
        reservaRequest.setPago(true);

        Call<ReservaResponse> call = reservaService.Create(reservaRequest);
        call.enqueue(new Callback<ReservaResponse>() {
            @Override
            public void onResponse(Call<ReservaResponse> call, Response<ReservaResponse> response) {
                if(response.isSuccessful()){
                    System.out.println("Obtuvo los datos");
                }else{
                    System.out.println("No Obtuvo los datos");
                }
            }

            @Override
            public void onFailure(Call<ReservaResponse> call, Throwable t) {

                System.out.println(t.toString()+"<- Este es el error");
            }
        });
    }
}