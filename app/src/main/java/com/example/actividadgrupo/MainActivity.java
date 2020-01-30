package com.example.actividadgrupo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Classe> classes = new ArrayList<>();
    ArrayAdapter<Classe> adapter;
    Button borrarButton;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayView();
    }

    private void displayView() {
        borrarButton = findViewById(R.id.llevarClasseButton);
        DBInterface bd;
        bd = new DBInterface(getApplicationContext());
        list = findViewById(R.id.listView);
        bd.obre();
        Cursor c = bd.obtenirTotesLesClasses();

        c.moveToFirst();
        classes = new ArrayList<>();
        while (!c.isAfterLast()) {
            classes.add(new Classe(c.getInt(0), c.getString(1)));
            c.moveToNext();
        }
        bd.tanca();

        adapter = new ArrayClasse(this, R.layout.classe, classes);
        list.setAdapter(adapter);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int pos, final long id) {
                borrarButton.setEnabled(true);
                borrarButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Classe classeABorrar = adapter.getItem(pos);
                        onClickEsborra(v, classeABorrar);
                    }
                });
                Toast.makeText(getApplicationContext(), "Pots borrar la classe", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
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

//    public void esborraClasse(View v) {
//        Intent i = new Intent(this, EsborrarClasse.class);
//        startActivity(i);
//    }

    public void onClickEsborra(View v, Classe classeABorrar) {
        DBInterface bd;
        bd = new DBInterface(this.getApplicationContext());
        bd.obre();
        long id = classeABorrar.getIdClasse();
        boolean result = bd.esborraClasse(id);
        if (result) {
            Toast.makeText(this, "Element esborrat", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "No s’ha pogut esborrar l’element",
                    Toast.LENGTH_SHORT).show();
        }
        bd.tanca();
        displayView();
        borrarButton.setEnabled(false);
    }
}
