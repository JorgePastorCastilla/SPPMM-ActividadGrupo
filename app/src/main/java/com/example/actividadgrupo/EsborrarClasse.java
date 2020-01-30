package com.example.actividadgrupo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EsborrarClasse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_esborrar_classe);
    }

    public void onClickEsborra(View v) {
        DBInterface bd;
        EditText editID = findViewById(R.id.esborraClasseEditText);
        bd = new DBInterface(this.getApplicationContext());
        bd.obre();
        long id = Long.parseLong(editID.getText().toString());
        boolean result = bd.esborraClasse(id);
        if (result) {
            Toast.makeText(this, "Element esborrat", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "No s’ha pogut esborrar l’element",
                    Toast.LENGTH_SHORT).show();
        }
        bd.tanca();
        finish();
    }
}
