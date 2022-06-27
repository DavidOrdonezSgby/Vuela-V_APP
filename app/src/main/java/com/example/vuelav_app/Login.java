package com.example.vuelav_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vuelav_app.Logico.Apis;
import com.example.vuelav_app.Logico.Request.UsuarioRequest;
import com.example.vuelav_app.Logico.Response.UsuarioResponse;
import com.example.vuelav_app.Logico.Service.UsuarioService;
import com.example.vuelav_app.Logico.Token.TokenController;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button Registro, Iniciar;
    private UsuarioService usuarioService = Apis.getUsuarioService();
    private EditText a1, a2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Registro = (Button) findViewById(R.id.btnRegistrarse);
        Registro.setOnClickListener(this);

        Iniciar = (Button) findViewById(R.id.btnIniciarSesion);
        Iniciar.setOnClickListener(this);

        a1 = (EditText) findViewById(R.id.txtUsername);
        a2 = (EditText) findViewById(R.id.password);

    }
    //FALTA UN PROGRES BAR EN MI CUENTA Y LOGIN

    public void revisarcorreo(UsuarioRequest usuario) {
        System.out.println("LOGIN");
        Call<UsuarioResponse> call = usuarioService.login(usuario);
        call.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                if (response.isSuccessful()) {
                    System.out.println("entro y si valio");
                    if (response.body().getEmail().equals(a1.getText().toString())) {
                        System.out.println("Coincide");
                        System.out.println(response.body().getEmail());

                        SharedPreferences sharedPreferences = getSharedPreferences("email", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("emailShared", response.body().getEmail());
                        editor.commit();

                        TokenController.setToken(Login.this, response.body().getToken());
                        TokenController.setId(Login.this, Integer.parseInt(response.body().getId().toString()));
                        System.out.println("ID " + response.body().getId().toString());
                        System.out.println("Cedula login " + response.body().getDocIdentificacion());
                        System.out.println("TOKEN login " + response.body().getToken());

                        Intent intent = new Intent(getApplicationContext(), SesionIniciada.class);
                        startActivity(intent);
                    } else {
                        System.out.println("No coincide");
                    }
                }else{
                    showErrorLogin();
                    System.out.println("No coincide");
                }
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                System.out.println("ERROR " + t.toString());
            }
        });
    }

    private void showErrorLogin(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogo_error_login, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private boolean validar() {
        boolean retorno = true;
        String c1 = a1.getText().toString();
        String c2 = a2.getText().toString();

        if (c1.isEmpty()) {
            a1.setError("Ingresar un usuario");
            retorno = false;
        }
        if (c2.isEmpty()) {
            a2.setError("Ingresar contraseña");
            retorno = false;
        }

        return retorno;
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
                if (!validar()) {
                    Toast.makeText(this, "Ingresar Datos", Toast.LENGTH_SHORT).show();
                } else {
                    UsuarioRequest usuario = new UsuarioRequest(a1.getText().toString(), a2.getText().toString());
                    revisarcorreo(usuario);
                }
                break;

        }
    }
}