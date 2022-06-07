package com.example.vuelav_app.acrividades;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

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
                startActivity(intent);
                finish();
            }
        },13000);
    }
}
