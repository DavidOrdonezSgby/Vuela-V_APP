package com.example.vuelav_app.Logico.Service;

import com.example.vuelav_app.Logico.Request.AsientoRequest;
import com.example.vuelav_app.Logico.Response.AsientoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface AsientoService {

    @POST("/api/asiento")
    Call<AsientoResponse> create(@Header("Authorization") String token, @Body AsientoRequest asientoRequest);


    @PUT("/api/asiento")
    Call<AsientoResponse> update(@Header("Authorization") String token, AsientoRequest asientoRequest);

}
