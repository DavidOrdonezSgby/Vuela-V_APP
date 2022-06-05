package com.example.vuelav_app.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.vuelav_app.Login;
import com.example.vuelav_app.R;

public class NotificationFragment extends Fragment implements View.OnClickListener {

    private Button iniciarsesionNotificaciones;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iniciarsesionNotificaciones = (Button) view.findViewById(R.id.buttonInicioSesionNot);
        iniciarsesionNotificaciones.setOnClickListener(this);
    }

    private void goToLogin() {
        Intent intent = new Intent(getActivity(), Login.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.buttonInicioSesionNot:
                goToLogin();
                break;
        }
    }
}