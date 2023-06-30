package com.example.appqr.MenuInicio;

// Clase Docente
public class Docente {
    private String nombre;
    private String correo;
    private String direccion;
    private String celular;
    private String idCurso;
    private String contraseña;

    public Docente() {
        // Constructor vacío requerido para Firebase
    }

    public Docente(String nombre, String correo, String direccion, String celular, String idCurso, String contraseña) {
        this.nombre = nombre;
        this.correo = correo;
        this.direccion = direccion;
        this.celular = celular;
        this.idCurso = idCurso;
        this.contraseña = contraseña;
    }

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
