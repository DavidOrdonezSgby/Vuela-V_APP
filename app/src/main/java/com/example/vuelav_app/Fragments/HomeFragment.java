package com.example.vuelav_app.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.vuelav_app.Fragments.HomeFragmentModel.RecienteAdaptador;
import com.example.vuelav_app.Logico.Apis;
import com.example.vuelav_app.Logico.Response.VueloResponse;
import com.example.vuelav_app.Logico.Service.VueloService;
import com.example.vuelav_app.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment{

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    VueloService vueloService = Apis.getVueloService();
    List<VueloResponse> listvuelo = new ArrayList<VueloResponse>();
    SearchView searchView;
    TextView txtidvuelo;
    private int validar=0;
    RecienteAdaptador adaptador;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);

        searchView = view.findViewById(R.id.search_view);
        progressBar = view.findViewById(R.id.progres1);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                    adaptador.getFilter().filter(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adaptador.getFilter().filter(newText);
                return false;
            }
        });

        recyclerView = view.findViewById(R.id.reciente_recycler);
        txtidvuelo = view.findViewById(R.id.txtvueloid);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), recyclerView.HORIZONTAL, false));
        ObtenerVuelos(recyclerView);
        return view;
    }

//PARA QUE ES???
//        private void iniData(){
//        VueloResponse item = new VueloResponse();
//        item.setIdVuelo(Long.valueOf(1));
//        item.setPrecio(12.8);
//        item.setOrigen("Mexico");
//        item.setDestino("Marruecos");
//        item.setEstado(Long.valueOf(1));
//        item.setIdTipoVuelo(Long.valueOf(2));
//        item.setFechaIda(new Date());
//        item.setFechaVuelta(new Date());
//        item.setHoraSalida("09:00:00");
//        item.setHoraLlegada("12:23:11");
//        item.setImagen("");
//        item.setIdAvion(Long.valueOf(1));
//
//
//        VueloResponse item2 = new VueloResponse();
//        item2.setIdVuelo(Long.valueOf(1));
//        item2.setPrecio(12.8);
//        item2.setOrigen("Los angeles");
//        item2.setDestino("Tokio");
//        item2.setEstado(Long.valueOf(1));
//        item2.setIdTipoVuelo(Long.valueOf(2));
//        item2.setFechaIda(new Date());
//        item2.setFechaVuelta(new Date());
//        item2.setHoraSalida("09:00:00");
//        item2.setHoraLlegada("12:23:11");
//        item2.setImagen("");
//        item2.setIdAvion(Long.valueOf(1));
//        listvuelo.add(item);
//        listvuelo.add(item2);
//    }


    private void ObtenerVuelos(RecyclerView recycler){
       // listvuelo = new ArrayList<>();
        if(validar==0){
            System.out.println("Entro al metodo");
            Call<List<VueloResponse>> call = vueloService.listAll();
            call.enqueue(new Callback<List<VueloResponse>>() {
                @Override
                public void onResponse(Call<List<VueloResponse>> call, Response<List<VueloResponse>> response) {
                    if(response.isSuccessful()){
                        System.out.println("Obtuvo los datos");


                        for (int i = 0; i < response.body().size(); i++) {
                            if(response.body().get(i).getEstado()==1&&response.body().get(i).getIdTipoVuelo()==2){
                                listvuelo.add(response.body().get(i));
                            }
                        }
                        adaptador = new RecienteAdaptador(getContext(),listvuelo);
                        recyclerView.setAdapter(adaptador);
                        System.out.println(listvuelo.size());
                        validar=1;
                        progressBar.setVisibility(View.GONE);
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