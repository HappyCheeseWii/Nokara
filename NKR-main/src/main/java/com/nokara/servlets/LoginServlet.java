package com.nokara.servlets;

import java.io.IOException;
import com.nokara.models.Usuario;
import java.sql.SQLException;

import com.nokara.dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import jakarta.servlet.http.Cookie;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UsuarioDAO usuarioDAO;

    public void init() {
        usuarioDAO = new UsuarioDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        //Obtener parametros enviados del html
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        email = email == null ? "" : email.trim();
        password = password == null ? "" : password;

        request.setAttribute("email",email);

        if (email != null && !email.trim().isEmpty()) {
            Cookie cookieCorreo = new Cookie(
                    "correoRecordado",
                    URLEncoder.encode(email.trim(), StandardCharsets.UTF_8)
            );

            cookieCorreo.setMaxAge(7 * 24 * 60 * 60);
            cookieCorreo.setHttpOnly(true);
            cookieCorreo.setPath(request.getContextPath().isEmpty()
                    ? "/" : request.getContextPath());

            response.addCookie(cookieCorreo);
        }

        //Validar correo
        if (email.isEmpty()) {
            request.setAttribute("errorEmail","Correo obligatorio");
        }
        if(password.isEmpty()) {
            request.setAttribute("errorPassword","Contraseña obligatoria");
        }

        if (email.isEmpty() || password.isEmpty()) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        }
        try {
            // Usuario temporal mientras se configura MariaDB
            if (email.equals("admin@nokara.com")
                    && password.equals("123456")) {

                Usuario usuario = new Usuario(
                        "admin",
                        "admin@nokara.com",
                        "123456"
                );

                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);
                session.setMaxInactiveInterval(30 * 60);

                response.sendRedirect(
                        request.getContextPath() + "/index.jsp"
                );
                return;
            }

            // Mientras no haya base de datos, rechazar otros usuarios
            request.setAttribute("errorEmail", "Credenciales incorrectas");
            request.getRequestDispatcher("/login.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Error al iniciar sesión", e);
        }
    }
}
