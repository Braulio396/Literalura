package com.aluracursos.literalura.service;

import com.aluracursos.literalura.model.Libro;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FiltroDeLibros {

    public Libro obtenerLibroMasDescargado(JsonNode nodoResultados) {
        List<Libro> libros = new ArrayList<>();
        for (JsonNode nodoLibro : nodoResultados) {
            Libro libro = new Libro();
            libro.setTitulo(nodoLibro.path("title").asText("N/A"));
            libro.setAutor(nodoLibro.path("authors").get(0).path("name").asText("N/A"));
            libro.setIdioma(nodoLibro.path("languages").get(0).asText("N/A"));
            libro.setNumeroDeDescargas(nodoLibro.path("download_count").asInt(0));
            libro.setFechaNacimientoAutor(nodoLibro.path("authors").get(0).path("birth_year").asText("N/A"));
            libro.setFechaDefuncionAutor(nodoLibro.path("authors").get(0).path("death_year").asText("N/A"));
            libros.add(libro);
        }

        return libros.stream().max((l1, l2) -> Integer.compare(l1.getNumeroDeDescargas(), l2.getNumeroDeDescargas()))
                .orElse(new Libro("N/A", "N/A", "N/A", 0, "N/A", "N/A"));
    }
}
