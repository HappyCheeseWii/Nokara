
package com.nokara.servlets;

import com.nokara.ejb.UserService;
import com.nokara.models.Usuario;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private UserService userService;

    public LoginServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        email = email == null ? "" : email.trim();
        password = password == null ? "" : password;

        request.setAttribute("email", email);

        if (!email.isEmpty()) {
            Cookie cookieCorreo = new Cookie(
                    "correoRecordado",
                    URLEncoder.encode(email, StandardCharsets.UTF_8)
            );

            cookieCorreo.setMaxAge(7 * 24 * 60 * 60);
            cookieCorreo.setHttpOnly(true);
            cookieCorreo.setPath(request.getContextPath().isEmpty()
                    ? "/" : request.getContextPath());

            response.addCookie(cookieCorreo);
        }

        if (email.isEmpty()) {
            request.setAttribute("errorEmail", "Correo obligatorio");
        }

        if (password.isEmpty()) {
            request.setAttribute("errorPassword", "Contraseña obligatoria");
        }

        if (email.isEmpty() || password.isEmpty()) {
            request.getRequestDispatcher("/login.jsp")
                    .forward(request, response);
            return;
        }

        try {
            Usuario usuario = userService.buscarPorCorreo(email);

            if (usuario != null &&
                    userService.validarPassword(usuario, password)) {

                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
                session.setMaxInactiveInterval(30 * 60);

                response.sendRedirect(
                        request.getContextPath() + "/index.jsp"
                );
                return;
            }

            request.setAttribute(
                    "errorEmail",
                    "Correo o contraseña incorrectos"
            );

            request.getRequestDispatcher("/login.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error al iniciar sesión", e);
        }
    }
}
