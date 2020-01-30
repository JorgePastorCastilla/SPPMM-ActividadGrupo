package com.example.actividadgrupo;

public class Classe {
    private int idClasse;
    private String nomClasse;

    public Classe(int idClasse, String nomClasse) {
        this.idClasse = idClasse;
        this.nomClasse = nomClasse;
    }

    public String getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(String nomClasse) {
        this.nomClasse = nomClasse;
    }

    public int getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(int idClasse) {
        this.idClasse = idClasse;
    }
}
