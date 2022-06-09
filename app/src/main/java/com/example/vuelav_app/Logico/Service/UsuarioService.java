package com.example.vuelav_app.Logico.Service;


import com.example.vuelav_app.Logico.Models.Usuario;
import com.example.vuelav_app.Logico.Models.UsuarioRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioService {

    @POST("/api/usuario/login")
    Call<UsuarioRequest> login(@Body UsuarioRequest usuario);

    @POST("/api/usuario/signup")
    Call<Usuario> signup(@Body Usuario usuario);

    @GET("/api/usuario/usuario/{cedula}")
    Call<Usuario> findByCI(@Path("cedula") String cedula);

}
