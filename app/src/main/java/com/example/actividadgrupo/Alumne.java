package com.example.actividadgrupo;

public class Alumne {
    private int id;
    private String nom;
    private String llinatges;
    private String poblacio;
    private String direccio;
    private String telefon;
    private byte[] avatar;
    private int positius;

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public Alumne(int id, String nom, String llinatges, String poblacio, String direccio, String telefon, int positius) {
        this.id = id;
        this.nom = nom;
        this.llinatges = llinatges;
        this.poblacio = poblacio;
        this.direccio = direccio;
        this.telefon = telefon;
        this.positius = positius;
    }
    public Alumne(int id, String nom, String llinatges, String poblacio, String direccio, String telefon,byte[] avatar) {
        this.id = id;
        this.nom = nom;
        this.llinatges = llinatges;
        this.poblacio = poblacio;
        this.direccio = direccio;
        this.telefon = telefon;
        this.avatar = avatar;
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

    public int getPositius() {
        return positius;
    }
    public String getPositiusString(){
        return "("+positius+")";
    }

    public void setPositius(int positius) {
        this.positius = positius;
    }
}
