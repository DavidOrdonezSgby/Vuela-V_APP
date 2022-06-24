package com.example.vuelav_app.Fragments.NotificationFragmentModel;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.vuelav_app.Logico.Apis;
import com.example.vuelav_app.Logico.Models.Usuario.ReservaModel;
import com.example.vuelav_app.Logico.Response.ReservaResponse;
import com.example.vuelav_app.Logico.Service.ReservaService;
import com.example.vuelav_app.Logico.Token.TokenController;
import com.example.vuelav_app.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationSesionIniciada extends Fragment {

    private ReservaService reservaService = Apis.getReservaService();
    ArrayList<ReservaResponse> responseArrayList = new ArrayList<>();

    private ListView recyclerView = null;
    List<ReservaResponse> rv = new ArrayList<ReservaResponse>();
    NotificacionesAdaptador adaptador = null;
    int validador =0 ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_notification_sesion_iniciada, container, false);

        adaptador = new NotificacionesAdaptador(getContext(), R.layout.item_notificacion, rv);
        recyclerView = view.findViewById(R.id.notificaciones_recycler);
        recyclerView.setAdapter(adaptador);
        obtDatos(TokenController.getId(getContext()));

        return view;
    }



    private void obtDatos(int id){
        if(validador == 0  ){System.out.println("Entro a call notificaciones");
        Call<List<ReservaResponse>> call = reservaService.obtReservacion("Bearer "+TokenController.getToken(getContext()), id);
        call.enqueue(new Callback<List<ReservaResponse>>() {
            @Override
            public void onResponse(Call<List<ReservaResponse>> call, Response<List<ReservaResponse>> response) {
                if (response.isSuccessful()){
                    System.out.println("Bien");
                    List<ReservaResponse> data = response.body();
                    for (int i = 0; i < response.body().size(); i++){
                        System.out.println(response.body().get(i).getFechaIda().toString());
                    }
                    rv.addAll(response.body());
                    adaptador.notifyDataSetChanged();
                    validador = 1;
                }else{
                    System.out.println(response.code());
                    System.out.println(response.message());
                    System.out.println("mal");
                }


            }

            @Override
            public void onFailure(Call<List<ReservaResponse>> call, Throwable t) {
                System.out.println("Error "+t.toString());
            }
        });}
    }
}