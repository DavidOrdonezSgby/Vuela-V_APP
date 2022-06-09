package com.example.vuelav_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vuelav_app.Logico.Apis;
import com.example.vuelav_app.Logico.Models.Usuario;
import com.example.vuelav_app.Logico.Models.UsuarioRequest;
import com.example.vuelav_app.Logico.Service.UsuarioService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button Registro, Iniciar;
    private UsuarioService usuarioService = Apis.getUsuarioService();
    private EditText a1, a2;
    public String cedula;

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

    public void revisarcorreo(UsuarioRequest usuario){

        Call<UsuarioRequest> call=usuarioService.login(usuario);
        call.enqueue(new Callback<UsuarioRequest>() {
            @Override
            public void onResponse(Call<UsuarioRequest> call, Response<UsuarioRequest> response) {
                if(response.isSuccessful()){
                    System.out.println("entro y si valio");
                    if(response.body().getEmail().equals(a1.getText().toString())){
                        System.out.println("Coincide");
                        cedula = response.body().getDocIdentificacion();
                        System.out.println(response.body().getEmail());
                        Intent intent = new Intent(getApplicationContext(), MiCuenta.class);
                        intent.putExtra("cedula", cedula);
                        startActivity(intent);
                    }else{
                        System.out.println("No coincide");
                    }

                }
            }

            @Override
            public void onFailure(Call<UsuarioRequest> call, Throwable t) {
                System.out.println("fallooooooooo");
                System.out.println(t.toString());

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
                UsuarioRequest usuario=new UsuarioRequest(a1.getText().toString(),a2.getText().toString());
                revisarcorreo(usuario);
                break;

        }
    }

}