package com.example.indecsa;

public class Trabajador {
    private String nombre;
    private String descripcion;
    private String especialidad;
    private String equipo;
    private int experiencia; // 1-5 estrellas
    private String disponibilidad;
    private int imagenResId; // ID del recurso drawable

    public Trabajador(String nombre, String descripcion, String especialidad,
                      String equipo, int experiencia, String disponibilidad, int imagenResId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.especialidad = especialidad;
        this.equipo = equipo;
        this.experiencia = experiencia;
        this.disponibilidad = disponibilidad;
        this.imagenResId = imagenResId;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getEspecialidad() { return especialidad; }
    public String getEquipo() { return equipo; }
    public int getExperiencia() { return experiencia; }
    public String getDisponibilidad() { return disponibilidad; }
    public int getImagenResId() { return imagenResId; }
}