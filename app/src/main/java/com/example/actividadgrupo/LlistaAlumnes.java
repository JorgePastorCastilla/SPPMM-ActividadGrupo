package com.example.actividadgrupo;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.database.Cursor;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.util.ArrayList;

public class LlistaAlumnes extends AppCompatActivity {
    private ArrayList<Alumne> alumnes = new ArrayList<>();
    ArrayAdapter<Alumne> adapter;
    Button borrarButton;
    Button cancelarButton;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista_alumnes);

        displayView();
    }
    private void displayView() {
        cancelarButton = findViewById(R.id.cancelarButtonAlumne);
        borrarButton = findViewById(R.id.deleteAlumneButton);
        DBAlumne bd;
        bd = new DBAlumne(getApplicationContext());
        list = findViewById(R.id.listViewAlumne);
        bd.obre();
        Bundle data = getIntent().getExtras();
        int idClasse = data.getInt("idClasse");
        Cursor c = bd.allAlumnes(idClasse+"");
        //Toast.makeText(getApplicationContext(), idClasse+"", Toast.LENGTH_SHORT).show();


        c.moveToFirst();
        alumnes = new ArrayList<>();
        while (!c.isAfterLast()) {
            alumnes.add(new Alumne(c.getInt(0), c.getString(1), c.getString(2), c.getString(3), c.getString(4), c.getString(5)));
            c.moveToNext();
        }
        bd.tanca();

        adapter = new ArrayAlumne(this, R.layout.alumne_list_item, alumnes);
        list.setAdapter(adapter);
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, final int pos, final long id) {
                borrarButton.setEnabled(true);
                cancelarButton.setVisibility(View.VISIBLE);
                cancelarButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        borrarButton.setEnabled(false);
                        cancelarButton.setVisibility(View.INVISIBLE);
                    }
                });
                borrarButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Alumne alumneABorrar = adapter.getItem(pos);
                        onClickEsborra(v, alumneABorrar);
                    }
                });
                Toast.makeText(getApplicationContext(), "Pots borrar l'alumne", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayView();
    }

    public void afegeixAlumne(View view) {
        borrarButton.setEnabled(false);
        cancelarButton.setVisibility(View.INVISIBLE);
        Intent i = new Intent(getApplicationContext(), AfegirAlumne.class);
        Bundle data = getIntent().getExtras();
        int idClasse = data.getInt("idClasse");
        i.putExtra("idClasse",idClasse);
        startActivity(i);
    }


    public void onClickEsborra(View v, Alumne alumneABorrar) {
        DBAlumne bd;
        DBAlumneClasse bd2;
        bd = new DBAlumne(this.getApplicationContext());
        bd2 = new DBAlumneClasse(this.getApplicationContext());
        bd.obre();
        bd2.obre();
        long id = alumneABorrar.getId();
        Bundle data = getIntent().getExtras();
        int idClasse = data.getInt("idClasse");
        boolean result = bd.delete(id);
        boolean result2 = bd2.delete(id, idClasse);
        if (result) {
            Toast.makeText(this, "Alumne borrat", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "No s’ha pogut borrar l'alumne",
                    Toast.LENGTH_SHORT).show();
        }
        bd.tanca();
//        bd2.tanca();
        displayView();
        borrarButton.setEnabled(false);
        cancelarButton.setVisibility(View.INVISIBLE);
    }
}
