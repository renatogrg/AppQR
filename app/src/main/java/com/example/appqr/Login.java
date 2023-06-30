package com.example.appqr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.appqr.MenuInicio.MenuAdmin;
import com.example.appqr.preferences;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private EditText username, password;
    private Button login;
    private Switch active;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.edtEmail);
        password = findViewById(R.id.edtPassword);
        login = findViewById(R.id.btnLogin);
        active = findViewById(R.id.active);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("login").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String input1 = username.getText().toString();
                        String input2 = password.getText().toString();

                        if (dataSnapshot.child(input1).exists()) {
                            if (dataSnapshot.child(input1).child("password").getValue(String.class).equals(input2)) {
                                if (active.isChecked()) {
                                    if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("admin")) {
                                        preferences.setDataLogin(Login.this, true);
                                        preferences.setDataAs(Login.this, "admin");
                                        startActivity(new Intent(Login.this, MenuAdmin.class));
                                    } else if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("docente")) {
                                        preferences.setDataLogin(Login.this, true);
                                        preferences.setDataAs(Login.this, "docente");
                                        startActivity(new Intent(Login.this, MenuDocente.class));
                                    } else if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("user")) {
                                        preferences.setDataLogin(Login.this, true);
                                        preferences.setDataAs(Login.this, "user");
                                        startActivity(new Intent(Login.this, MenuEstudiante.class));
                                    }
                                } else {
                                    if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("admin")) {
                                        preferences.setDataLogin(Login.this, false);
                                        startActivity(new Intent(Login.this, MenuAdmin.class));
                                    } else if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("docente")) {
                                        preferences.setDataLogin(Login.this, false);
                                        startActivity(new Intent(Login.this, MenuDocente.class));
                                    } else if (dataSnapshot.child(input1).child("as").getValue(String.class).equals("user")) {
                                        preferences.setDataLogin(Login.this, false);
                                        startActivity(new Intent(Login.this, MenuEstudiante.class));
                                    }
                                }
                            } else {
                                Toast.makeText(Login.this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Login.this, "El usuario no está registrado", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (preferences.getDataLogin(this)) {
            String userType = preferences.getDataAs(this);
            if (userType.equals("admin")) {
                startActivity(new Intent(Login.this, MenuAdmin.class));
            } else if (userType.equals("docente")) {
                startActivity(new Intent(Login.this, MenuDocente.class));
            } else if (userType.equals("user")) {
                startActivity(new Intent(Login.this, MenuEstudiante.class));
            }
        }
    }
}
