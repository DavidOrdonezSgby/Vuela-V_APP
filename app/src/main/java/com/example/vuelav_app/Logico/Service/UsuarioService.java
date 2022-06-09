package com.example.vuelav_app.Logico.Service;


import com.example.vuelav_app.Logico.Models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UsuarioService {

    @POST("/api/usuario/login")
    Call<Usuario> login(@Body Usuario usuario);

}
