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
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Classe> classes = new ArrayList<>();
    ArrayAdapter<Classe> adapter;
    Button borrarButton;
    Button cancelarButton;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        actualitzaLlista();
    }

    private void actualitzaLlista() {
        cancelarButton = findViewById(R.id.cancelarButton);
        borrarButton = findViewById(R.id.llevarClasseButton);
        DBInterface bd;
        bd = new DBInterface(getApplicationContext());
        list = findViewById(R.id.listView);
        bd.obre();
        Cursor c = bd.obtenirTotesLesClasses();

        c.moveToFirst();
        classes = new ArrayList<>();
        while (!c.isAfterLast()) {
            classes.add(new Classe(c.getInt(0), c.getString(1), c.getString(2), 20));
            c.moveToNext();
        }
        bd.tanca();

        adapter = new ArrayClasse(this, R.layout.classe, classes);
        list.setAdapter(adapter);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int pos, final long id) {
                borrarButton.setEnabled(true);
                cancelarButton.setVisibility(View.VISIBLE);
                cancelarButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deshabilitaButtons();
                    }
                });
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
        actualitzaLlista();
    }

    public void afegeixClasse(View view) {
        deshabilitaButtons();
        Intent i = new Intent(this, AfegirClasse.class);
        startActivity(i);
    }


    public void onClickEsborra(View v, Classe classeABorrar) {
        DBInterface bd;
        bd = new DBInterface(this.getApplicationContext());
        bd.obre();
        long id = classeABorrar.getIdClasse();
        boolean result = bd.esborraClasse(id);
        if (result) {
            Toast.makeText(this, "Classe borrada", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "No s’ha pogut borrar la classe",
                    Toast.LENGTH_SHORT).show();
        }
        bd.tanca();
        actualitzaLlista();
        deshabilitaButtons();
    }

    private void deshabilitaButtons() {
        borrarButton.setEnabled(false);
        cancelarButton.setVisibility(View.INVISIBLE);
    }
}