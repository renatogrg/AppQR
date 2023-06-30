package com.example.appqr.MenuInicio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.appqr.MenuDocente;
import com.example.appqr.R;
import com.example.appqr.TareasDocente;

public class MenuAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);
    }

    public void RegistrarProfesores(View view) {
        Intent intent=new Intent(MenuAdmin.this, RegistrarDocente.class);
        startActivity(intent);
    }
}