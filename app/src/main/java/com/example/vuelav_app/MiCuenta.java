package com.example.vuelav_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MiCuenta extends AppCompatActivity implements View.OnClickListener {

    private ImageButton regresarInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_cuenta);

        regresarInicio = (ImageButton) findViewById(R.id.imgRegresar);
        regresarInicio.setOnClickListener(this);
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