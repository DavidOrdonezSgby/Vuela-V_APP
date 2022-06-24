package com.example.vuelav_app.Fragments.AccountFragmentModel;

import androidx.fragment.app.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vuelav_app.Logico.Apis;
import com.example.vuelav_app.Logico.Response.UsuarioResponse;
import com.example.vuelav_app.Logico.Service.UsuarioService;
import com.example.vuelav_app.Logico.Token.TokenController;
import com.example.vuelav_app.MainActivity;
import com.example.vuelav_app.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MiCuenta extends Fragment implements View.OnClickListener{

    private TextView nombre, apellido, fecha, genero, emailU, telefono;
    private ImageView cerrar;
    private UsuarioService usuarioService = Apis.getUsuarioService();
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_mi_cuenta, container, false);

        nombre = (TextView) view.findViewById(R.id.txtNombre);
        apellido = (TextView) view.findViewById(R.id.txtApellido);
        fecha = (TextView) view.findViewById(R.id.txtFecha);
        genero = (TextView) view.findViewById(R.id.txtGenero);
        emailU = (TextView) view.findViewById(R.id.txtEmail);
        telefono = (TextView) view.findViewById(R.id.txtTelefono);
        cerrar = (ImageView) view.findViewById(R.id.ic_cerrar_click);
        cerrar.setOnClickListener(this);

        obtusuario(cargarDatos());

        return view;
    }

    public void obtusuario(String email){
        Call<UsuarioResponse> call=usuarioService.findByemail("Bearer "+ new TokenController().getToken(getActivity()), email);
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

    public String cargarDatos(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("email", Context.MODE_PRIVATE);
        System.out.println("MI CUENTA SHARED "+sharedPreferences.getString("emailShared", "Predefinido"));
        return sharedPreferences.getString("emailShared", "Predefinido");
    }

    private void salir(){
        progressDialog = new ProgressDialog(getContext());
        TokenController.setId(getContext().getApplicationContext(), 0);
        TokenController.setToken(getContext().getApplicationContext(), "");
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("email", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear().commit();
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
        progressDialog.setMessage("Cerrando sesión...");
        progressDialog.show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.ic_cerrar_click:
                salir();
                break;
        }
    }
}