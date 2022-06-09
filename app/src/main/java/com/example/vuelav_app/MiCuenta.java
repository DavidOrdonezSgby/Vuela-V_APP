package com.example.vuelav_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.vuelav_app.Logico.Apis;
import com.example.vuelav_app.Logico.Models.Usuario;
import com.example.vuelav_app.Logico.Service.UsuarioService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MiCuenta extends AppCompatActivity implements View.OnClickListener {

    private ImageButton regresarInicio;
    private TextView nombre, apellido, fecha, genero, email, telefono;
    private UsuarioService usuarioService = Apis.getUsuarioService();
    private Login login = new Login();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_cuenta);

        regresarInicio = (ImageButton) findViewById(R.id.imgRegresar);
        regresarInicio.setOnClickListener(this);

        nombre = (TextView) findViewById(R.id.txtNombre);
        apellido = (TextView) findViewById(R.id.txtApellido);
        fecha = (TextView) findViewById(R.id.txtFecha);
        genero = (TextView) findViewById(R.id.txtGenero);
        email = (TextView) findViewById(R.id.txtEmail);
        telefono = (TextView) findViewById(R.id.txtTelefono);
        System.out.println(getIntent().getStringExtra("cedula"));
        obtusuario(getIntent().getStringExtra("cedula"));
    }

    public void obtusuario(String cedula){
        Call<Usuario> call=usuarioService.findByCI(cedula);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()) {
                    System.out.println(response.body().getDoc_identificacion());
                }else{
                    System.out.println("Esta mal :/");
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                System.out.println("Datos obtenidos mal"+ t.toString());
            }
        });
    }

    private void goToHome(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.imgRegresar:
                goToHome();
                break;
        }
    }
}