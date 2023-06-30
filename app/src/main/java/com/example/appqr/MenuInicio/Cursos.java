package com.example.appqr.MenuInicio;


// Clase Curso
public class Cursos {
    private String id;
    private String nombre;

    public Cursos() {
        // Constructor vacío requerido para Firebase
    }

    public Cursos(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
