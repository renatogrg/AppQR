package com.example.appqr.MenuInicio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appqr.Adapter.CheckableSpinnerAdapter;
import com.example.appqr.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrarDocente extends AppCompatActivity {
    private EditText txt_prof_nombre, txt_prof_correo, txt_prof_direccion, txt_profe_celular, txt_contraseña;
    private Spinner spnCursoProfesor;
    private Button addTbtn;
    private DatabaseReference databaseReference;
    private List<String> nombresCursos;
    private List<String> idsCursos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_docente);

        txt_prof_nombre = findViewById(R.id.txt_prof_nombre);
        txt_prof_correo = findViewById(R.id.txt_prof_username);
        txt_prof_direccion = findViewById(R.id.txt_prof_direccion);
        txt_profe_celular = findViewById(R.id.txt_profe_celular);
        txt_contraseña = findViewById(R.id.txt_contraseña);
        addTbtn = findViewById(R.id.addTbtn);
        spnCursoProfesor = findViewById(R.id.spnCursoProfesor);

        // Inicializar Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference();
        nombresCursos = new ArrayList<>();
        idsCursos = new ArrayList<>();
        loadCursos();

        addTbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarDocente();
            }
        });
    }

    public void loadCursos() {
        databaseReference.child("cursos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot cursoSnapshot : snapshot.getChildren()) {
                        String id = cursoSnapshot.getKey();
                        String nombre = cursoSnapshot.child("nombre").getValue(String.class);
                        nombresCursos.add(nombre);
                        idsCursos.add(id);
                    }

                    CheckableSpinnerAdapter arrayAdapter = new CheckableSpinnerAdapter(RegistrarDocente.this, android.R.layout.simple_spinner_item, nombresCursos, idsCursos);
                    spnCursoProfesor.setAdapter(arrayAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Manejar el error en caso de que la carga de cursos falle
            }
        });
    }

    public void registrarDocente() {
        String nombre = txt_prof_nombre.getText().toString();
        String direccion = txt_prof_direccion.getText().toString();
        String celular = txt_profe_celular.getText().toString();
        String contraseña = txt_contraseña.getText().toString();

        // Obtener los cursos seleccionados (solo los IDs)
        List<String> cursosSeleccionados = ((CheckableSpinnerAdapter) spnCursoProfesor.getAdapter()).getCheckedItems();

        // Obtener el ID del docente (que será el mismo que el username)
        String username = txt_prof_correo.getText().toString().replace(".", "-");

        // Crear mapa con la información del docente
        Map<String, Object> docente = new HashMap<>();
        docente.put("as", "docente");
        docente.put("password", contraseña);
        docente.put("nombre", nombre);
        docente.put("username", username);
        docente.put("direccion", direccion); // Agregar la dirección al mapa
        docente.put("celular", celular); // Agregar el número de celular al mapa
        docente.put("cursos", cursosSeleccionados);

        // Guardar la información del docente en Firebase en la ubicación adecuada
        databaseReference.child("login").child(username).setValue(docente)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(RegistrarDocente.this, "Docente registrado correctamente", Toast.LENGTH_SHORT).show();
                        limpiarCampos();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegistrarDocente.this, "Error al registrar el docente", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void limpiarCampos() {
        txt_prof_nombre.setText("");
        txt_prof_correo.setText("");
        txt_prof_direccion.setText("");
        txt_profe_celular.setText("");
        txt_contraseña.setText("");
        spnCursoProfesor.setSelection(0);
    }
}
