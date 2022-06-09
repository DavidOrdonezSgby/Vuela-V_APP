package com.example.vuelav_app.Logico.Models;

import java.util.Date;

public class Usuario {

    private String pais;

    private String doc_identificacion;

    private String nombres;

    private String apellidos;

    private Date fechaNacimiento;

    private String genero;

    private String telefono;

    private String email;

    private String clave;

    private String razonSocial;

    private Boolean terminosCondiciones;

    private Boolean autorizacion;

    public Usuario() {
    }

    public Usuario(String email, String clave) {
        this.email = email;
        this.clave = clave;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDoc_identificacion() {
        return doc_identificacion;
    }

    public void setDoc_identificacion(String doc_identificacion) {
        this.doc_identificacion = doc_identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Boolean getTerminosCondiciones() {
        return terminosCondiciones;
    }

    public void setTerminosCondiciones(Boolean terminosCondiciones) {
        this.terminosCondiciones = terminosCondiciones;
    }

    public Boolean getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(Boolean autorizacion) {
        this.autorizacion = autorizacion;
    }
}
