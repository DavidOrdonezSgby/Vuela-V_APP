package com.example.vuelav_app.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.vuelav_app.Login;
import com.example.vuelav_app.R;
import com.example.vuelav_app.ReserveEmergent.Destino;
import com.example.vuelav_app.ReserveEmergent.Origen;

public class ReserveFragment extends Fragment implements View.OnClickListener {

    private TextView origen;
    private TextView destino;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reserve, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        origen = (TextView) view.findViewById(R.id.txtclickOrigen);
        origen.setOnClickListener(this);

        destino = (TextView) view.findViewById(R.id.txtclickDestino);
        destino.setOnClickListener(this);
    }

    private void goToOrigen() {
        Intent intent = new Intent(getActivity(), Origen.class);
        startActivity(intent);
    }
    private void goToDestino() {
        Intent intent = new Intent(getActivity(), Destino.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.txtclickOrigen:
                goToOrigen();
                break;
            case R.id.txtclickDestino:
                goToDestino();
                break;
        }
    }
}