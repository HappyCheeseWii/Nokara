package com.nokara.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //Obtener parametros enviados del html
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean nError = false;

        //Validar que no hayan campos vacios

        if (email==null || email.trim().isEmpty()) {
            out.println("<h3>Error: Correo obligatorio</h3>");
            nError = true;
        }
        if (password==null || password.trim().isEmpty()) {
            out.println("<h3>Error: Contraseña obligatoria</h3>");
            nError = true;
        }

        if (nError) {
            return;
        }

        response.sendRedirect("index.html");
    }

    public void destroy() {

        super.destroy();
    }
}
