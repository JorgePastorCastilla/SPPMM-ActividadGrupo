package com.example.actividadgrupo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;


public class DBAlumneClasse {
    public static final String CLAU_ID = "_id";
    public static final String CLAU_ALUMNE = "_idAlumne";
    public static final String CLAU_CLASSE = "_idClasse";
    public static final String CLAU_POSITIUS = "_positius";

    public static final String TAG = "DBAlumneClasse";
    public static final String BD_NOM = "BDProfesor";
    public static final String BD_TAULA_ALUMNECLASSE = "alumne_classe";
    public static final int VERSIO = 1;
    public static final String BD_CREATE ="create table " + BD_TAULA_ALUMNECLASSE +
            "( " + CLAU_ID + " integer primary key autoincrement, " +
            CLAU_ALUMNE +" text not null, " +
            CLAU_CLASSE +" text not null, "+
            CLAU_POSITIUS +" integer DEFAULT 0, " +
            "foreign key (" + CLAU_ALUMNE + ") references " + DBAlumne.BD_TAULA_ALUMNE + "(" + DBAlumne.CLAU_ID + "), " +
            "foreign key (" + CLAU_CLASSE + ") references " + DBInterface.BD_TAULA_CLASSE + "(" + DBInterface.CLAU_ID +
            "));";
    private final Context context;
    private AjudaDB ajuda;
    private SQLiteDatabase bd;

    public DBAlumneClasse(Context con) {
        this.context = con;
        ajuda = new AjudaDB(context);
    }

    //Obre la Base de dades
    public DBAlumneClasse obre() throws SQLException {
        bd = ajuda.getWritableDatabase();
        return this;
    }
    //Tanca la Base de dades
    public void tanca() {
        ajuda.close();
    }

    public long addAlumneClasse(String alumne, String classe) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(CLAU_ALUMNE, alumne);
        initialValues.put(CLAU_CLASSE, classe);
        if(alumne.isEmpty() || classe.isEmpty())return -1;
        return bd.insert(BD_TAULA_ALUMNECLASSE ,null, initialValues);
    }

    public boolean delete(long IDFila) {
        return bd.delete(BD_TAULA_ALUMNECLASSE, CLAU_ID + " = " + IDFila, null) > 0;
    }

    public int getPositius(String alumne, String classe){
        Cursor result = bd.query(BD_TAULA_ALUMNECLASSE, new String[] {CLAU_POSITIUS }, (CLAU_ALUMNE+"="+alumne+" and "+CLAU_CLASSE+"="+classe),null, null, null, null);
        result.moveToFirst();
        return result.getInt(0);
    }

}
