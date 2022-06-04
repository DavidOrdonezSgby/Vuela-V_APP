package com.example.vuelav_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AccountFragment extends Fragment implements View.OnClickListener {

    private Button iniciarsesion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iniciarsesion = (Button) view.findViewById(R.id.buttonInicioSesion);
        iniciarsesion.setOnClickListener(this);
    }

    private void goToLogin() {
        Intent intent = new Intent(getActivity(), Login.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.buttonInicioSesion:
                goToLogin();
                break;

        }
    }
}