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
    private static final int CODI_LLISTA_ALUMNES = 200;
    private ArrayList<Classe> classes = new ArrayList<>();
    ArrayAdapter<Classe> adapter;
    Button borrarButton;
    Button cancelarButton;
    Button modificarButton;
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
        modificarButton = findViewById(R.id.modifyClasseButton);
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
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Classe classe = adapter.getItem(position);
                Intent i = new Intent(getApplicationContext(), LlistaAlumnes.class);
                i.putExtra("idClasse", classe.getIdClasse());
                startActivity(i);

            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int pos, final long id) {
                borrarButton.setEnabled(true);
                modificarButton.setEnabled(true);
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
                modificarButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Classe classeAmodificar = adapter.getItem(pos);
                            Intent i = new Intent(getApplicationContext(), ModificarClasse.class);
                            i.putExtra("idClasse", classeAmodificar.getIdClasse());
                            startActivity(i);

                    }
                });
                Toast.makeText(getApplicationContext(), "Pots borrar la classe", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    /*public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODI_LLISTA_ALUMNES) {
            //Si el resultat ha sigut positiu
            if (resultCode == RESULT_OK) {
//                data.getExtras().clear();
                data.removeExtra("idClasse");
            }
        }
    }*/

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
        modificarButton.setEnabled(false);
        cancelarButton.setVisibility(View.INVISIBLE);

    }
}