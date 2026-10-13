package com.nokara.servlets;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class FormatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        InputStream archivo = getClass()
                .getClassLoader()
                .getResourceAsStream("../assets/formats.json");

        ObjectMapper mapper = new ObjectMapper();

        List<Map<String, String>> formats = mapper.readValue(
                archivo,
                new TypeReference<List<Map<String, String>>>() {}
        );

        request.setAttribute("formats", formats);

        request.getRequestDispatcher("/subpaginas/formats.jsp")
                .forward(request, response);
    }
}