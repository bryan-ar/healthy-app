package com.upc.healthyapp.models;

public class DoctorModel {
    private String sNombreDoctor;
    private String sEspecialidad;
    private String sFotoDoctor;

    public DoctorModel(String sNombreDoctor, String sEspecialidad, String sFotoDoctor) {
        this.sNombreDoctor = sNombreDoctor;
        this.sEspecialidad = sEspecialidad;
        this.sFotoDoctor = sFotoDoctor;
    }

    public String getsNombreDoctor() {
        return sNombreDoctor;
    }

    public void setsNombreDoctor(String sNombreDoctor) {
        this.sNombreDoctor = sNombreDoctor;
    }

    public String getsEspecialidad() {
        return sEspecialidad;
    }

    public void setsEspecialidad(String sEspecialidad) {
        this.sEspecialidad = sEspecialidad;
    }

    public String getsFotoDoctor() {
        return sFotoDoctor;
    }

    public void setsFotoDoctor(String sFotoDoctor) {
        this.sFotoDoctor = sFotoDoctor;
    }
}
