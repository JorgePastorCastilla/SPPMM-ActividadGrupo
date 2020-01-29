package com.example.actividadgrupo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Classe> classes = new ArrayList<>();
    ArrayAdapter<Classe> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        classes.add("Clase 1");
//        classes.add("Clase 2");
//        classes.add("Clase 1");
//        classes.add("Clase 2");
//        classes.add("Clase 1");
//        classes.add("Clase 2");
//        classes.add("Clase 1");
//        classes.add("Clase 2");
//        classes.add("Clase 1");
//        classes.add("Clase 2");
//        classes.add("Clase 1");
//        classes.add("Clase 2");
//        classes.add("Clase 1");
//        classes.add("Clase 2");
//        classes.add("Clase 1");
//        classes.add("Clase 2");
//        classes.add("Clase 1");
//        classes.add("Clase 2");

        displayView();
    }

    private void displayView() {
        DBInterface bd;
        bd = new DBInterface(getApplicationContext());
        ListView list = findViewById(R.id.listView);
        bd.obre();
        Cursor c = bd.obtenirTotesLesClasses();

        c.moveToFirst();
        classes = new ArrayList<>();
        while (!c.isAfterLast()) {
            classes.add(new Classe(c.getString(1)));
            c.moveToNext();
        }
        bd.tanca();

        adapter = new ArrayClasse(this, R.layout.classe, classes);
        list.setAdapter(adapter);
//        ArrayAdapter<String> a = new ArrayAdapter<>(this, R.layout.classe, classes);
//        ListView list = findViewById(R.id.listView);
//        list.setAdapter(a);
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayView();
    }

    public void afegeixClasse(View view) {
        Intent i = new Intent(this, AfegirClasse.class);
        startActivity(i);
    }


    public void llistaContactes() {
//        DBInterface bd;
//        bd = new DBInterface(this);
//        bd.obre();
//        Cursor c = bd.obtenirTotesLesClasses();
//        c.moveToFirst();
//        ArrayList<HashMap<String, String>> llista = new ArrayList<HashMap<String, String>>();
//        //Recorr tota la llista de contacte i els va mostrant un davall l'altre emprant un ListAdapter amb el Layout de "activity_llista"
//        //Que té tres textViews, un per la id, l'altre per el nom i un altre per l'email.
//        while (!c.isAfterLast()) {
//            HashMap<String, String> map = new HashMap<String, String>();
//            map.put("id",c.getString(0));
//            map.put("nom",c.getString(1));
//            llista.add(map);c.moveToNext();
//        }bd.tanca();
//        ListAdapter adapter = new SimpleAdapter(this, llista,R.layout.activity_llista,new String[] { "id", "nom"}, new int[] {R.id.id,
//                R.id.nom, R.id.email});
//        setListAdapter(adapter);



//        if(c.getCount() == 0) {
//            Toast.makeText(MainActivity.this, "No hi ha cap classe",Toast.LENGTH_LONG).show();
//        } else {
//            while (c.moveToNext()) {
//                classes.add(c.getString(1));
//                ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, classes);
//                list.setAdapter(adapter);
//            }
//        }
    }
}
