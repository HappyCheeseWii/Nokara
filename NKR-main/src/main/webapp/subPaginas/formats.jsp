<%--
  Created by IntelliJ IDEA.
  User: Dova
  Date: 08/10/2026
  Time: 11:31 a.m.
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>

<%
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>

<%!
    String nombreSistema = "NOKARA";
%>

<%
    List<Map<String,String>> formats =
        (List<Map<String,String>>) request.getAttribute("formats");
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formatos - <%=nombreSistema%></title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
    <link rel="icon" href="<%= request.getContextPath() %>/assets/NKR-P.jpg">
</head>

<body>
    <header class="principal">
        <div class="nkr">
            <h1><%=nombreSistema%></h1>
            <a href="../index.jsp" class="btn-regresar">Volver</a>
        </div>
    </header>

    <main class="contenido">
        <h2>Formatos de Policía Judicial de Colombia</h2>
        <p class="subtitulo">Selecciona un formato para descargar en formato Word o PDF</p>

        <%--Cargar lista de formatos--%>
        <div class="contenedor-formatos">
            <% if (formats != null && !formats.isEmpty()) { %>

                        <% for (Map<String, String> item : formats) { %>

                            <div class="tarjeta-formato-fila">

                                <div class="info-formato-texto">

                                    <strong>
                                        <%= item.get("codigo") %>
                                    </strong>

                                    -

                                    <%= item.get("nombre") %>

                                </div>

                                <div class="grupo-botones-descarga">

                                    <a
                                        href="<%= request.getContextPath() %>/assets/formats/<%= item.get("codigo") %>.docx"
                                        class="btn-descarga-item"
                                        download="<%= item.get("codigo") %>_<%= item.get("slug") %>.docx">
                                        Word
                                    </a>

                                    <a
                                        href="<%= request.getContextPath() %>/assets/formats/<%= item.get("codigo") %>.pdf"
                                        class="btn-descarga-item"
                                        download="<%= item.get("codigo") %>_<%= item.get("slug") %>.pdf">
                                        PDF
                                    </a>

                                </div>

                            </div>

                        <% } %>

                    <% } else { %>

                        <p>No se pudieron cargar los formatos.</p>

                    <% } %>
        </div>
    </main>
</body>

</html>
