package com.example.vuelav_app.Logico.Models.Usuario;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reserva implements Serializable {

    private Long id;

    private Date fechaIda;

    private Date fechaVuelta;

    private String horaSalida;
    private String horaLlegada;

    private Long estado;

    private String observacion;

    private Date fechaRegistro;

    private String origen;

    private String destino;

    private Boolean pago;

    public List<Pasajero> pasajero = new ArrayList<>();

    private Manifiesto manifiesto;

    private Vuelo vuelo;

    private List<Usuario> usuarios = new ArrayList<>();



}
