package com.nokara.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (username == null || username.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {

            request.setAttribute("mensaje_error", "Todos los campos son obligatorios");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        request.setAttribute(
                "mensaje_error",
                "El registro estará disponible cuando se configure la base de datos."
        );

        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }
}