package com.example.vuelav_app.Fragments.HomeFragmentModel;

public class Reciente {

    private String nombreLugar;
    private String ciudadLugar;
    private String precio;
    private Integer imgUrl;

    public Reciente(String nombreLugar, String ciudadLugar, String precio, Integer imgUrl) {
        this.nombreLugar = nombreLugar;
        this.ciudadLugar = ciudadLugar;
        this.precio = precio;
        this.imgUrl = imgUrl;
    }

    public String getNombreLugar() {
        return nombreLugar;
    }

    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

    public String getCiudadLugar() {
        return ciudadLugar;
    }

    public void setCiudadLugar(String ciudadLugar) {
        this.ciudadLugar = ciudadLugar;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public Integer getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(Integer imgUrl) {
        this.imgUrl = imgUrl;
    }
}
