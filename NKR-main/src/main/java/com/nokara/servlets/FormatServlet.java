
package com.nokara.servlets;

import com.nokara.dao.FormatoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FormatServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FormatoDAO formatoDAO = new FormatoDAO(getServletContext());

        try {
            List<Map<String, String>> formats = formatoDAO.listarTodos();

            request.setAttribute("formats", formats);

            request.getRequestDispatcher("/subPaginas/formats.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Error al cargar los formatos."
            );
        }
    }
}
