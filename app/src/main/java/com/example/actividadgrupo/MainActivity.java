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
}
