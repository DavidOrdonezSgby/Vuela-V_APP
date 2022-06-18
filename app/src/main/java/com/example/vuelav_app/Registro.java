package com.example.vuelav_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.vuelav_app.Logico.Apis;
import com.example.vuelav_app.Logico.Models.RegisterRequest;
import com.example.vuelav_app.Logico.Response.UsuarioResponse;
import com.example.vuelav_app.Logico.Service.UsuarioService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registro extends AppCompatActivity {


    private UsuarioService usuarioService = Apis.getUsuarioService();

    private EditText nombre, apellido, cedula ,razonsocial, telefono, email, contra;
    private Button btnDates;
    private DatePickerDialog.OnDateSetListener setListener;
    private String fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        btnDates = (Button) findViewById(R.id.btnDate);
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
                Calendar mCalendar = Calendar.getInstance();
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, month);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayofMoth);
                String selectDate = DateFormat.getDateInstance(DateFormat.FULL).format(mCalendar.getTime());
                btnDates.setText(selectDate);
                System.out.println(selectDate);

                SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                fecha = formats.format(mCalendar.getTime());

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

        usuario.setFechaNacimiento(fecha);

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
                    showRegisterComplete();
                }else{
                    System.out.println("Esta cedula ya esta creada");
                    showRegisterError();
                }
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                System.out.println(t.toString());
                showRegisterError();

            }
        });
    }

    private void showRegisterComplete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogo_confirmacion_registro, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnComplete = view.findViewById(R.id.btnOk);
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SesionIniciada.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }

    private void showRegisterError(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Registro.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogo_error_registro, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnComplete = view.findViewById(R.id.btnOkError);
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}