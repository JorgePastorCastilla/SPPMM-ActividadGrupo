package com.example.actividadgrupo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import static com.example.actividadgrupo.DBInterface.BD_CREATE;
import static com.example.actividadgrupo.DBInterface.BD_NOM;
import static com.example.actividadgrupo.DBInterface.BD_TAULA_CLASSE;
import static com.example.actividadgrupo.DBInterface.TAG;
import static com.example.actividadgrupo.DBInterface.VERSIO;

public class DBInterface {
    public static final String CLAU_ID = "_idClasse";
    public static final String CLAU_NOM = "nomClasse";
    public static final int NUM_ALUMNES = 10;
    public static final String CLAU_SIGLES = "siglesClasse";
    public static final String TAG = "DBInterface";
    public static final String BD_NOM = "BDProfesor";
    public static final String BD_TAULA_CLASSE = "classe";
    public static final int VERSIO = 1;
    public static final String BD_CREATE ="create table " + BD_TAULA_CLASSE + "( " + CLAU_ID + " integer primary key autoincrement, " + CLAU_NOM +" text not null, " + CLAU_SIGLES + " text not null);";
    private final Context context;
    private AjudaDB ajuda;
    private SQLiteDatabase bd;

    public DBInterface(Context con) {
        this.context = con;
        ajuda = new AjudaDB(context);
    }

    public DBInterface obre() throws SQLException {
        bd = ajuda.getWritableDatabase();
        return this;
    }

    public void tanca() {
        ajuda.close();
    }

    public long afegirClasse(String nom, String sigles) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(CLAU_NOM, nom);
        initialValues.put(CLAU_SIGLES, sigles);
        if(nom.isEmpty() || sigles.isEmpty())return -1;
        return bd.insert(BD_TAULA_CLASSE ,null, initialValues);
    }

    public boolean esborraClasse(long IDFila) {
        return bd.delete(BD_TAULA_CLASSE, CLAU_ID + " = " + IDFila, null) > 0;
    }

//    public Cursor obtenirContacte(long IDFila) throws SQLException {
//        Cursor mCursor = bd.query(true, BD_TAULA, new String[] {
//                CLAU_ID, CLAU_NOM,CLAU_EMAIL},CLAU_ID + " = " + IDFila, null, null, null, null, null);
//        if(mCursor != null) {
//            mCursor.moveToFirst();
//        }
//        return mCursor;
//    }
//
    public Cursor obtenirTotesLesClasses() {
        return bd.query(BD_TAULA_CLASSE, new String[] {

                CLAU_ID, CLAU_NOM, CLAU_SIGLES}, null,null, null, null, null);
    }
//
//    public boolean actualitzarContacte(long IDFila, String nom, String email) {
//        ContentValues args = new ContentValues();
//        args.put(CLAU_NOM, nom);
//        args.put(CLAU_EMAIL, email);
//        return bd.update(BD_TAULA, args, CLAU_ID + " = " + IDFila, null) > 0;
//    }
}
