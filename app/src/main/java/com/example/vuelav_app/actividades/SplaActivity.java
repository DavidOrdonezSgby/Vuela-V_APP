package com.example.vuelav_app.actividades;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vuelav_app.Logico.Token.TokenController;
import com.example.vuelav_app.MainActivity;
import com.example.vuelav_app.R;

public class SplaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spla);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplaActivity.this, MainActivity.class);
                TokenController.setToken(SplaActivity.this,"");
                SharedPreferences sharedPreferences = getSharedPreferences("email", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear().commit();
                System.out.println("ELIMINAR SHA" +sharedPreferences.getString("emailShared", "Predefinido"));
                startActivity(intent);
               finish();
               //edy gay
            }
        },3000);
    }
}
