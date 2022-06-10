package com.example.vuelav_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.vuelav_app.Logico.Apis;
import com.example.vuelav_app.Logico.Response.UsuarioResponse;
import com.example.vuelav_app.Logico.Service.UsuarioService;
import com.example.vuelav_app.Logico.Token.TokenController;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MiCuenta extends AppCompatActivity implements View.OnClickListener {

    private ImageButton regresarInicio;
    private TextView nombre, apellido, fecha, genero, emailU, telefono;
    private UsuarioService usuarioService = Apis.getUsuarioService();
    private Login login = new Login();

    public MiCuenta() {
    }

    private Context context;

    public MiCuenta(Context context){
        this.context=context;
    }

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
        emailU = (TextView) findViewById(R.id.txtEmail);
        telefono = (TextView) findViewById(R.id.txtTelefono);
        System.out.println(getIntent().getStringExtra("cedula"));
        obtusuario(getIntent().getStringExtra("email"));
    }

    public void obtusuario(String email){
        Call<UsuarioResponse> call=usuarioService.findByemail("Bearer "+ new TokenController().getToken(this), email);
        call.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                if(response.isSuccessful()) {
                    nombre.setText(response.body().getNombres().toString());
                    apellido.setText(response.body().getApellidos().toString());
                    fecha.setText(response.body().getFechaNacimiento().toString());
                    genero.setText(response.body().getGenero().toString());
                    emailU.setText(response.body().getEmail().toString());
                    telefono.setText(response.body().getTelefono().toString());
                    System.out.println(response.body().getDocIdentificacion());
                }else{
                    System.out.println("Esta mal :/");
                    System.out.println("Error "+ response.code());
                }
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

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