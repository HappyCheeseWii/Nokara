package com.nokara.servlets;

import com.nokara.config.ConexionBD;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/health")
public class HealthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");

        try (var connection = ConexionBD.getConnection();
             var statement = connection.createStatement()) {
            statement.setQueryTimeout(5);
            try (var rows = statement.executeQuery("""
                    SELECT 1 FROM users, cases, documents WHERE 1 = 0
                    """)) {
                response.getWriter().println("{\"tomee\":\"up\",\"database\":\"up\"}");
            }
        } catch (SQLException e) {
            log("Error verificando NokaraDB", e);
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            response.getWriter().println("{\"tomee\":\"up\",\"database\":\"down\"}");
        }
    }
}
