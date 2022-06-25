package com.example.vuelav_app.Logico.Service;

import com.example.vuelav_app.Logico.Request.PasajeroRequest;
import com.example.vuelav_app.Logico.Response.PasajeroResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PasajeroService {

    @POST("api/pasajero")
    Call<PasajeroResponse> create(@Header("Authorization") String token, @Body PasajeroRequest pasajeroRequest);

    @GET("api/pasajero/{id}")
    Call<List<PasajeroResponse>> listId(@Header("Authorization") String token, @Path(value="id") int id);

}
