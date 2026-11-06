
package com.nokara.servlets;

import com.nokara.dao.FormatoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class FormatServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        FormatoDAO formatoDAO = new FormatoDAO(getServletContext());

        try {
            List<Map<String, String>> formats = formatoDAO.listarTodos();

            request.setAttribute("formats", formats);
            request.setAttribute("totalFormatos", formats.size());
            request.setAttribute("fechaConsulta", new Date());

            request.getRequestDispatcher("/subPaginas/formats.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Error al cargar los formatos.", e);
        }
    }
}
