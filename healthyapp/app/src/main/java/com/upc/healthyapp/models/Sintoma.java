package com.upc.healthyapp.models;

public class Sintoma {
    private int id;
    private String descripcion;
    private String fecha;
    private String hora;

    public Sintoma() {
    }

    public Sintoma(int id, String descripcion, String fecha, String hora) {
        this.id = id;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
