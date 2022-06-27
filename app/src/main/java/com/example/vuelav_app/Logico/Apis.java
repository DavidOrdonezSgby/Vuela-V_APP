package com.example.vuelav_app.Logico;

import com.example.vuelav_app.Logico.Service.AsientoService;
import com.example.vuelav_app.Logico.Service.PasajeroService;
import com.example.vuelav_app.Logico.Service.ReservaService;
import com.example.vuelav_app.Logico.Service.UsuarioService;
import com.example.vuelav_app.Logico.Service.VueloService;

public class Apis {
    //public static final String URL="http://192.168.18.9:8080/";
    //public static final String URL="http://192.168.100.4:8080/";
     public static final String URL="https://aerolineavuelov.herokuapp.com";

    public static UsuarioService getUsuarioService(){
        return Cliente.getCliente(URL).create(UsuarioService.class);
    }
    public static VueloService getVueloService(){
        return Cliente.getCliente(URL).create(VueloService.class);
    }
    public static ReservaService getReservaService(){
        return Cliente.getCliente(URL).create(ReservaService.class);
    }
    public static AsientoService getAsientoService(){
        return Cliente.getCliente(URL).create(AsientoService.class);
    }
    public static PasajeroService getPasajeroService(){
        return Cliente.getCliente(URL).create(PasajeroService.class);
    }

}
