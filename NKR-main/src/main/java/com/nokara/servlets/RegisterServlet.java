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

        //Validar q no hayan campos vacios
        if (username == null || username.trim().isEmpty() || email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {

            request.setAttribute("mensaje_error", "Todos los campos son obligatorios");

            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        //Guardar temporalmente datos
        getServletContext().setAttribute("usuario", username);
        getServletContext().setAttribute("correo", email);
        getServletContext().setAttribute("password", password);

        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    public void destroy() {

        super.destroy();
    }
}
