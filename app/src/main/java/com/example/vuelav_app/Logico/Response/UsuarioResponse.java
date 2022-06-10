package com.example.vuelav_app.Logico.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.sql.Date;

public class UsuarioResponse {

    @SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("pais")
    @Expose
    private String pais;

    @SerializedName("docIdentificacion")
    @Expose
    private String docIdentificacion;

    @SerializedName("nombres")
    @Expose
    private String nombres;

    @SerializedName("apellidos")
    @Expose
    private String apellidos;

    @SerializedName("fechaNacimiento")
    @Expose
    private Date fechaNacimiento;

    @SerializedName("genero")
    @Expose
    private String genero;

    @SerializedName("telefono")
    @Expose
    private String telefono;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("clave")
    @Expose
    private String clave;

    @SerializedName("razonSocial")
    @Expose
    private String razonSocial;

    @SerializedName("terminosCondiciones")
    @Expose
    private Boolean terminosCondiciones;

    @SerializedName("autorizacion")
    @Expose
    private Boolean autorizacion;

    @SerializedName("rol")
    @Expose
    private String rol;

    @SerializedName("token")
    @Expose
    private String token;

    public UsuarioResponse() {
    }

    public UsuarioResponse(Long id, String pais, String docIdentificacion, String nombres, String apellidos, Date fechaNacimiento, String genero, String telefono, String email, String clave, String razonSocial, Boolean terminosCondiciones, Boolean autorizacion, String rol, String token) {
        this.id = id;
        this.pais = pais;
        this.docIdentificacion = docIdentificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.telefono = telefono;
        this.email = email;
        this.clave = clave;
        this.razonSocial = razonSocial;
        this.terminosCondiciones = terminosCondiciones;
        this.autorizacion = autorizacion;
        this.rol = rol;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDocIdentificacion() {
        return docIdentificacion;
    }

    public void setDocIdentificacion(String docIdentificacion) {
        this.docIdentificacion = docIdentificacion;
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

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
