package com.example.vuelav_app.Logico.Models.Usuario;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Pasajero implements Serializable {

    private Long id;

    private String nombres;

    private String docIdentificacion;

    private Boolean estado;

    private Double equipaje;

    private List<Asiento> asientos = new ArrayList<>();

    private List<Reserva> reservas = new ArrayList<>();
}
