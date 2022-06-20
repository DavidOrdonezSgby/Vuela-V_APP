package com.example.vuelav_app.Logico;

import com.example.vuelav_app.Logico.Service.ReservaService;
import com.example.vuelav_app.Logico.Service.UsuarioService;
import com.example.vuelav_app.Logico.Service.VueloService;

public class Apis {

    //public static final String URL="http://192.168.18.9:8080/";
    public static final String URL="http://192.168.0.117:8080/";

    public static UsuarioService getUsuarioService(){
        return Cliente.getCliente(URL).create(UsuarioService.class);
    }
    public static VueloService getVueloService(){
        return Cliente.getCliente(URL).create(VueloService.class);
    }
    public static ReservaService getReservaService(){
        return Cliente.getCliente(URL).create(ReservaService.class);
    }
}
