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
import com.example.vuelav_app.Logico.Models.Usuario.Usuario;
import com.example.vuelav_app.Logico.Service.UsuarioService;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Registro extends AppCompatActivity {


    private UsuarioService usuarioService = Apis.getUsuarioService();

    private EditText nombre, apellido, cedula ,razonsocial, telefono, email, contra;
    private String fechanacimiento;
    private DatePickerDialog btnDate;
    private Button btnDates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        fechaelegida();
        btnDates = findViewById(R.id.btnDate);
        btnDates.setText(TodosDias());

        nombre = (EditText) findViewById(R.id.txtNombreRegistro);
        apellido = (EditText) findViewById(R.id.txtApellidoRegistro);
       cedula = (EditText) findViewById(R.id.txtCedulaRegistro);
        telefono = (EditText) findViewById(R.id.txtCelularRegistro);
        razonsocial = (EditText)findViewById(R.id.txtRazonSocialRegistro);
        email = (EditText) findViewById(R.id.txtUsernameRegistro);
        contra = (EditText) findViewById(R.id.txtPasswordregistro);

    }
    private String TodosDias() {
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        mes = mes + 1;
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        return obtenerfecha(anio, mes, dia);
    }


    private void fechaelegida() {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                mes = mes + 1;
                String date = obtenerfecha(anio, mes, dia);
                btnDates.setText(date);
                fechanacimiento = date;
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

    private String obtenerfecha(int anio, int mes, int dia) {
        return ""+ anio + "      -     " + Formatomes(mes) + "      -      " + dia ;
    }

    private int Formatomes(int mes) {
        if (mes == 1)
            return 1;
        if (mes == 2)
            return 2;
        if (mes == 3)
            return 3;
        if (mes == 4)
            return 4;
        if (mes == 5)
            return 5;
        if (mes == 6)
            return 6;
        if (mes == 7)
            return 7;
        if (mes == 8)
            return 8;
        if (mes == 9)
            return 9;
        if (mes == 10)
            return 10;
        if (mes == 11)
            return 11;
        if (mes == 12)
            return 12;
        return 6;

    }
    public void abrirfecha(View view) {
        btnDate.show();
    }

    public void crearusuario(View view){
        Usuario usuario = new Usuario();
        usuario.setDoc_identificacion(cedula.getText().toString());
        usuario.setNombres(nombre.getText().toString());
        usuario.setApellidos(apellido.getText().toString());
        usuario.setRazonSocial(razonsocial.getText().toString());
        usuario.setTelefono(telefono.getText().toString());
        usuario.setEmail(email.getText().toString());
        usuario.setClave(contra.getText().toString());

        Call<Usuario> call=usuarioService.signup(usuario);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    System.out.println("entro y si valio \n Creadouski");
                }else{
                    System.out.println("Esta cedula ya esta creada");
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                System.out.println("Fallo el creado mi pana");
                System.out.println(t.toString());

            }
        });

    }
}