package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.service.FiltroDeLibros;
import com.aluracursos.literalura.service.Api;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.repository.LibroRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Principal implements CommandLineRunner {

    @Autowired
    private LibroRepo libroRepo;

    private List<Libro> listaLibros;

    public Principal() {
        this.listaLibros = new ArrayList<>();
    }

    @Override
    public void run(String... args) throws Exception {
        mostrarMenu();
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("------------------");
            System.out.println("Elija la opción a través de su número:");
            System.out.println("1- buscar libro por título");
            System.out.println("2- listar libros registrados");
            System.out.println("3- listar autores registrados");
            System.out.println("4- listar autores vivos en un determinado año");
            System.out.println("5- listar libros por idioma");
            System.out.println("0- salir");
            System.out.println("------------------");

            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el título del libro:");
                    String titulo = scanner.nextLine();
                    buscarLibroPorTitulo(titulo);
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    System.out.println("Ingrese el año:");
                    int año = scanner.nextInt();
                    listarAutoresVivosPorAno(año);
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida, por favor intente de nuevo.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    private void buscarLibroPorTitulo(String titulo) {
        Api cliente = new Api();
        try {
            String respuesta = cliente.buscarLibroPorTitulo(titulo);
            mostrarResultadosBusqueda(respuesta);
        } catch (IOException | InterruptedException e) {
            System.out.println("Error al buscar el libro: " + e.getMessage());
        }
    }

    private void mostrarResultadosBusqueda(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode nodoRaiz = objectMapper.readTree(jsonResponse);
            JsonNode nodoResultados = nodoRaiz.path("results");

            FiltroDeLibros filtroDeLibros = new FiltroDeLibros();
            Libro libroMasDescargado = filtroDeLibros.obtenerLibroMasDescargado(nodoResultados);

            if (libroMasDescargado != null) {
                libroMasDescargado.setTitulo(libroMasDescargado.getTitulo() != null ? libroMasDescargado.getTitulo() : "N/A");
                libroMasDescargado.setAutor(libroMasDescargado.getAutor() != null ? libroMasDescargado.getAutor() : "N/A");
                libroMasDescargado.setIdioma(libroMasDescargado.getIdioma() != null ? libroMasDescargado.getIdioma() : "N/A");
                libroMasDescargado.setNumeroDeDescargas(libroMasDescargado.getNumeroDeDescargas() > 0 ? libroMasDescargado.getNumeroDeDescargas() : 0);
                libroMasDescargado.setFechaNacimientoAutor(libroMasDescargado.getFechaNacimientoAutor() != null ? libroMasDescargado.getFechaNacimientoAutor() : "N/A");
                libroMasDescargado.setFechaDefuncionAutor(libroMasDescargado.getFechaDefuncionAutor() != null ? libroMasDescargado.getFechaDefuncionAutor() : "N/A");

                System.out.println("Título: " + libroMasDescargado.getTitulo());
                System.out.println("Autor: " + libroMasDescargado.getAutor());
                System.out.println("Idioma: " + libroMasDescargado.getIdioma());
                System.out.println("Número de descargas: " + libroMasDescargado.getNumeroDeDescargas());

                if (!libroMasDescargado.getTitulo().equals("N/A") &&
                        !libroMasDescargado.getAutor().equals("N/A") &&
                        !libroMasDescargado.getIdioma().equals("N/A")) {

                    listaLibros.add(libroMasDescargado);

                    if (libroRepo.findByTituloAndAutor(libroMasDescargado.getTitulo(), libroMasDescargado.getAutor()).isEmpty()) {
                        libroRepo.save(libroMasDescargado);
                    }
                    System.out.println("------------------");
                } else {
                    System.out.println("El libro no tiene toda la información requerida y no se ha guardado.");
                }
            } else {
                System.out.println("No se encontraron resultados.");
            }
        } catch (IOException e) {
            System.out.println("Error al procesar la respuesta JSON: " + e.getMessage());
        }
    }

    private void listarLibrosRegistrados() {

        List<Libro> librosDB = libroRepo.findAll();

        Set<Libro> librosUnicos = new HashSet<>(librosDB);

        if (librosUnicos.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            for (Libro libro : librosUnicos) {
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Idioma: " + libro.getIdioma());
                System.out.println("Número de descargas: " + libro.getNumeroDeDescargas());
                System.out.println("------------------");
            }
        }
    }

    private void listarAutoresRegistrados() {

        List<Libro> librosDB = libroRepo.findAll();


        Set<String> autoresUnicos = new HashSet<>();

        if (librosDB.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            for (Libro libro : librosDB) {
                String autorInfo = "Autor: " + libro.getAutor() + "\n" +
                        "Fecha de Nacimiento: " + libro.getFechaNacimientoAutor() + "\n" +
                        "Fecha de Defunción: " + libro.getFechaDefuncionAutor() + "\n" +
                        "Libro: " + libro.getTitulo() + "\n";
                if (!autoresUnicos.contains(autorInfo)) {
                    autoresUnicos.add(autorInfo);
                    System.out.println(autorInfo);
                    System.out.println("------------------");
                }
            }
        }
    }

    private void listarAutoresVivosPorAno(int año) {

        List<Libro> librosDB = libroRepo.findAll();

        List<Autor> autoresVivos = librosDB.stream()
                .filter(libro -> {
                    String fechaNacimiento = libro.getFechaNacimientoAutor();
                    String fechaDefuncion = libro.getFechaDefuncionAutor();
                    return !fechaNacimiento.equals("N/A") &&
                            (fechaDefuncion.equals("N/A") || Integer.parseInt(fechaDefuncion) >= año) &&
                            Integer.parseInt(fechaNacimiento) <= año;
                })
                .map(libro -> new Autor(libro.getAutor(), libro.getFechaNacimientoAutor(), libro.getFechaDefuncionAutor()))
                .distinct()
                .sorted(Comparator.comparing(Autor::getFechaNacimiento))
                .collect(Collectors.toList());


        if (autoresVivos.isEmpty()) {
            System.out.println("No hay autores vivos en el año " + año + ".");
        } else {
            for (Autor autor : autoresVivos) {
                System.out.println("Autor: " + autor.getNombre());
                System.out.println("Fecha de Nacimiento: " + autor.getFechaNacimiento());
                System.out.println("Fecha de Defunción: " + autor.getFechaDefuncion());
                System.out.println("------------------");
            }
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Función para listar libros por idioma");
    }
}

