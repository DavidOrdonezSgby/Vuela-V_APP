package com.example.vuelav_app.Logico.Models.Usuario;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Avion implements Serializable {

    private Long id;

    private String descripcion;
    private String placa;
    private String tipo;
    private Boolean estado;

    public List<Asiento> asiento = new ArrayList<>();

    private List<Vuelo> avion;

}
