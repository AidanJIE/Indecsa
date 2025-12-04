package com.example.indecsa;

public class Trabajador {
    private String nombre;
    private String descripcion;
    private String especialidad;
    private String estado;
    private int experiencia;
    private String disponibilidad;
    private int imagenResId;

    public Trabajador(String nombre, String descripcion, String especialidad,
                      String estado, int experiencia, String disponibilidad, int imagenResId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.especialidad = especialidad;
        this.estado = estado;
        this.experiencia = experiencia;
        this.disponibilidad = disponibilidad;
        this.imagenResId = imagenResId;
    }

    // Getters
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public String getEspecialidad() { return especialidad; }
    public String getEstado() { return estado; }
    public int getExperiencia() { return experiencia; }
    public String getDisponibilidad() { return disponibilidad; }
    public int getImagenResId() { return imagenResId; }
}