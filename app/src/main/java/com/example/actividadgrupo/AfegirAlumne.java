package com.example.actividadgrupo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AfegirAlumne extends AppCompatActivity {

    private static final int APP_PERMISSION_READ_STORAGE = 404;
    private static final int GALLERY_REQUEST_CODE = 400;
    DBAlumne bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afegir_alumne);
    }

    public void seleccionaIMG(View v) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    APP_PERMISSION_READ_STORAGE);
        }
        recullDeGaleria();
    }

    private void recullDeGaleria(){
        //Create an Intent with action as ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent,GALLERY_REQUEST_CODE);
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
        int idClasse = data.getInt("idClasse");
        if (bd.addAlumne( nom,llinatges,poblacio,direccio,telefon,idClasse+"" ) != -1) {
            Toast.makeText(this, "Alumne creat correctament", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error a l’afegir", Toast.LENGTH_SHORT).show();
        }
        bd.tanca();
        finish();
    }


}
