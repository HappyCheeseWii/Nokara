
package com.nokara.servlets;

import com.nokara.ejb.UserService;
import com.nokara.models.Usuario;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        username = username == null ? "" : username.trim();
        email = email == null ? "" : email.trim();
        password = password == null ? "" : password;

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            request.setAttribute(
                    "mensaje_error",
                    "Todos los campos son obligatorios"
            );

            request.getRequestDispatcher("/register.jsp")
                    .forward(request, response);
            return;
        }

        try {
            if (userService.existeCorreo(email)) {
                request.setAttribute(
                        "mensaje_error",
                        "El correo ya se encuentra registrado."
                );

                request.getRequestDispatcher("/register.jsp")
                        .forward(request, response);
                return;
            }

            Usuario usuario = new Usuario(username, email, password);

            if (userService.registrar(usuario)) {
                request.setAttribute(
                        "mensaje_exito",
                        "¡Registro exitoso! Ya puedes iniciar sesión."
                );

                request.getRequestDispatcher("/login.jsp")
                        .forward(request, response);
            } else {
                request.setAttribute(
                        "mensaje_error",
                        "No se pudo registrar el usuario."
                );

                request.getRequestDispatcher("/register.jsp")
                        .forward(request, response);
            }

        } catch (Exception e) {
            throw new ServletException("Error al registrar el usuario", e);
        }
    }
}
