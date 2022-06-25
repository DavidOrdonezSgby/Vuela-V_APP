package com.example.vuelav_app.Logico.Models.Usuario;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Asiento implements Serializable {

    private Long id;

    private String nombre;

    private Long estado;

    private Double precio;

    public List<Pasajero> pasajero= new ArrayList<>();

    private List<Avion> aviones = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Pasajero> getPasajero() {
        return pasajero;
    }

    public void setPasajero(List<Pasajero> pasajero) {
        this.pasajero = pasajero;
    }

    public List<Avion> getAviones() {
        return aviones;
    }

    public void setAviones(List<Avion> aviones) {
        this.aviones = aviones;
    }
}
