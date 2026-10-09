package com.nokara.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID;

    static {
        serialVersionUID = 1L;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //Obtener parametros enviados del html
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //Validar q no hayan campos vacios
        if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {

            request.setAttribute("mensaje_error", "Todos los campos son obligatorios");

            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }

        String correoRegistrado =
                (String) getServletContext().getAttribute("correo");

        String passwordRegistrada =
                (String) getServletContext().getAttribute("password");

        // Comprobar los datos guardados temporalmente
        if (email.equals(correoRegistrado)
                && password.equals(passwordRegistrada)) {

            response.sendRedirect(request.getContextPath() + "/index.jsp");

        } else {

            request.setAttribute("mensaje_error",
                    "Correo o contraseña incorrectos");

            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
    public void destroy() {

        super.destroy();
    }
}
