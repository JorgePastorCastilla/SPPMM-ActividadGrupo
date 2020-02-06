package com.example.actividadgrupo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBAlumne {
    public static final String CLAU_ID = "_idAlumne";
    public static final String CLAU_NOM = "nom";
    public static final String CLAU_LLINATGES = "llinatges";
    public static final String CLAU_POBLACIO = "poblacio";
    public static final String CLAU_DIRECCIO = "direccio";
    public static final String CLAU_TELEFON = "telefon";

    public static final String TAG = "DBAlumne";
    public static final String BD_NOM = "BDProfesor";
    public static final String BD_TAULA_ALUMNE = "alumne";
    public static final int VERSIO = 1;
    public static final String BD_CREATE ="create table " + BD_TAULA_ALUMNE +
            "( " + CLAU_ID + " integer primary key autoincrement, " +
            CLAU_NOM +" text not null, " +
            CLAU_LLINATGES +" text not null, "+
            CLAU_POBLACIO +" text not null, " +
            CLAU_DIRECCIO +" text not null, " +
            CLAU_TELEFON +" text not null "  +
            ");";
    private final Context context;
    private AjudaDB ajuda;
    private SQLiteDatabase bd;

    public DBAlumne(Context con) {
        this.context = con;
        ajuda = new AjudaDB(context);
    }

    //Obre la Base de dades
    public DBAlumne obre() throws SQLException {
        bd = ajuda.getWritableDatabase();
        return this;
    }
    //Tanca la Base de dades
    public void tanca() {
        ajuda.close();
    }

    public long addAlumne(String nom, String llinatges, String poblacio, String direccio, String telefon) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(CLAU_NOM, nom);
        initialValues.put(CLAU_LLINATGES, llinatges);
        initialValues.put(CLAU_POBLACIO, poblacio);
        initialValues.put(CLAU_DIRECCIO, direccio);
        initialValues.put(CLAU_TELEFON, telefon);
        if(nom.isEmpty())return -1;
        return bd.insert(BD_TAULA_ALUMNE ,null, initialValues);
    }
    public long addAlumne(String nom, String llinatges, String poblacio, String direccio, String telefon, String classe) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(CLAU_NOM, nom);
        initialValues.put(CLAU_LLINATGES, llinatges);
        initialValues.put(CLAU_POBLACIO, poblacio);
        initialValues.put(CLAU_DIRECCIO, direccio);
        initialValues.put(CLAU_TELEFON, telefon);
        if(nom.isEmpty())return -1;
        bd.insert(BD_TAULA_ALUMNE ,null, initialValues);
        ContentValues valuesForRelation = new ContentValues();
        int insertado = getId(nom,llinatges,poblacio,direccio,telefon);
        valuesForRelation.put(DBAlumneClasse.CLAU_ALUMNE,insertado);
        valuesForRelation.put(DBAlumneClasse.CLAU_CLASSE,classe);
        return bd.insert(DBAlumneClasse.BD_TAULA_ALUMNECLASSE ,null, valuesForRelation);
    }

    public boolean delete(long IDFila) {
        return bd.delete(BD_TAULA_ALUMNE, CLAU_ID + " = " + IDFila, null) > 0;
    }

    public int getId(String nom, String llinatges, String poblacio, String direccio, String telefon){
//        Cursor c = bd.query(BD_TAULA_ALUMNE,new String[]{CLAU_ID}, CLAU_NOM+"="+nom+" AND "+CLAU_LLINATGES+"="+llinatges+" AND "+CLAU_POBLACIO+"="+poblacio+" AND "+CLAU_DIRECCIO+"="+direccio+" AND "+CLAU_TELEFON+"="+telefon,null,null,null,null);

        Cursor c = bd.rawQuery("SELECT _idAlumne FROM alumne WHERE nom=? AND llinatges=? AND poblacio=? AND direccio=? AND telefon=?", new String[]{nom,llinatges,poblacio,direccio,telefon} );
        c.moveToFirst();
        return c.getInt(0);
    }
    public Cursor allAlumnes() {
        return bd.query(BD_TAULA_ALUMNE, new String[] {
                CLAU_ID, CLAU_NOM, CLAU_LLINATGES, CLAU_POBLACIO, CLAU_DIRECCIO, CLAU_TELEFON}, null,null, null, null, null);
    }
    public Cursor allAlumnes(String classe){
        String whereAllAlumnes = DBAlumne.BD_TAULA_ALUMNE+"."+CLAU_ID+"="+DBAlumneClasse.BD_TAULA_ALUMNECLASSE+"."+DBAlumneClasse.CLAU_ALUMNE+" AND " +DBAlumneClasse.BD_TAULA_ALUMNECLASSE+"."+DBAlumneClasse.CLAU_CLASSE+"="+classe;
        return bd.query(BD_TAULA_ALUMNE+" , "+DBAlumneClasse.BD_TAULA_ALUMNECLASSE, new String[] {
                DBAlumne.BD_TAULA_ALUMNE+"."+CLAU_ID, CLAU_NOM, CLAU_LLINATGES, CLAU_POBLACIO, CLAU_DIRECCIO, CLAU_TELEFON}, whereAllAlumnes,null, null, null, null);
    }

}
