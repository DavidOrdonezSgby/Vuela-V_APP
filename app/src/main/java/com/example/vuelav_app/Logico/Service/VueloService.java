package com.example.vuelav_app.Logico.Service;

import com.example.vuelav_app.Logico.Response.VueloResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VueloService {

    @GET("/api/vuelo/vueloAll")
    Call<List<VueloResponse>> listAll();

}
