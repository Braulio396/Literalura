# LiterAlura

LiterAlura es una aplicación de consola en Java desarrollada con Spring Boot que interactúa con la API de Gutendex para buscar y gestionar libros. Esta aplicación permite realizar búsquedas de libros, listar libros y autores registrados, y otras funcionalidades relacionadas con la gestión de libros.

## Funcionalidades

- **Buscar libro por título**: Busca un libro en la API de Gutendex por su título y muestra la información del libro más descargado.
- **Listar libros registrados**: Muestra una lista de todos los libros guardados en la base de datos.
- **Listar autores registrados**: Muestra una lista de todos los autores registrados junto con su información y los libros asociados.
- **Listar autores vivos en un determinado año**: Muestra todos los autores que estaban vivos en el año ingresado.
- **Listar libros por idioma**: Lista los libros según su idioma.
- **Salir**: Termina la ejecución del programa.

## Uso

Al ejecutar la aplicación, se mostrará un menú en la consola. Selecciona la opción deseada ingresando el número correspondiente y sigue las instrucciones.

1. **Buscar libro por título**: Ingresa el título del libro.
2. **Listar libros registrados**: Muestra los libros registrados en la base de datos.
3. **Listar autores registrados**: Muestra los autores y sus libros.
4. **Listar autores vivos en un determinado año**: Ingresa el año para ver los autores vivos en ese año.
5. **Listar libros por idioma**: Lista los libros según el idioma.
0. **Salir**: Finaliza el programa.

## Requisitos

- Java 11 o superior
- Spring Boot 2.5 o superior
- Conexión a internet para acceder a la API de Gutendex
- Base de datos SQL (ej. MySQL, PostgreSQL)
- Maven 3.6 o superior
