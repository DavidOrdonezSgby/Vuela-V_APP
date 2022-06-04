package com.example.vuelav_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;

public class Registro extends AppCompatActivity {

    String fnacimiento,edadp;

    private DatePickerDialog btnDate;
    private Button btnDates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        fechaelegida();
        btnDates = findViewById(R.id.btnDate);
        btnDates.setText(TodosDias());


    }
    private String TodosDias() {
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        mes = mes + 1;
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        return obtenerfecha(dia, mes, anio);
    }


    private void fechaelegida() {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int anio, int mes, int dia) {
                mes = mes + 1;
                String date = obtenerfecha(dia, mes, anio);
                fnacimiento = date;
                btnDates.setText(date);
                Integer an = Integer.valueOf(anio);
            }
        };
        Calendar cal = Calendar.getInstance();
        int anio = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);

        int estilo = AlertDialog.THEME_HOLO_LIGHT;

        btnDate = new DatePickerDialog(this, estilo, date, anio, mes, dia);
        btnDate.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String obtenerfecha(int dia, int mes, int anio) {
        return Formatomes(mes) + " " + dia + " " + anio;
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
}