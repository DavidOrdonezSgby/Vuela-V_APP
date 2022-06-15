package com.example.vuelav_app.Logico;

import com.example.vuelav_app.Logico.Service.UsuarioService;

public class Apis {

   // public static final String URL="http://192.168.18.9:8080/";
    public static final String URL="http://192.168.0.117:8080/";

    public static UsuarioService getUsuarioService(){
        return Cliente.getCliente(URL).create(UsuarioService.class);
    }
}
