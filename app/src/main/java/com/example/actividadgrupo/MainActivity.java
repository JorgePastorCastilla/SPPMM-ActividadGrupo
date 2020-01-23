package com.example.actividadgrupo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> classes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        classes.add("Clase 1");
        classes.add("Clase 2");
        classes.add("Clase 1");
        classes.add("Clase 2");
        classes.add("Clase 1");
        classes.add("Clase 2");
        classes.add("Clase 1");
        classes.add("Clase 2");
        classes.add("Clase 1");
        classes.add("Clase 2");
        classes.add("Clase 1");
        classes.add("Clase 2");
        classes.add("Clase 1");
        classes.add("Clase 2");
        classes.add("Clase 1");
        classes.add("Clase 2");
        classes.add("Clase 1");
        classes.add("Clase 2");
        classes.add("Clase 1");
        classes.add("Clase 2");
        classes.add("Clase 1");
        classes.add("Clase 2");
        classes.add("Clase 1");
        classes.add("Clase 2");
        classes.add("Clase 1");
        classes.add("Clase 2");
        classes.add("Clase 1");
        classes.add("Clase 2");
        classes.add("Clase 1");
        classes.add("Clase 2");
        classes.add("Clase 1");
        classes.add("Clase 2");
        classes.add("Clase 1");
        classes.add("Clase 2");
        classes.add("Clase 1");
        classes.add("Clase 2");
        classes.add("Clase 1");
        classes.add("Clase 2");
        classes.add("Clase 1");
        classes.add("Clase 2");
        classes.add("Clase 1");
        classes.add("Clase 2");
        classes.add("Clase 1");
        classes.add("Clase 2");
        displayView();
    }

    private void displayView() {
        ArrayAdapter<String> a = new ArrayAdapter<>(this, R.layout.classe, classes);
        ListView list = findViewById(R.id.listView);
        list.setAdapter(a);
    }
}
