package com.example.vuelav_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

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
    private DatePickerDialog btnDate;
    private Button btnDates;
    private Date date1;
        private String fecha2;

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
        findViewById(R.id.btnDate).setOnClickListener(l -> abrirfecha());

    }
    private void fechaelegida() {
       DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                mes = mes + 1;

                 String fecha = anio + "-" + mes + "-" + dia;

                try {
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                    fecha2 = formato.format(fecha);
                    date1 = formato.parse(fecha2) ;
                    System.out.println(date1);
                    System.out.println(fecha);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                btnDates.setText(anio + "    -   " + mes + "     -   " + dia);

            }
        };
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int estilo = AlertDialog.BUTTON_POSITIVE;

        btnDate = new DatePickerDialog(this, estilo, date, anio, mes, dia);
        btnDate.getDatePicker().setMaxDate(System.currentTimeMillis());
    }


    private void abrirfecha() {
        fechaelegida();
        btnDate.show();
    }

    public void crearusuario(View view){
        System.out.println( ":v entro");
        RegisterRequest usuario = new RegisterRequest();
        usuario.setDocIdentificacion(cedula.getText().toString());
        usuario.setNombres(nombre.getText().toString());
        usuario.setApellidos(apellido.getText().toString());
        usuario.setRazonSocial(razonsocial.getText().toString());
       usuario.setFechaNacimiento(date1);
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