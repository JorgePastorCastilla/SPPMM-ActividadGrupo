package com.example.actividadgrupo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ArrayClasse extends ArrayAdapter<Classe> {
    private Context context;
    private ArrayList<Classe> classes;

    public ArrayClasse(@NonNull Context context, int resource, ArrayList<Classe> classes) {
        super(context, resource, classes);
        this.context = context;
        this.classes = classes;
    }

    public View getView(final int position, final View convertView, ViewGroup parent) {
        //agafam el Item per posició a l'Array
        final Classe classe = classes.get(position);
        //agafam "l'inflater" per "inflar" el layout per a cada item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.classe, null);

        //instanciam cada element del layout a utilitzar

        TextView nom = (TextView) view.findViewById(R.id.classeTextView);
        //omplim les dades
        nom.setText(classe.getNom());

        return view;
    }
}
