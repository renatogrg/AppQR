package com.example.appqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MenuEstudiante extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_estudiante);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.configuraciones)
        {
            Intent intent=new Intent(MenuEstudiante.this, Configuracion.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void llamarTareasEstudiante(View view) {
        Intent intent=new Intent(MenuEstudiante.this, TareasEstudiante.class);
        startActivity(intent);
    }

    public void llamarAsistenciaEstudiante(View view) {
        Intent intent=new Intent(MenuEstudiante.this, AsistenciaEstudiante.class);
        startActivity(intent);
    }

    public void llamarEscanearQR(View view) {
        Intent intent=new Intent(MenuEstudiante.this, Escanear.class);
        startActivity(intent);
    }
}