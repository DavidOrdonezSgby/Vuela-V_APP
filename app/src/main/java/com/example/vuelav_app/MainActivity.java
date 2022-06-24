package com.example.vuelav_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.example.vuelav_app.Fragments.AccountFragment;
import com.example.vuelav_app.Fragments.AccountFragmentModel.MiCuenta;
import com.example.vuelav_app.Fragments.HomeFragment;
import com.example.vuelav_app.Fragments.NotificationFragment;
import com.example.vuelav_app.Fragments.ReserveFragment;
import com.example.vuelav_app.Logico.Token.TokenController;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    NotificationFragment notificationFragment = new NotificationFragment();
    AccountFragment accountFragment = new AccountFragment();
    ReserveFragment reserveFragment = new ReserveFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(TokenController.getToken(this).toString()+"AQUI ESTA EL TOKEN BORRADO");
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();




        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        homeFragment.onResume();
                        return true;
                    case R.id.notification:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, notificationFragment).commit();
                        homeFragment.onPause();
                        return true;
                    case R.id.account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, accountFragment).commit();
                        homeFragment.onPause();
                        return true;
//                    case R.id.reserve:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container, reserveFragment).commit();
//                        homeFragment.onPause();
//                        return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}