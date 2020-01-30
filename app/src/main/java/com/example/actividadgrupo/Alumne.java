package com.example.actividadgrupo;

public class Alumne {
    private int id;
    private String nom;
    private String llinatges;
    private String poblacio;
    private String direccio;
    private String telefon;

    public Alumne(int id, String nom, String llinatges, String poblacio, String direccio, String telefon) {
        this.id = id;
        this.nom = nom;
        this.llinatges = llinatges;
        this.poblacio = poblacio;
        this.direccio = direccio;
        this.telefon = telefon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLlinatges() {
        return llinatges;
    }

    public void setLlinatges(String llinatges) {
        this.llinatges = llinatges;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }

    public String getDireccio() {
        return direccio;
    }

    public void setDireccio(String direccio) {
        this.direccio = direccio;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
}
