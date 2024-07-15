package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LibroRepo extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloAndAutor(String titulo, String autor);
}
