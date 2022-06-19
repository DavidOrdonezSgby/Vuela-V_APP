package com.example.vuelav_app.Logico.Request;



import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class ReservaRequest  {

    private Long idReserva;

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

    private Long idServicio;

    //private Long idTripulacion;

    private Long idVuelo;

    //private Long idManifiesto;

    private Long idUsuario;
}
