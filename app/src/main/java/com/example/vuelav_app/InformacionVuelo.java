package com.example.vuelav_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuelav_app.Logico.Apis;
import com.example.vuelav_app.Logico.Request.AsientoRequest;
import com.example.vuelav_app.Logico.Response.AsientoResponse;
import com.example.vuelav_app.Logico.Response.PasajeroResponse;
import com.example.vuelav_app.Logico.Response.VueloResponse;
import com.example.vuelav_app.Logico.SQLite.AdminSQLiteOpenHelper;
import com.example.vuelav_app.Logico.Service.AsientoService;
import com.example.vuelav_app.Logico.Service.VueloService;
import com.example.vuelav_app.Logico.Token.TokenController;

import java.nio.charset.Charset;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformacionVuelo extends AppCompatActivity implements View.OnClickListener{
    TextView viewidvuelo, viewdestinovuelo, viewestadovuelo,viewfechaidavuelo,viewfechavueltavuelo, viewhorallegadavuelo,
            viewhorasalidavuelo, viewpreciovuelo,viewtipovuelo,viewavionvuelo;
    ImageView imagenvuelo;
    VueloService vueloService = Apis.getVueloService();
    private int datoLong;
    Button btnlight, btnnormal, btnpremium;
    AsientoService asientoService=Apis.getAsientoService();
    private int idasiento;
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

        btnlight = (Button) findViewById(R.id.btn_light);
        btnlight.setOnClickListener(this);
        btnnormal = (Button) findViewById(R.id.btn_normal);
        btnnormal.setOnClickListener(this);
        btnpremium = (Button) findViewById(R.id.btn_premium);
        btnpremium.setOnClickListener(this);

       findViewById(R.id.btnAgregarFavoritos).setOnClickListener(l->guardarfavoritos());

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

    private void crearAsiento(){



    }

    private String getRandomString(int i)
    {

        // bind the length
        byte[] bytearray;
        bytearray = new byte[256];
        String mystring;
        StringBuffer thebuffer;
        String theAlphaNumericS;

        new Random().nextBytes(bytearray);

        mystring
                = new String(bytearray, Charset.forName("UTF-8"));

        thebuffer = new StringBuffer();

        //remove all spacial char
        theAlphaNumericS
                = mystring
                .replaceAll("[^A-Z0-9]", "");

        //random selection
        for (int m = 0; m < theAlphaNumericS.length(); m++) {

            if (Character.isLetter(theAlphaNumericS.charAt(m))
                    && (i > 0)
                    || Character.isDigit(theAlphaNumericS.charAt(m))
                    && (i > 0)) {

                thebuffer.append(theAlphaNumericS.charAt(m));
                i--;
            }
        }

        // the resulting string
        return thebuffer.toString();
    }


    private void guardarfavoritos(){
        if(!TokenController.getToken(this).equals("")) {
            AdminSQLiteOpenHelper admin= new AdminSQLiteOpenHelper(this,"base",null,1);
            SQLiteDatabase baseDatos=admin.getWritableDatabase();
            Cursor fila=baseDatos.rawQuery("select id from usuario where id="+TokenController.getId(this),null);
            if(fila.moveToFirst()){
                System.out.println("y esta creado");
            }else{
                ContentValues cv = new ContentValues();
                cv.put("id",TokenController.getId(this));
                baseDatos.insert("usuario",null,cv);
            }

            Cursor fila2=baseDatos.rawQuery("select id from vuelo where id="+datoLong,null);
            if(fila2.moveToFirst()){
                System.out.println();
            }else{
                ContentValues cv = new ContentValues();
                cv.put("id",datoLong);
                baseDatos.insert("vuelo",null,cv);
            }

            Cursor fila3=baseDatos.rawQuery("select id_vuelo from guardados where id_vuelo="+datoLong,null);
            if(fila3.moveToFirst()){
                System.out.println("ya existe esta reserva");
            }else{
                ContentValues cv3=new ContentValues();
                cv3.put("id_usuario",TokenController.getId(this));
                cv3.put("id_vuelo",datoLong);
                baseDatos.insert("guardados",null,cv3);
                baseDatos.close();
            }

        }else{
            Toast.makeText(this,"Para guardar en favoritos, Necesita Iniciar Sesion",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        System.out.println("Entro obn click");
        switch (id) {
            case R.id.btn_light:
                System.out.println("Entro en ejecucion");
                if(!TokenController.getToken(this).equals("")){
                    AsientoRequest asientoRequest=new AsientoRequest();
                    asientoRequest.setNombre(getRandomString(1));
                    asientoRequest.setEstado(1);
                    asientoRequest.setIdAvion(1);
                    asientoRequest.setPrecio(500.00);
                    Call<AsientoResponse> call=asientoService.create("Bearer "+TokenController.getToken(this),asientoRequest);
                    call.enqueue(new Callback<AsientoResponse>() {
                        @Override
                        public void onResponse(Call<AsientoResponse> call, Response<AsientoResponse> response) {
                            if(response.isSuccessful()){
                                idasiento=Integer.parseInt(response.body().getIdAsiento().toString());
                                System.out.println("este es el id asiento .---->"+idasiento);
                                System.out.println("Creo el asiento correcto");
                                Intent intent = new Intent(getApplicationContext(), Reserva.class);
                                System.out.println("EL asiento <<<>>>>>>"+response.body().getIdAsiento());
                                intent.putExtra("id",datoLong);
                                Long id = response.body().getIdAsiento();
                                intent.putExtra("idasiento",id.toString());
                                intent.putExtra("precio",100.00);
                                System.out.println("Info -> " + datoLong);
                                startActivity(intent);
                            }else{
                                System.out.println("No se creo el asiento");
                            }
                        }

                        @Override
                        public void onFailure(Call<AsientoResponse> call, Throwable t) {
                            System.out.println("El error es este-> "+t.toString());
                        }
                    });

                }else{
                    Toast.makeText(this,"Para reservar su vuelo, Necesita Iniciar Sesion",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                }
                break;
                case R.id.btn_normal:
                    if(!TokenController.getToken(this).equals("")){
                        AsientoRequest asientoRequest=new AsientoRequest();
                        asientoRequest.setNombre(getRandomString(1));
                        asientoRequest.setEstado(1);
                        asientoRequest.setIdAvion(1);
                        asientoRequest.setPrecio(500.00);
                        Call<AsientoResponse> call=asientoService.create("Bearer "+TokenController.getToken(this),asientoRequest);
                        call.enqueue(new Callback<AsientoResponse>() {
                            @Override
                            public void onResponse(Call<AsientoResponse> call, Response<AsientoResponse> response) {
                                if(response.isSuccessful()){
                                    idasiento=Integer.parseInt(response.body().getIdAsiento().toString());
                                    System.out.println("este es el id asiento .---->"+idasiento);
                                    System.out.println("Creo el asiento correcto");
                                    Intent intent = new Intent(getApplicationContext(), Reserva.class);
                                    intent.putExtra("id",datoLong);
                                    intent.putExtra("idasiento",response.body().getIdAsiento());
                                    intent.putExtra("precio",100.00);
                                    System.out.println("Info -> " + datoLong);
                                    startActivity(intent);
                                }else{
                                    System.out.println("No se creo el asiento");
                                }
                            }

                            @Override
                            public void onFailure(Call<AsientoResponse> call, Throwable t) {
                                System.out.println("El error es este-> "+t.toString());
                            }
                        });

                    }else{
                        Toast.makeText(this,"Para reservar su vuelo, Necesita Iniciar Sesion",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), Login.class);
                        startActivity(intent);
                    }
                    break;

                    case R.id.btn_premium:

                        if(!TokenController.getToken(this).equals("")){
                            AsientoRequest asientoRequest=new AsientoRequest();
                            asientoRequest.setNombre(getRandomString(1));
                            asientoRequest.setEstado(1);
                            asientoRequest.setIdAvion(1);
                            asientoRequest.setPrecio(500.00);
                            Call<AsientoResponse> call=asientoService.create("Bearer "+TokenController.getToken(this),asientoRequest);
                            call.enqueue(new Callback<AsientoResponse>() {
                                @Override
                                public void onResponse(Call<AsientoResponse> call, Response<AsientoResponse> response) {
                                    if(response.isSuccessful()){
                                        idasiento=Integer.parseInt(response.body().getIdAsiento().toString());
                                        System.out.println("este es el id asiento .---->"+idasiento);
                                        System.out.println("Creo el asiento correcto");
                                        Intent intent = new Intent(getApplicationContext(), Reserva.class);
                                        intent.putExtra("id",datoLong);
                                        intent.putExtra("idasiento",response.body().getIdAsiento());
                                        intent.putExtra("precio",100.00);
                                        System.out.println("Info -> " + datoLong);
                                        startActivity(intent);
                                    }else{
                                        System.out.println("No se creo el asiento");
                                    }
                                }

                                @Override
                                public void onFailure(Call<AsientoResponse> call, Throwable t) {
                                    System.out.println("El error es este-> "+t.toString());
                                }
                            });

                        }else{
                            Toast.makeText(this,"Para reservar su vuelo, Necesita Iniciar Sesion",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            startActivity(intent);
                        }

                        break;
        }
    }
}