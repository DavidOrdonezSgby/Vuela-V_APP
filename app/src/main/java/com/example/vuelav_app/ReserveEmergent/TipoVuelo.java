package com.example.vuelav_app.ReserveEmergent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vuelav_app.Fragments.ReserveFragment;
import com.example.vuelav_app.R;

public class TipoVuelo extends AppCompatActivity implements View.OnClickListener {

    private RadioButton comercial, premium;
    private Button confirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_bottom_sheet);

        comercial = (RadioButton) findViewById(R.id.rbComercial);
        premium = (RadioButton) findViewById(R.id.rbPremium);

        confirmar = (Button) findViewById(R.id.btnConfirmarTipoVuelo);
        confirmar.setOnClickListener(this);

    }

    private void enviar(){
        String str = null;
        if (comercial.isChecked()==true){
            str = "Comercial";
        }
        if (premium.isChecked()){
            str= "Premium";
        }
        Intent intent = new Intent(getApplicationContext(), ReserveFragment.class);
        intent.putExtra("Opcion", str);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnConfirmarTipoVuelo:
                enviar();
                break;

        }
    }
}
