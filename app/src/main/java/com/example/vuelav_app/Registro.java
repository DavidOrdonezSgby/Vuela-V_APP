package com.example.vuelav_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vuelav_app.Logico.Apis;
import com.example.vuelav_app.Logico.Models.RegisterRequest;
import com.example.vuelav_app.Logico.Response.UsuarioResponse;
import com.example.vuelav_app.Logico.Service.UsuarioService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registro extends AppCompatActivity {


    private UsuarioService usuarioService = Apis.getUsuarioService();

    private EditText nombre, apellido, cedula ,razonsocial, telefono, email, contra;
    private Date date1;
    private String fecha2;
    private TextView visualizaFecha;
    private Button btnDates;
    private DatePickerDialog.OnDateSetListener setListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        btnDates = (Button) findViewById(R.id.btnDate);
        visualizaFecha = (TextView) findViewById(R.id.txtVisualizaFecha);
        nombre = (EditText) findViewById(R.id.txtNombreRegistro);
        apellido = (EditText) findViewById(R.id.txtApellidoRegistro);
       cedula = (EditText) findViewById(R.id.txtCedulaRegistro);
        telefono = (EditText) findViewById(R.id.txtCelularRegistro);
        razonsocial = (EditText)findViewById(R.id.txtRazonSocialRegistro);
        email = (EditText) findViewById(R.id.txtUsernameRegistro);
        contra = (EditText) findViewById(R.id.txtPasswordregistro);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        btnDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Registro.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayofMoth) {
                month = month+1;
                String date = year+"/"+month+"/"+day;
                btnDates.setText(date);
            }
        };
    }

    public void crearusuario(View view){
        System.out.println( ":v entro");
        RegisterRequest usuario = new RegisterRequest();
        usuario.setDocIdentificacion(cedula.getText().toString());
        usuario.setNombres(nombre.getText().toString());
        usuario.setApellidos(apellido.getText().toString());
        usuario.setRazonSocial(razonsocial.getText().toString());
        usuario.setFechaNacimiento(date1);
        System.out.println("FECHA REGISTRO"+ date1);
        usuario.setTelefono(telefono.getText().toString());
        usuario.setEmail(email.getText().toString());
        usuario.setClave(contra.getText().toString());

        Call<UsuarioResponse> call=usuarioService.signup(usuario);
        System.out.println("paso los datosd");
        call.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                if(response.isSuccessful()){
                    System.out.println("entro y si valio \n Creadouski");
                }else{
                    System.out.println("Esta cedula ya esta creada");
                }
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                System.out.println(t.toString());

            }
        });
    }
}