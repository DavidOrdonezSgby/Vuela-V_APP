package com.example.vuelav_app.Logico.Models.Usuario;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Manifiesto implements Serializable {

    private Long id;

    private String archivo;

    private Date fechaCreacion;

    private Usuario usuario;

    private List<Reserva> reserva;
}
