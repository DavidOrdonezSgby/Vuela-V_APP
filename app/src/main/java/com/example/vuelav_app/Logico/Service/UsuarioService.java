package com.example.vuelav_app.Logico.Service;


import com.example.vuelav_app.Logico.Models.Usuario.Usuario;
import com.example.vuelav_app.Logico.Models.RegisterRequest;
import com.example.vuelav_app.Logico.Response.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioService {

    @POST("/api/usuario/login")
    Call<UsuarioResponse> login(@Body RegisterRequest usuario);

    @POST("/api/usuario/signup")
    Call<Usuario> signup(@Body Usuario usuario);

    @GET("/api/usuario/usuario/{cedula}")
    Call<UsuarioResponse> findByCI(@Header("Authorization") String token, @Path(value="cedula") String cedula);

}
