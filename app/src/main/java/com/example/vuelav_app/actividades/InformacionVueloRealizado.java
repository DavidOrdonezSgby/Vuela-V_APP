package com.example.vuelav_app.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuelav_app.Logico.Apis;
import com.example.vuelav_app.Logico.Response.ReservaResponse;
import com.example.vuelav_app.Logico.Response.VueloResponse;
import com.example.vuelav_app.Logico.Service.ReservaService;
import com.example.vuelav_app.Logico.Service.VueloService;
import com.example.vuelav_app.Logico.Token.TokenController;
import com.example.vuelav_app.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformacionVueloRealizado extends AppCompatActivity {

    VueloService vueloService= Apis.getVueloService();
    TextView idavion,idvuelo,estadovuelo,fechareserva,destino,origen,
            fechaida, fechavuelta,precio,tipovuelo, salida, llegada;
    ImageView hola;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_vuelo_realizado);
        idavion=findViewById(R.id.viewAvion_InformacionVuelorealizado);
        idvuelo=findViewById(R.id.viewid_informacionvuelorealizado);
        estadovuelo=findViewById(R.id.viewestado_informacionvuelorealizado);
        fechareserva=findViewById(R.id.ViewFechaReserva_InformacionVuelorealizado);
        destino=findViewById(R.id.viewdestino_informacionvuelorealizado);
        origen=findViewById(R.id.viewOrigen_InformacionVuelorealizado);
        fechaida=findViewById(R.id.viewFechaIda_InformacionVuelorealizado);
        fechavuelta=findViewById(R.id.viewFechaVuelta_InformacionVuelorealizado);
        precio=findViewById(R.id.viewPrecio_InformacionVuelorealizado);
        tipovuelo=findViewById(R.id.ViewTipoVuelo_InformacionVuelorealizado);
        salida = findViewById(R.id.viewHorasalida_InformacionVuelorealizado);
        llegada= findViewById(R.id.viewHoraLlegada_InformacionVuelorealizado);
        hola=findViewById(R.id.img_informacionvuelorealizado);
        ObtenerVuelos(getIntent().getIntExtra("idVuelo",0));
        obtenerfechaR(getIntent().getIntExtra("idReserva",0));
    }

    private String transformarD(Date date) {
        SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return formats.format(date).toString();
    }

    private void ObtenerVuelos(int aLong){
        Call<VueloResponse> vueloResponseCall= vueloService.findById(aLong);
        vueloResponseCall.enqueue(new Callback<VueloResponse>() {
            @Override
            public void onResponse(Call<VueloResponse> call, Response<VueloResponse> response) {
                if(response.isSuccessful()){

                    idavion.setText(response.body().getIdAvion().toString());
                    idvuelo.setText(response.body().getIdVuelo().toString());
                    destino.setText(response.body().getDestino().toString());
                    origen.setText(response.body().getOrigen().toString());
                    salida.setText(response.body().getHoraSalida().toString());
                    llegada.setText(response.body().getHoraLlegada().toString());
                    precio.setText(response.body().getPrecio().toString());
                    tipovuelo.setText(response.body().getIdTipoVuelo().toString());
                    fechaida.setText(transformarD(response.body().getFechaIda()));
                    fechavuelta.setText(transformarD(response.body().getFechaVuelta()));
                    estadovuelo.setText(response.body().getEstado().toString());

                    if(response.body().getImagen()==null) {
                        hola.setImageResource(R.drawable.img2);
                    }else{
                        hola.setImageBitmap(transformar(response.body().getImagen()));
                    }

                    Toast.makeText(getApplicationContext(), "Deslice para revisar la Información", Toast.LENGTH_SHORT).show();
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

    private void obtenerfechaR(int r){
        Call<ReservaResponse> call=Apis.getReservaService().obtReservacionId("Bearer "+TokenController.getToken(this),r);
        call.enqueue(new Callback<ReservaResponse>() {
            @Override
            public void onResponse(Call<ReservaResponse> call, Response<ReservaResponse> response) {
                if(response.isSuccessful()){
                    fechareserva.setText(transformarD(response.body().getFechaRegistro()));
                }
            }

            @Override
            public void onFailure(Call<ReservaResponse> call, Throwable t) {

            }
        });
    }


    public Bitmap transformar(String string){

        String base64Image = string.split(",")[1];

        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;

    }

}