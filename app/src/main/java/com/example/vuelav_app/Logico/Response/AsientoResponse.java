package com.example.vuelav_app.Logico.Response;


import com.example.vuelav_app.Logico.Models.Usuario.Avion;

import java.io.Serializable;
import java.util.List;

public class AsientoResponse implements Serializable {

    private Long idAsiento;

    private String nombre;

    private Long estado;

    private int idavion;

    private Double precio;

    public AsientoResponse() {

    }

    public int getIdavion() {
        return idavion;
    }

    public void setIdavion(int idavion) {
        this.idavion = idavion;
    }

    public Long getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(Long idAsiento) {
        this.idAsiento = idAsiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getEstado() {
        return estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }


    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
