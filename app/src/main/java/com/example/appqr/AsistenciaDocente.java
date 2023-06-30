package com.example.appqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AsistenciaDocente extends AppCompatActivity {
    Spinner spnRegCurso;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asistencia_docente);
        spnRegCurso=findViewById(R.id.spnCurso);
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        firebaseFirestore.setFirestoreSettings(settings);
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            DatosSpinner(userId);
        }
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
            Intent intent=new Intent(AsistenciaDocente.this, Configuracion.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void volvermenudocente(View view) {
        Intent intent=new Intent(AsistenciaDocente.this, MenuDocente.class);
        startActivity(intent);
    }
    private void DatosSpinner(String idUser){
        firebaseFirestore.collection("tblCursoDocente")
                .whereEqualTo("idUsuario", idUser)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<String> cursos = new ArrayList<>();

                        for (DocumentSnapshot document : queryDocumentSnapshots) {
                            String nombreCurso = document.getString("curDocNombre");
                            cursos.add(nombreCurso);
                        }

                        // Crear un adaptador para el Spinner
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(AsistenciaDocente.this,
                                android.R.layout.simple_spinner_item, cursos);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                        // Establecer el adaptador en el Spinner
                        spnRegCurso.setAdapter(adapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("ERROR", "Error al obtener los cursos desde Firebase", e);
                    }
                });
    }
}