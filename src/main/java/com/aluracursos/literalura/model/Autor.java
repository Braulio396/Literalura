package com.aluracursos.literalura.model;

import java.util.Objects;

public class Autor {
    private String nombre;
    private String fechaNacimiento;
    private String fechaDefuncion;

    public Autor(String nombre, String fechaNacimiento, String fechaDefuncion) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento != null ? fechaNacimiento : "N/A";
        this.fechaDefuncion = fechaDefuncion != null ? fechaDefuncion : "N/A";
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaDefuncion() {
        return fechaDefuncion;
    }

    public void setFechaDefuncion(String fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(nombre, autor.nombre) &&
                Objects.equals(fechaNacimiento, autor.fechaNacimiento) &&
                Objects.equals(fechaDefuncion, autor.fechaDefuncion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, fechaNacimiento, fechaDefuncion);
    }
}
