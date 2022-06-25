package com.example.vuelav_app.Logico.Response;



import com.example.vuelav_app.Logico.Models.Usuario.Asiento;
import com.example.vuelav_app.Reserva;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PasajeroResponse{
    private Long idPasajero;

    private String nombres;

    private String docIdentificacion;

    private Boolean estado;

    private Double equipaje;

    private int idReserva;

    private int idAsiento;

    public Long getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(Long idPasajero) {
        this.idPasajero = idPasajero;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getDocIdentificacion() {
        return docIdentificacion;
    }

    public void setDocIdentificacion(String docIdentificacion) {
        this.docIdentificacion = docIdentificacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Double getEquipaje() {
        return equipaje;
    }

    public void setEquipaje(Double equipaje) {
        this.equipaje = equipaje;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(int idAsiento) {
        this.idAsiento = idAsiento;
    }
}
