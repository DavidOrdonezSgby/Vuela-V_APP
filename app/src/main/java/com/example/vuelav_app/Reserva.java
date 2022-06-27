package com.example.vuelav_app;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import com.example.vuelav_app.Logico.Models.Usuario.Asiento;
import com.example.vuelav_app.Logico.Request.AsientoRequest;
import com.example.vuelav_app.Logico.Request.PasajeroRequest;
import com.example.vuelav_app.Logico.Response.AsientoResponse;
import com.example.vuelav_app.Logico.Response.PasajeroResponse;
import com.example.vuelav_app.Logico.Response.UsuarioResponse;
import com.example.vuelav_app.Logico.Service.AsientoService;
import com.example.vuelav_app.Logico.Service.PasajeroService;
import com.example.vuelav_app.Logico.Service.UsuarioService;
import com.example.vuelav_app.Logico.Token.TokenController;
import com.example.vuelav_app.R;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuelav_app.Logico.Apis;
import com.example.vuelav_app.Logico.Request.ReservaRequest;
import com.example.vuelav_app.Logico.Response.ReservaResponse;
import com.example.vuelav_app.Logico.Response.VueloResponse;
import com.example.vuelav_app.Logico.Service.ReservaService;
import com.example.vuelav_app.Logico.Service.VueloService;
import com.example.vuelav_app.actividades.SuccessPaymentActivity;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Reserva extends AppCompatActivity {
TextView idreserva, destinoreserva, estadoreserva,fechaidareserva,fechavueltareserva, horallegadareserva,
        fechareserva,horasalidareserva, observacionvuelo,origenreserva,pagoreserva;;
        Button btnReservar_vuelo;
    private int idvuelo;
    VueloService vueloService = Apis.getVueloService();
    ReservaService reservaService=Apis.getReservaService();

    private final static  String CHANNEL_ID="NOFTIFICACION";
    private final static int NOTIFICACION_ID =0;
    private PendingIntent pendingIntent;
    ProgressDialog progressDialog;
    private Date ida, vuelta, actual;
    SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar calendar = Calendar.getInstance();
    Date dateObj = calendar.getTime();
    private int idasiento;
    private String nombreUsuario;
    String formattedDate = dtf.format(dateObj);
    Asiento asiento;


    PasajeroService pasajeroService=Apis.getPasajeroService();
    UsuarioService usuarioService=Apis.getUsuarioService();
    UsuarioResponse usuarioResponse1;

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
        System.out.println("id -> "+getIntent().getIntExtra("id",0));
        obCliente();
        Obtenervuelo();
        btnReservar_vuelo = findViewById(R.id.btnReservar_vuelo);

        btnReservar_vuelo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearReserva();
            }
        });
        setPendingIntent();


        //findViewById(R.id.btnReservar_vuelo).setOnClickListener(l->crearReserva());

    }

    private String transformar(Date date){
        SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return formats.format(date).toString();
    }



    private void obCliente(){
        Call<UsuarioResponse> call=usuarioService.findById("Bearer "+TokenController.getToken(Reserva.this),TokenController.getId(Reserva.this));
        call.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                if(response.isSuccessful()){
                    System.out.println("Linea 125 Ingreso obt cliente");
                    usuarioResponse1=response.body();

                }else{

                    System.out.println(response.message().toString());
                    System.out.println(response.code());

                }
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                System.out.println("Error de llamar al usuario en Reserva-> "+t.toString());
            }
        });
    }


    private void crearPasajero(int idReserva){

        System.out.println("ID ASIENTO --> "+idasiento);
        PasajeroRequest pasajeroRequest=new PasajeroRequest();
        pasajeroRequest.setNombres(usuarioResponse1.getNombres());
        pasajeroRequest.setDocIdentificacion(usuarioResponse1.getDocIdentificacion());
        pasajeroRequest.setEstado(true);
        pasajeroRequest.setEquipaje(500.00);
        pasajeroRequest.setIdReserva(idReserva);
        pasajeroRequest.setIdAsiento(TokenController.getIdAsiento(this));

        Call<PasajeroResponse> call=pasajeroService.create("Bearer "+TokenController.getToken(Reserva.this),pasajeroRequest);
        call.enqueue(new Callback<PasajeroResponse>() {
            @Override
            public void onResponse(Call<PasajeroResponse> call, Response<PasajeroResponse> response) {
                if(response.isSuccessful()){

                    System.out.println("Se creo el pasajero");

                }else{
                    System.out.println("Hay un error");
                    System.out.println(response.message());
                    System.out.println(response.code());
                }
            }

            @Override
            public void onFailure(Call<PasajeroResponse> call, Throwable t) {
                System.out.println("Este es el error al crear el pasajero ->"+t.toString());
            }
        });
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
                    System.out.println("linea 183 Hora salida "+response.body().getHoraSalida());
                    //observacionvuelo.setText(response.body().);

                    ida = response.body().getFechaIda();
                    vuelta = response.body().getFechaVuelta();
                    actual = null;
                }else{

                }
            }
            @Override
            public void onFailure(Call<VueloResponse> call, Throwable t) {

            }
        });
    }


    private void crearReserva(){


        progressDialog = new ProgressDialog(this);
        System.out.println("Procesando");
        System.out.println("token reserva"+TokenController.getToken(this));
        progressDialog.setMessage("Procesando solicitud espere...");
        progressDialog.show();

        System.out.println("IDUSER "+TokenController.getId(this));

        ReservaRequest reservaRequest= new ReservaRequest();

        reservaRequest.setIdUsuario(TokenController.getId(this));
        reservaRequest.setFechaRegistro(formattedDate);
        reservaRequest.setObservacion(observacionvuelo.getText().toString());
        reservaRequest.setIdVuelo(getIntent().getIntExtra("id",0));
        reservaRequest.setDestino(destinoreserva.getText().toString());
        reservaRequest.setEstado(Integer.parseInt(estadoreserva.getText().toString()));
        reservaRequest.setFechaIda(transformar(ida));
        reservaRequest.setFechaVuelta(transformar(vuelta));
        reservaRequest.setHoraLlegada(horallegadareserva.getText().toString());
        reservaRequest.setHoraSalida(horasalidareserva.getText().toString());
        reservaRequest.setOrigen(origenreserva.getText().toString());
        reservaRequest.setPago(true);

        Call<ReservaResponse> call = reservaService.creaReserva("Bearer "+TokenController.getToken(this), reservaRequest);
        call.enqueue(new Callback<ReservaResponse>() {
            @Override
            public void onResponse(Call<ReservaResponse> call, Response<ReservaResponse> response) {
                progressDialog.dismiss();
                System.out.println("Entro al enqueue de la reserva");
                if(response.isSuccessful()){
                    System.out.println("Paso el metodo ob Cliente");
                    System.out.println("Crea el Asiento");
                    crearPasajero(response.body().getIdReserva());
                    createNotificationChannel();
                    Intent intent = new Intent(Reserva.this, SuccessPaymentActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    System.out.println(response.message());
                    System.out.println(response.code());


                }
            }

            @Override
            public void onFailure(Call<ReservaResponse> call, Throwable t) {
                progressDialog.dismiss();
                System.out.println(t.toString()+"<- Este es el error");
            }
        });
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Registro";
            String description = "Gracias por la reservación";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void setPendingIntent(){
        Intent intent = new Intent(this, SesionIniciada.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(SesionIniciada.class);
        stackBuilder.addNextIntent(intent);
        pendingIntent = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}