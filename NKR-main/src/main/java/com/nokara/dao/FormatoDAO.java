package com.nokara.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FormatoDAO {

    private final ServletContext context;
    private final ObjectMapper mapper = new ObjectMapper();

    public FormatoDAO(ServletContext context) {
        this.context = context;
    }

    public List<Map<String, String>> listarTodos() throws IOException {

        String ruta = "/WEB-INF/datos/formatos.json";

        try (InputStream entrada = context.getResourceAsStream(ruta)) {

            if (entrada == null) {
                throw new IOException("No se encontró el archivo: " + ruta);
            }

            List<Map<String, String>> formatos = mapper.readValue(
                    entrada,
                    new TypeReference<List<Map<String, String>>>() {}
            );

            return formatos != null ? formatos : new ArrayList<>();
        }
    }
}