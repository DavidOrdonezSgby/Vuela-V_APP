package com.example.vuelav_app.Fragments.ReserveEmergent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.vuelav_app.Fragments.ReserveFragment;
import com.example.vuelav_app.R;

public class Origen extends AppCompatActivity implements View.OnClickListener {

    private ImageButton volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_origen);

        volver = (ImageButton) findViewById(R.id.imageButtonBackOrigen);
        volver.setOnClickListener(this);

    }

    private void goToReserve(){
        Intent intent = new Intent(getApplicationContext(), ReserveFragment.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.imageButtonBackOrigen:
                goToReserve();
                break;
        }
    }
}