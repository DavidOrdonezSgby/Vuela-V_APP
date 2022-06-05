package com.example.vuelav_app.ReserveEmergent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.vuelav_app.Fragments.ReserveFragment;
import com.example.vuelav_app.MainActivity;
import com.example.vuelav_app.R;
import com.google.android.material.navigation.NavigationBarView;

public class Destino extends AppCompatActivity implements View.OnClickListener {

    private ImageButton volver;
    ReserveFragment reserveFragment = new ReserveFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destino);

        volver = (ImageButton) findViewById(R.id.imageButtonBackDestino);
        volver.setOnClickListener(this);

    }

    private void goToReserve() {

    }


        @Override
        public void onClick (View view){
            int id = view.getId();
            switch (id) {
                case R.id.imageButtonBackDestino:
                    goToReserve();
                    break;
            }
        }
    }