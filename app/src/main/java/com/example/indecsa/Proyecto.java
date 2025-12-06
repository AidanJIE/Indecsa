package com.example.indecsa;

// CAMBIO AQUÍ: Nombre de la clase
public class Proyecto {

    // CAMBIOS DE ATRIBUTOS
    private String proyectito;       // Anteriormente nombre
    private String descripcion;
    private String especialidad;
    private String estado;
    private int avance;              // Anteriormente experiencia
    private String direccion;        // Anteriormente disponibilidad
    private int imagenResId;

    // CONSTRUCTOR ACTUALIZADO
    public Proyecto(String proyectito, String descripcion, String especialidad,
                    String estado, int avance, String direccion, int imagenResId) {
        this.proyectito = proyectito;
        this.descripcion = descripcion;
        this.especialidad = especialidad;
        this.estado = estado;
        this.avance = avance;
        this.direccion = direccion;
        this.imagenResId = imagenResId;
    }

    // GETTERS ACTUALIZADOS
    public String getProyectito() { return proyectito; }
    public String getDescripcion() { return descripcion; }
    public String getEspecialidad() { return especialidad; }
    public String getEstado() { return estado; }
    public int getAvance() { return avance; }
    public String getDireccion() { return direccion; }
    public int getImagenResId() { return imagenResId; }
}