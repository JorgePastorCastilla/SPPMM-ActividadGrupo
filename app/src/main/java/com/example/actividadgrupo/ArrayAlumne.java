package com.example.actividadgrupo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
//        View view = inflater.inflate(R.layout.alumne_list_item, null);

        //instanciam cada element del layout a utilitzar
        Button positiu = view.findViewById(R.id.positiu);
        positiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                LlistaAlumnes.onClickSuma();
                Toast.makeText(getContext(), "tu puta madre",Toast.LENGTH_SHORT).show();
            }
        });



        TextView nom = (TextView) view.findViewById(R.id.nomAlumne);
        //omplim les dades
        String alumneNom = alumne.getNom();
        alumneNom = alumneNom.substring(0,1).toUpperCase() + alumneNom.substring(1);
        String alumneLlinatges = alumne.getLlinatges();
        alumneLlinatges = alumneLlinatges.substring(0,1).toUpperCase() + alumneLlinatges.substring(1);

        //AQUI ALOMEJOR PODRIAMOS SUMAR EL TOTAL DE POSITIVOS CON EL FORMATO: Nombre Apellido1 Apellido2 (NumeroPositivos)

        String alumneText = alumneNom + " " + alumneLlinatges;
        alumneText += " " + alumne.getPositiusString();

        nom.setText(alumneText);

        return view;
    }

}