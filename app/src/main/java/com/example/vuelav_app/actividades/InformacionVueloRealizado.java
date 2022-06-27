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
import com.example.vuelav_app.Logico.Response.VueloResponse;
import com.example.vuelav_app.Logico.Service.VueloService;
import com.example.vuelav_app.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformacionVueloRealizado extends AppCompatActivity {

    VueloService vueloService= Apis.getVueloService();
    TextView idavion,idvuelo,estadovuelo,fechareserva,destino,origen,
            fechaida, fechavuelta,precio,tipovuelo;
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
        hola=findViewById(R.id.img_informacionvuelorealizado);
        ObtenerVuelos(Integer.parseInt(getIntent().getStringExtra("idVuelo")));
    }

    private void ObtenerVuelos(int aLong){
        Call<VueloResponse> vueloResponseCall= vueloService.findById(aLong);
        vueloResponseCall.enqueue(new Callback<VueloResponse>() {
            @Override
            public void onResponse(Call<VueloResponse> call, Response<VueloResponse> response) {
                if(response.isSuccessful()){

                    idavion.setText(response.body().getIdAvion().toString());
                    idvuelo.setText(response.body().getIdVuelo().toString());

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


    public Bitmap transformar(String string){

        String base64Image = string.split(",")[1];

        byte[] decodedString = Base64.decode(base64Image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;

    }

}