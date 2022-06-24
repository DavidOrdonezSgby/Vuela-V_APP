package com.example.vuelav_app.Logico.Models.Usuario;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ReservaModel {
    private Integer idReserva;

    private Date fechaIda;

    private Date fechaVuelta;


    private String horaSalida;

    private String horaLlegada;


    private Integer estado;

    private String observacion;


    private Date fechaRegistro;

    private String origen;


    private String destino;


    private Boolean pago;

    private Integer idServicio;


    private Integer idTripulacion;


    private Integer idVuelo;

    private Integer idManifiesto;


    private Integer idUsuario;

    public ReservaModel() {

    }

    public ReservaModel(Integer idReserva, Date fechaIda, Date fechaVuelta, String horaSalida, String horaLlegada, Integer estado, String observacion, Date fechaRegistro, String origen, String destino, Boolean pago, Integer idServicio, Integer idTripulacion, Integer idVuelo, Integer idManifiesto, Integer idUsuario) {
        this.idReserva = idReserva;
        this.fechaIda = fechaIda;
        this.fechaVuelta = fechaVuelta;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.estado = estado;
        this.observacion = observacion;
        this.fechaRegistro = fechaRegistro;
        this.origen = origen;
        this.destino = destino;
        this.pago = pago;
        this.idServicio = idServicio;
        this.idTripulacion = idTripulacion;
        this.idVuelo = idVuelo;
        this.idManifiesto = idManifiesto;
        this.idUsuario = idUsuario;
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public Date getFechaIda() {
        return fechaIda;
    }

    public void setFechaIda(Date fechaIda) {
        this.fechaIda = fechaIda;
    }

    public Date getFechaVuelta() {
        return fechaVuelta;
    }

    public void setFechaVuelta(Date fechaVuelta) {
        this.fechaVuelta = fechaVuelta;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Boolean getPago() {
        return pago;
    }

    public void setPago(Boolean pago) {
        this.pago = pago;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public Integer getIdTripulacion() {
        return idTripulacion;
    }

    public void setIdTripulacion(Integer idTripulacion) {
        this.idTripulacion = idTripulacion;
    }

    public Integer getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(Integer idVuelo) {
        this.idVuelo = idVuelo;
    }

    public Integer getIdManifiesto() {
        return idManifiesto;
    }

    public void setIdManifiesto(Integer idManifiesto) {
        this.idManifiesto = idManifiesto;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
