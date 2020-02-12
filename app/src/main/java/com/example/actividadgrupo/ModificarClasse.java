package com.example.actividadgrupo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ModificarClasse extends AppCompatActivity {
    DBInterface bd;
    EditText editNom;
    EditText editSigles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_classe);
        editNom = findViewById(R.id.afegirClasseEditText);
        editSigles = findViewById(R.id.siglesClasseEditText);
    }

    public void onClick(View v) {

        bd = new DBInterface(this);
        bd.obre();
        Bundle data = getIntent().getExtras();
        int idClasse = data.getInt("idClasse");
        bd.updateClasse(idClasse+"",editNom.getText().toString(),editSigles.getText().toString());
        bd.tanca();
        finish();
    }
}
