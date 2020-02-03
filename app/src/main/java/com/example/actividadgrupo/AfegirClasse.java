package com.example.actividadgrupo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AfegirClasse extends AppCompatActivity {
    DBInterface bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afegir_classe);
    }

    public void onClick(View v) {
        EditText editNom = findViewById(R.id.afegirClasseEditText);
        EditText editSigles = findViewById(R.id.siglesClasseEditText);
        bd = new DBInterface(this);
        bd.obre();
        if (bd.afegirClasse(editNom.getText().toString(), editSigles.getText().toString()) != -1) {
            Toast.makeText(this, "Classe creada correctament", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error a l’afegir", Toast.LENGTH_SHORT).show();
        }
        bd.tanca();
        finish();
    }
}
