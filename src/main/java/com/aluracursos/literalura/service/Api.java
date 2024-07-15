package com.aluracursos.literalura.service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Api {

    private static final String BASE_URL = "https://gutendex.com/books/";

    public String buscarLibroPorTitulo(String titulo) throws IOException, InterruptedException {
        String tituloCodificado = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
        String url = BASE_URL + "?search=" + tituloCodificado;

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());

        return respuesta.body();
    }
}
