package com.example.vuelav_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.vuelav_app.Fragments.AccountFragment;
import com.example.vuelav_app.Fragments.AccountFragmentModel.MiCuenta;
import com.example.vuelav_app.Fragments.HomeFragment;
import com.example.vuelav_app.Fragments.NotificationFragment;
import com.example.vuelav_app.Fragments.NotificationFragmentModel.NotificationSesionIniciada;
import com.example.vuelav_app.Fragments.ReserveFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class SesionIniciada extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    NotificationFragment notificationFragment = new NotificationFragment();
    ReserveFragment reserveFragment = new ReserveFragment();
    MiCuenta miCuenta = new MiCuenta();
    NotificationSesionIniciada notificationSesionIniciada = new NotificationSesionIniciada();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion_iniciada);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.notification:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, notificationSesionIniciada).commit();
                        return true;
                    case R.id.account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, miCuenta).commit();
                        return true;
                    case R.id.reserve:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, reserveFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}