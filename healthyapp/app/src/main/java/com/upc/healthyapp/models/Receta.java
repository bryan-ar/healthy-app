package com.upc.healthyapp.models;

public class Receta {
    private int id_receta;
    private int id_cita;
    private String receta1;
    private String receta2;
    private String fecha;
    private String hora;

    public Receta(int id_receta, int id_cita, String receta1, String receta2, String fecha, String hora) {
        this.id_receta = id_receta;
        this.id_cita = id_cita;
        this.receta1 = receta1;
        this.receta2 = receta2;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getIdReceta() {
        return id_receta;
    }

    public void setIdReceta(int id_receta) {
        this.id_receta = id_receta;
    }

    public int getIdCita() {
        return id_cita;
    }

    public void setIdCita(int id_cita) {
        this.id_cita = id_cita;
    }

    public String getReceta1() {
        return receta1;
    }

    public void setReceta1(String receta1) {
        this.receta1 = receta1;
    }

    public String getReceta2() {
        return receta2;
    }

    public void setReceta2(String receta2) {
        this.receta2 = receta2;
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
