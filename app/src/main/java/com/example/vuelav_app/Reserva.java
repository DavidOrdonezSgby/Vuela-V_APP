package com.example.vuelav_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class Reserva extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        findViewById(R.id.btnReservar_vuelo).setOnClickListener(l->IniciarAccion());
    }

    private void IniciarAccion() {
        Toast.makeText(Reserva.this,"Debe Aceptar los Términos y Condiciones",Toast.LENGTH_LONG).show();
    }
    private void CrearManifiesto(){


    }

    private void Obtenervuelo(){

    }

    private void crearReserva(){

    }
}