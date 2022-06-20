package com.example.vuelav_app.Logico.Response;



import java.io.Serializable;
import java.sql.Time;
import java.util.Date;


public class ReservaResponse  {


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

    private Long idTripulacion;

    private int idVuelo;

    private Long idManifiesto;

    private Long idUsuario;

    public ReservaResponse() {
        
    }

    public ReservaResponse(Long id, Date fechaIda, Date fechaVuelta, String horaSalida, String horaLlegada, Long estado, String observacion, Date fechaRegistro, String origen, String destino, Boolean pago, Long id1, Long id2) {
    }

    public ReservaResponse(Long id, Date fechaIda, Date fechaVuelta, String horaSalida, String horaLlegada, Long estado, String observacion, Date fechaRegistro, String origen, String destino, Boolean pago, Long id1) {
    }
}
