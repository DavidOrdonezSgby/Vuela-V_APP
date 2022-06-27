package com.example.vuelav_app.actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vuelav_app.R;

import com.google.android.material.textfield.TextInputEditText;

public class SuccessPaymentActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView card_number;
    private TextView card_expire;
    private TextView card_cvv;
    private TextView card_name;

    private TextInputEditText et_card_number;
    private TextInputEditText et_expire;
    private TextInputEditText et_cvv;
    private TextInputEditText et_name;
    Button btnFinish;
    private PendingIntent pendingIntent;
    private final static  String CHANNEL_ID="NOFTIFICACION";
    private final static int NOTIFICACION_ID =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_payment);

        card_number = (TextView) findViewById(R.id.card_number);
        card_expire = (TextView) findViewById(R.id.card_expire);
        card_cvv = (TextView) findViewById(R.id.card_cvv);
        card_name = (TextView) findViewById(R.id.card_name);

        et_card_number = (TextInputEditText) findViewById(R.id.et_card_number);
        et_expire = (TextInputEditText) findViewById(R.id.et_expire);
        et_cvv = (TextInputEditText) findViewById(R.id.et_cvv);
        et_name = (TextInputEditText) findViewById(R.id.et_name);
        btnFinish = findViewById(R.id.btnFinish);

        btnFinish.setOnClickListener(this);

        et_card_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int count) {
                if (charSequence.toString().trim().length() == 0) {
                    card_number.setText("**** **** **** ****");
                } else {
                    String number = insertPeriodically(charSequence.toString().trim(), " ", 4);
                    card_number.setText(number);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et_expire.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int count) {
                if (charSequence.toString().trim().length() == 0) {
                    card_expire.setText("MM/YY");
                } else {
                    String exp = insertPeriodically(charSequence.toString().trim(), "/", 2);
                    card_expire.setText(exp);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et_cvv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int count) {
                if (charSequence.toString().trim().length() == 0) {
                    card_cvv.setText("***");
                } else {
                    card_cvv.setText(charSequence.toString().trim());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int count) {
                if (charSequence.toString().trim().length() == 0) {
                    card_name.setText("Your Name");
                } else {
                    card_name.setText(charSequence.toString().trim());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        initToolbar();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChanne() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
            CharSequence name = "Notificacion - Registro Exitoso";
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void EviarNotificacion() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_connecting_airports_24);
        builder.setContentTitle("Reserva - Registrada con éxito");
        builder.setColor(Color.BLUE);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA,1000,1000);
        builder.setVibrate(new long[]{1000,1000,1000});
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat= NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(NOTIFICACION_ID,builder.build());
    }


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pago");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static String insertPeriodically(String text, String insert, int period) {
        StringBuilder builder = new StringBuilder(text.length() + insert.length() * (text.length() / period) + 1);
        int index = 0;
        String prefix = "";
        while (index < text.length()) {
            builder.append(prefix);
            prefix = insert;
            builder.append(text.substring(index, Math.min(index + period, text.length())));
            index += period;
        }
        return builder.toString();
    }

    private void showReserveComplete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(SuccessPaymentActivity.this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialogo_reserva_completada, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private boolean validar() {
        boolean retorno = true;
        String c1 = et_card_number.getText().toString();
        String c2 = et_expire.getText().toString();
        String c3 = et_cvv.getText().toString();
        String c4 = et_name.getText().toString();

        if (c1.isEmpty()) {
            et_card_number.setError("Ingresar el número de tarjeta");
            retorno = false;
        }
        if (c2.isEmpty()) {
            et_expire.setError("Ingresar expire");
            retorno = false;
        }
        if (c3.isEmpty()) {
            et_cvv.setError("Ingresar cvv");
            retorno = false;
        }
        if (c4.isEmpty()) {
            et_name.setError("Ingresar el nombre");
            retorno = false;
        }
        return retorno;
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnFinish:
                if(!validar()){
                    Toast.makeText(this, "Ingresar Datos", Toast.LENGTH_SHORT).show();
                }else{
                    EviarNotificacion();
                    createNotificationChanne();
                    //showReserveComplete();
                    Toast.makeText(this, "Se completó la reserva con éxito", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

}
