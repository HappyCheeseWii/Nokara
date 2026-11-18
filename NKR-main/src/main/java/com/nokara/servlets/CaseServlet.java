
package com.nokara.servlets;

import com.nokara.ejb.CaseService;
import com.nokara.models.Case;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@MultipartConfig
public class CaseServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    private CaseService caseService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!validarSesion(request, response)) {
            return;
        }

        String accion = request.getParameter("accion");

        if ("ver".equals(accion)) {
            Long id = obtenerId(request);

            Case caso = caseService.buscarPorId(id);

            if (caso == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }

            request.setAttribute("caso", caso);

        } else {
            List<Case> casos = caseService.listarTodos();
            request.setAttribute("casos", casos);
            request.setAttribute("totalCasos", caseService.contarCasos());
        }

        request.getRequestDispatcher("/subPaginas/casos.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!validarSesion(request, response)) {
            return;
        }

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");

        switch (accion == null ? "" : accion) {

            case "registrar":
                registrarCaso(request, response);
                break;

            case "actualizar":
                actualizarCaso(request, response);
                break;

            case "eliminar":
                eliminarCaso(request, response);
                break;

            case "agregarDocumento":
                agregarDocumento(request, response);
                break;

            case "eliminarDocumento":
                eliminarDocumento(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void registrarCaso(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        if (title == null || title.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El título es obligatorio.");
            return;
        }

        Case caso = new Case();
        caso.setTitle(title.trim());
        caso.setDescription(description);

        caseService.registrar(caso);

        response.sendRedirect(request.getContextPath() + "/CaseServlet");
    }

    private void actualizarCaso(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Long id = obtenerId(request);

        Case caso = new Case();
        caso.setId(id);
        caso.setTitle(request.getParameter("title"));
        caso.setDescription(request.getParameter("description"));

        if (!caseService.actualizar(caso)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/CaseServlet");
    }

    private void eliminarCaso(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Long id = obtenerId(request);

        if (!caseService.eliminar(id)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/CaseServlet");
    }

    private void agregarDocumento(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        Long caseId = obtenerId(request);

        Part archivo = request.getPart("archivo");

        if (archivo == null || archivo.getSize() == 0) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Debe seleccionar un archivo.");
            return;
        }

        String nombreOriginal = Paths.get(archivo.getSubmittedFileName())
                .getFileName().toString();

        String carpeta = getServletContext().getRealPath("/uploads");

        if (carpeta == null) {
            throw new ServletException("No se pudo determinar la ruta de almacenamiento.");
        }

        Path directorio = Paths.get(carpeta);
        Files.createDirectories(directorio);

        String nombreAlmacenado = java.util.UUID.randomUUID() + "_" + nombreOriginal;
        Path destino = directorio.resolve(nombreAlmacenado);

        archivo.write(destino.toString());

        Case.DocumentInfo documento = new Case.DocumentInfo();
        documento.setOriginalFileName(nombreOriginal);
        documento.setCustomTitle(request.getParameter("customTitle"));
        documento.setFileDescription(request.getParameter("fileDescription"));
        documento.setFilePath("uploads/" + nombreAlmacenado);

        if (!caseService.agregarDocumento(caseId, documento)) {
            Files.deleteIfExists(destino);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/CaseServlet?accion=ver&id=" + caseId);
    }

    private void eliminarDocumento(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Long id = obtenerId(request);

        if (!caseService.eliminarDocumento(id)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/CaseServlet");
    }

    private Long obtenerId(HttpServletRequest request) throws IOException {
        try {
            return Long.parseLong(request.getParameter("id"));
        } catch (Exception e) {
            throw new IOException("Identificador inválido.");
        }
    }

    private boolean validarSesion(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return false;
        }

        return true;
    }
}
