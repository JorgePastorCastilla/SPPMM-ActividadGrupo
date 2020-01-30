package com.example.actividadgrupo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ArrayAlumne extends ArrayAdapter<Alumne>{


    private Context context;
    private ArrayList<Alumne> alumnes;

    public ArrayAlumne(@NonNull Context context, int resource, ArrayList<Alumne> classes) {
        super(context, resource, classes);
        this.context = context;
        this.alumnes = classes;
    }

    public View getView(final int position, final View convertView, ViewGroup parent) {
        //agafam el Item per posició a l'Array
        final Alumne alumne = alumnes.get(position);
        //agafam "l'inflater" per "inflar" el layout per a cada item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.alumne_list_item, null);

        //instanciam cada element del layout a utilitzar

        TextView nom = (TextView) view.findViewById(R.id.nomAlumne);
        //omplim les dades
        nom.setText(alumne.getNom() + " " + alumne.getLlinatges());

        return view;
    }
}