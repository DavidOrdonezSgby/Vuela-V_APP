package com.example.vuelav_app.actividades;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vuelav_app.Logico.Token.TokenController;
import com.example.vuelav_app.MainActivity;
import com.example.vuelav_app.R;

public class SplaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_spla);

        //Animaciones
        Animation animacion1 = AnimationUtils.loadAnimation(this,R.anim.desplazmientoarriba);
        Animation animacion2 = AnimationUtils.loadAnimation(this,R.anim.desplazamientoabajo);

        TextView txtspla1 = findViewById(R.id.txtspla1);
        TextView txtspla2 = findViewById(R.id.txtspla2);
        ImageView logo = findViewById(R.id.imgspla);

        txtspla1.setAnimation(animacion2);
        txtspla2.setAnimation(animacion2);
        logo.setAnimation(animacion1);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplaActivity.this, MainActivity.class);
                TokenController.setToken(SplaActivity.this,"");
                SharedPreferences sharedPreferences = getSharedPreferences("email", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear().commit();
                System.out.println("ELIMINAR SHA" +sharedPreferences.getString("emailShared", "Predefinido"));

                TokenController.setId(SplaActivity.this,0);

                startActivity(intent);
               finish();
            }
        },3000);
    }
}
