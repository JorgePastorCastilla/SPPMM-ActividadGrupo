package com.example.actividadgrupo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AfegirAlumne extends AppCompatActivity {

    DBAlumne bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afegir_alumne);
    }

    public void onClick(View v) {
        EditText editNom = (EditText)findViewById(R.id.addAlumneNom);
        EditText editLlinatges = (EditText)findViewById(R.id.addAlumneLlinatges);
        EditText editPoblacio = (EditText)findViewById(R.id.addAlumnePoblacio);
        EditText editDireccio = (EditText)findViewById(R.id.addAlumneDireccio);
        EditText editTelefon = (EditText)findViewById(R.id.addAlumneTelefon);

        String nom = editNom.toString();
        String llinatges = editLlinatges.toString();
        String poblacio = editPoblacio.toString();
        String direccio = editDireccio.toString();
        String telefon = editTelefon.toString();

        bd = new DBAlumne(this);
        bd.obre();
        if (bd.addAlumne( nom,llinatges,poblacio,direccio,telefon ) != -1) {
            Toast.makeText(this, "Alumne creat correctament", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error a l’afegir", Toast.LENGTH_SHORT).show();
        }
        bd.tanca();
        finish();
    }
}
