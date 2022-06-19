package com.example.vuelav_app.Logico.Service;

import com.example.vuelav_app.Logico.Request.ReservaRequest;
import com.example.vuelav_app.Logico.Response.ReservaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReservaService {

    @POST("/api/reserva")
    Call<?> Create(ReservaRequest reservaRequest);

    @GET("/api/reserva/reservasByUsuario/{id}")
    Call<List<ReservaResponse>> obtReservacion(@Path("id") int id);

}
