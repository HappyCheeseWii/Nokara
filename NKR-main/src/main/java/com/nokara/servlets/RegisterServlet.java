package com.nokara.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //Obtener parametros enviados del html
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean nError = false;

        //Validar q no hayan campos vacios


        if (username==null || username.trim().isEmpty()) {
            out.println("<h3>Error: Nombre de usuario obligatorio</h3>");
            nError = true;
        }
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

        out.println("<h2>Usuario registrado</h2>");
        out.println("<p>Nombre:"+ username +"</p>");
        out.println("<p>Email:"+ email +"</p>");
        out.println("<p>Password:"+ password +"</p>");
    }

    public void destroy() {

        super.destroy();
    }
}
