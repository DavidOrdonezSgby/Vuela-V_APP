package com.example.vuelav_app.Logico.Service;

import com.example.vuelav_app.Logico.Request.ReservaRequest;
import com.example.vuelav_app.Logico.Response.ReservaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReservaService {

    @POST("/api/reserva")
    Call<ReservaResponse> creaReserva(@Header("Authorization") String token, @Body ReservaRequest reservaRequest);

    @GET("/api/reserva/reservasByUsuario/{id}")
    Call<List<ReservaResponse>> obtReservacion(@Header("Authorization") String token, @Path("id") int id);

    @GET("/api/reserva/{id}")
    Call<ReservaResponse> obtReservacionId(@Header("Authorization") String token, @Path("id") int id);

}
