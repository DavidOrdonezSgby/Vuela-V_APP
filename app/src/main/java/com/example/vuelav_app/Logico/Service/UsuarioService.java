package com.example.vuelav_app.Logico.Service;


import com.example.vuelav_app.Logico.Models.Usuario.Usuario;
import com.example.vuelav_app.Logico.Models.RegisterRequest;
import com.example.vuelav_app.Logico.Request.UsuarioRequest;
import com.example.vuelav_app.Logico.Response.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioService {

    @POST("/api/usuario/login")
    Call<UsuarioResponse> login(@Body UsuarioRequest usuario);

    @POST("/api/usuario/signup")
    Call<UsuarioResponse> signup(@Body RegisterRequest registerRequest);

    @GET("/api/usuario/{email}")
    Call<UsuarioResponse> findByemail(@Header("Authorization") String token, @Path(value="email") String email);

    @GET("api/usuario/user/id_user/{id}")
    Call<UsuarioResponse> findById(@Header("Authorization") String token, @Path(value="id") int id);
}
