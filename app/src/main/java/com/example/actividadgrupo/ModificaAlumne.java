package com.example.actividadgrupo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ModificaAlumne extends AppCompatActivity {
    DBAlumne bd;
    EditText editNom;
    EditText editSigles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica_alumne);
    }

    public void onClick(View v) {
        EditText editNom = (EditText)findViewById(R.id.addAlumneNom);
        EditText editLlinatges = (EditText)findViewById(R.id.addAlumneLlinatges);
        EditText editPoblacio = (EditText)findViewById(R.id.addAlumnePoblacio);
        EditText editDireccio = (EditText)findViewById(R.id.addAlumneDireccio);
        EditText editTelefon = (EditText)findViewById(R.id.addAlumneTelefon);

        String nom = editNom.getText().toString();
        String llinatges = editLlinatges.getText().toString();
        String poblacio = editPoblacio.getText().toString();
        String direccio = editDireccio.getText().toString();
        String telefon = editTelefon.getText().toString();

        bd = new DBAlumne(this);
        bd.obre();
        Bundle data = getIntent().getExtras();
        int idAlumne = data.getInt("idAlumne");
        bd.updateAlumne(idAlumne+"",nom,llinatges,poblacio,direccio,telefon);
        bd.tanca();
        finish();
    }
}
