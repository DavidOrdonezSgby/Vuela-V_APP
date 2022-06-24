package com.example.vuelav_app.Logico.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ReservaRequest  {

    @SerializedName("idReserva")
    @Expose
    private Integer idReserva;

    @SerializedName("fechaIda")
    @Expose
    private String fechaIda;

    @SerializedName("fechaVuelta")
    @Expose
    private String fechaVuelta;

    @SerializedName("horaSalida")
    @Expose
    private String horaSalida;

    @SerializedName("horaLlegada")
    @Expose
    private String horaLlegada;

    @SerializedName("estado")
    @Expose
    private Integer estado;

    @SerializedName("observacion")
    @Expose
    private String observacion;

    @SerializedName("fechaRegistro")
    @Expose
    private String fechaRegistro;

    @SerializedName("origen")
    @Expose
    private String origen;

    @SerializedName("destino")
    @Expose
    private String destino;

    @SerializedName("pago")
    @Expose
    private Boolean pago;

    @SerializedName("idServicio")
    @Expose
    private Integer idServicio;

    @SerializedName("idTripulacion")
    @Expose
    private Integer idTripulacion;

    @SerializedName("idVuelo")
    @Expose
    private Integer idVuelo;

    @SerializedName("idManifiesto")
    @Expose
    private Integer idManifiesto;

    @SerializedName("idUsuario")
    @Expose
    private Integer idUsuario;

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public String getFechaIda() {
        return fechaIda;
    }

    public void setFechaIda(String fechaIda) {
        this.fechaIda = fechaIda;
    }

    public String getFechaVuelta() {
        return fechaVuelta;
    }

    public void setFechaVuelta(String fechaVuelta) {
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

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
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
