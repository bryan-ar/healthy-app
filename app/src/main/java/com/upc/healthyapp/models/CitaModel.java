package com.upc.healthyapp.models;

public class CitaModel {
    private String sNombreDoctor;
    private String sNombrePaciente;
    private String sLinkReunion;
    private String sMes;
    private String sDia;
    private String sHora;

    public CitaModel(String sNombreDoctor, String sNombrePaciente, String sLinkReunion,
                     String sMes, String sDia, String sHora, String mes, String anio) {
        this.sNombreDoctor = sNombreDoctor;
        this.sNombrePaciente = sNombrePaciente;
        this.sLinkReunion = sLinkReunion;
        this.sMes = sMes;
        this.sDia = sDia;
        this.sHora = sHora;
    }

    public String getsNombreDoctor() {
        return sNombreDoctor;
    }

    public void setsNombreDoctor(String sNombreDoctor) {
        this.sNombreDoctor = sNombreDoctor;
    }

    public String getsNombrePaciente() {
        return sNombrePaciente;
    }

    public void setsNombrePaciente(String sNombrePaciente) {
        this.sNombrePaciente = sNombrePaciente;
    }

    public String getsLinkReunion() {
        return sLinkReunion;
    }

    public void setsLinkReunion(String sLinkReunion) {
        this.sLinkReunion = sLinkReunion;
    }

    public String getsMes() {
        return sMes;
    }

    public void setsMes(String sMes) {
        this.sMes = sMes;
    }

    public String getsDia() {
        return sDia;
    }

    public void setsDia(String sDia) {
        this.sDia = sDia;
    }

    public String getsHora() {
        return sHora;
    }

    public void setsHora(String sHora) {
        this.sHora = sHora;
    }
}
