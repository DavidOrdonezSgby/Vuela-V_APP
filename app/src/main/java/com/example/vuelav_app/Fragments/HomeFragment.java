package com.example.vuelav_app.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vuelav_app.Fragments.HomeFragmentModel.Reciente;
import com.example.vuelav_app.Fragments.HomeFragmentModel.RecienteAdaptador;
import com.example.vuelav_app.InformacionVuelo;
import com.example.vuelav_app.Logico.Apis;
import com.example.vuelav_app.Logico.Response.VueloResponse;
import com.example.vuelav_app.Logico.Service.VueloService;
import com.example.vuelav_app.Login;
import com.example.vuelav_app.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment{

    RecyclerView recyclerView;

    VueloService vueloService = Apis.getVueloService();
    List<VueloResponse> listvuelo = new ArrayList<VueloResponse>();
    TextView txtidvuelo;
    private int validar=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ObtenerVuelos();
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.reciente_recycler);
        txtidvuelo = view.findViewById(R.id.txtvueloid);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), recyclerView.HORIZONTAL, false));
        RecienteAdaptador adaptador = new RecienteAdaptador(getContext(),listvuelo);
        recyclerView.setAdapter(adaptador);

        return view;


    }

    private void ObtenerVuelos(){
        if(validar==0){
            System.out.println("Entro al metodo");
            Call<List<VueloResponse>> call = vueloService.listAll();
            call.enqueue(new Callback<List<VueloResponse>>() {
                @Override
                public void onResponse(Call<List<VueloResponse>> call, Response<List<VueloResponse>> response) {
                    if(response.isSuccessful()){
                        System.out.println("Obtuvo los datos");
                        listvuelo.addAll(response.body());

                        System.out.println(listvuelo.size());
                        validar=1;
                    }else{
                        System.out.println("No obtuvo los datos");
                    }
                }

                @Override
                public void onFailure(Call<List<VueloResponse>> call, Throwable t) {
                    System.out.println(t.toString());
                }
            });
        }

    }

}