package com.example.actividadgrupo;

public class Classe {
    private int idClasse;
    private String siglesClasse;
    private String nomClasse;
    private int numAlumnes;

    public Classe(int idClasse, String nomClasse, String siglesClasse, int numAlumnes) {
        this.idClasse = idClasse;
        this.siglesClasse = siglesClasse;
        this.nomClasse = nomClasse;
        this.numAlumnes = numAlumnes;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }

    public String getSiglesClasse() {
        return siglesClasse;
    }

    public void setSiglesClasse(String siglesClasse) {
        this.siglesClasse = siglesClasse;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public int getNumAlumnes() {
        return numAlumnes;
    }

    public void setNumAlumnes(int numAlumnes) {
        this.numAlumnes = numAlumnes;
    }
}
