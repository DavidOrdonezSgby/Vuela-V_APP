package com.example.vuelav_app.Logico.Models.Usuario;


import com.example.vuelav_app.Reserva;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;


public class Vuelo implements Serializable {

    private Long id;

    private Double precio;

    private String origen;

    private String destino;

    private Long estado;

    private Date fechaIda;

    private Date fechaVuelta;


    private String horaSalida;

    private String horaLlegada;

    private String imagen;

    //private Promocion promocion;

    //private TipoVuelo tipoVuelo;

    private List<Reserva> reserva;

    private Usuario usuario;

    private Avion avion;
}
