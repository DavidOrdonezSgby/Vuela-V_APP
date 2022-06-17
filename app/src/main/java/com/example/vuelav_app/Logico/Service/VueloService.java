package com.example.vuelav_app.Logico.Service;

import com.example.vuelav_app.Logico.Response.VueloResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface VueloService {

    @GET("/api/vuelo/vueloAll")
    Call<List<VueloResponse>> listAll();

    @GET("/api/vuelo/vuelo/{id}")
    Call<VueloResponse> findById(@Path("id") int id);


}
