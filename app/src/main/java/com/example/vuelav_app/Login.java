package com.example.vuelav_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vuelav_app.Logico.Apis;
import com.example.vuelav_app.Logico.Models.RegisterRequest;
import com.example.vuelav_app.Logico.Request.UsuarioRequest;
import com.example.vuelav_app.Logico.Response.UsuarioResponse;
import com.example.vuelav_app.Logico.Service.UsuarioService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button Registro, Iniciar;
    private UsuarioService usuarioService = Apis.getUsuarioService();
    private EditText a1, a2;
    public String cedula;

    public Login() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Registro = (Button) findViewById(R.id.btnRegistrarse);
        Registro.setOnClickListener(this);

        Iniciar = (Button) findViewById(R.id.btnIniciarSesion);
        Iniciar.setOnClickListener(this);

        a1 = (EditText) findViewById(R.id.txtUsername);
        a2 = (EditText) findViewById(R.id.as);

    }

    public void revisarcorreo(RegisterRequest usuario) {
        System.out.println("LOGIN");
        Call<UsuarioResponse> call = usuarioService.login(usuario);
        call.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                if(response.isSuccessful()) {
                    System.out.println("entro y si valio");
                    if (response.body().getEmail().equals(a1.getText().toString())) {
                        System.out.println("Coincide");
                        cedula = response.body().getDocIdentificacion();
                        System.out.println(response.body().getEmail());
                        Intent intent = new Intent(getApplicationContext(), MiCuenta.class);
                        intent.putExtra("token", response.body().getToken());
                        intent.putExtra("cedula", cedula);
                        System.out.println("Cedula login" +response.body().getDocIdentificacion());
                        System.out.println("TOKEN login" +response.body().getToken());
                        startActivity(intent);
                    } else {
                        System.out.println("No coincide");
                    }
                }
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                System.out.println("ERROR RESPONSE");
            }
        });
    }


    private void goToRegistro() {
        Intent intent = new Intent(getApplicationContext(), Registro.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnRegistrarse:
                goToRegistro();
                break;
            case R.id.btnIniciarSesion:
                RegisterRequest usuario=new RegisterRequest(a1.getText().toString(),a2.getText().toString());
                revisarcorreo(usuario);
                break;

        }
    }

}