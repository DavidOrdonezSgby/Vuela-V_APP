package com.example.vuelav_app.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vuelav_app.Fragments.HomeFragmentModel.Reciente;
import com.example.vuelav_app.Fragments.HomeFragmentModel.RecienteAdaptador;
import com.example.vuelav_app.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class HomeFragment extends Fragment{

    RecyclerView recyclerView;
    RecienteAdaptador recienteAdaptador;
    ArrayList<Reciente> recientes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recientes = new ArrayList<>();

        recyclerView = view.findViewById(R.id.reciente_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), recyclerView.HORIZONTAL, false));

        llenar();

        RecienteAdaptador adaptador = new RecienteAdaptador(getContext(),recientes);
        recyclerView.setAdapter(adaptador);

        return view;
    }

    private void llenar(){
        recientes.add(new Reciente("Fondo de Bikini","Narnia","$200",R.drawable.recentimage1));
        recientes.add(new Reciente("Springfield","EE.UU","$500",R.drawable.recentimage1));
        recientes.add(new Reciente("Namekusei","España","$300",R.drawable.recentimage1));
    }
}