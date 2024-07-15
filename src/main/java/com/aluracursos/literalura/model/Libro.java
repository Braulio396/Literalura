package com.aluracursos.literalura.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo = "N/A";
    private String autor = "N/A";
    private String idioma = "N/A";
    private int numeroDeDescargas = 0;
    private String fechaNacimientoAutor = "N/A";
    private String fechaDefuncionAutor = "N/A";

    // Constructor vacío
    public Libro() {
    }

    public Libro(String titulo, String autor, String idioma, int numeroDeDescargas, String fechaNacimientoAutor, String fechaDefuncionAutor) {
        this.titulo = titulo != null ? titulo : "N/A";
        this.autor = autor != null ? autor : "N/A";
        this.idioma = idioma != null ? idioma : "N/A";
        this.numeroDeDescargas = numeroDeDescargas > 0 ? numeroDeDescargas : 0;
        this.fechaNacimientoAutor = fechaNacimientoAutor != null ? fechaNacimientoAutor : "N/A";
        this.fechaDefuncionAutor = fechaDefuncionAutor != null ? fechaDefuncionAutor : "N/A";
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo != null ? titulo : "N/A";
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor != null ? autor : "N/A";
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma != null ? idioma : "N/A";
    }

    public int getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(int numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas > 0 ? numeroDeDescargas : 0;
    }

    public String getFechaNacimientoAutor() {
        return fechaNacimientoAutor;
    }

    public void setFechaNacimientoAutor(String fechaNacimientoAutor) {
        this.fechaNacimientoAutor = fechaNacimientoAutor != null ? fechaNacimientoAutor : "N/A";
    }

    public String getFechaDefuncionAutor() {
        return fechaDefuncionAutor;
    }

    public void setFechaDefuncionAutor(String fechaDefuncionAutor) {
        this.fechaDefuncionAutor = fechaDefuncionAutor != null ? fechaDefuncionAutor : "N/A";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return numeroDeDescargas == libro.numeroDeDescargas &&
                Objects.equals(id, libro.id) &&
                Objects.equals(titulo, libro.titulo) &&
                Objects.equals(autor, libro.autor) &&
                Objects.equals(idioma, libro.idioma) &&
                Objects.equals(fechaNacimientoAutor, libro.fechaNacimientoAutor) &&
                Objects.equals(fechaDefuncionAutor, libro.fechaDefuncionAutor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, autor, idioma, numeroDeDescargas, fechaNacimientoAutor, fechaDefuncionAutor);
    }
}

