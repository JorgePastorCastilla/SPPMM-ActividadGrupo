package com.example.actividadgrupo;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static com.example.actividadgrupo.DBInterface.BD_CREATE;
import static com.example.actividadgrupo.DBInterface.BD_NOM;
import static com.example.actividadgrupo.DBInterface.BD_TAULA_CLASSE;
import static com.example.actividadgrupo.DBInterface.TAG;
import static com.example.actividadgrupo.DBInterface.VERSIO;

public class AjudaDB extends SQLiteOpenHelper {
    AjudaDB(Context con) {
        super(con, BD_NOM, null, VERSIO);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(BD_CREATE);
            db.execSQL(DBAlumne.BD_CREATE);
            db.execSQL(DBAlumneClasse.BD_CREATE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int VersioAntiga, int VersioNova) {
        Log.w(TAG, "Actualitzant Base de dades de la versió" + VersioAntiga+ " a " + VersioNova + ". Destruirà totes les dades");
        db.execSQL("DROP TABLE IF EXISTS " + BD_TAULA_CLASSE);
        //db.execSQL("DROP TABLE IF EXISTS " + DBAlumne.BD_TAULA_ALUMNE);

        onCreate(db);
    }
}