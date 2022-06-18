package com.example.vuelav_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vuelav_app.Fragments.AccountFragmentModel.MiCuenta;
import com.example.vuelav_app.Logico.Apis;
import com.example.vuelav_app.Logico.Request.UsuarioRequest;
import com.example.vuelav_app.Logico.Response.UsuarioResponse;
import com.example.vuelav_app.Logico.Service.UsuarioService;
import com.example.vuelav_app.Logico.Token.TokenController;

import java.util.prefs.Preferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button Registro, Iniciar;
    private UsuarioService usuarioService = Apis.getUsuarioService();
    private EditText a1, a2;
    String m;

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

    public void revisarcorreo(UsuarioRequest usuario) {
        System.out.println("LOGIN");
        Call<UsuarioResponse> call = usuarioService.login(usuario);
        call.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                if(response.isSuccessful()) {
                    System.out.println("entro y si valio");
                    if (response.body().getEmail().equals(a1.getText().toString())) {
                        System.out.println("Coincide");
                        System.out.println(response.body().getEmail());

                        SharedPreferences sharedPreferences = getSharedPreferences("email", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("emailShared", response.body().getEmail());
                        editor.commit();

                        TokenController.setToken(Login.this, response.body().getToken());
                        System.out.println("Cedula login " +response.body().getDocIdentificacion());
                        System.out.println("TOKEN login " +response.body().getToken());
                        //startActivity(intent);
                    } else {
                        System.out.println("No coincide");
                    }
                }
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                System.out.println("ERROR " + t.toString());
            }
        });
    }

    public String obtemail(String i){
        m = i;
        System.out.println("OTBEMAIL "+i);
        return i;
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
                UsuarioRequest usuario = new UsuarioRequest(a1.getText().toString(),a2.getText().toString());
                revisarcorreo(usuario);
                Intent intent = new Intent(getApplicationContext(), SesionIniciada.class);
                startActivity(intent);
                break;

        }
    }
}