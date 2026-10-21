<%--
  Created by IntelliJ IDEA.
  User: Dova
  Date: 05/10/2026
  Time: 08:34 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.net.URLDecoder, java.nio.charset.StandardCharsets" %>

<%!
    String nombreSistema = "NOKARA";
%>

<%
    String correoRecordado = "";

    if (request.getCookies() != null) {
        for (jakarta.servlet.http.Cookie c : request.getCookies()) {
            if ("correoRecordado".equals(c.getName())) {
                correoRecordado = URLDecoder.decode(
                        c.getValue(), StandardCharsets.UTF_8);
                break;
            }
        }
    }
%>

<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= nombreSistema%>-login</title>
    <link rel="stylesheet" href="css/styles.css" />
    <link rel="icon" href="assets/NKR-P.jpg" />
</head>

<body class="lr">
    <header>
        <div class="nkr">
            <h1><%= nombreSistema%></h1>
        </div>
    </header>
    <main class="ctn">
        <section class="img">
            <figure>
                <img src="assets/NKR-P.jpg" alt="<%= nombreSistema%> Logo">
            </figure>
        </section>
        <section class="cnt-form">

            <div class="form" id="login">
                <h2>Inicio de sesión</h2>
                <form action="LoginServlet" method="post" >

                    <%-- Correo --%>
                    <label for="correoIdL">Correo</label>
                    <input type="email" id="correoIdL" name="email"
                           value="<%= correoRecordado %>">

                    <% if (request.getAttribute("errorEmail") != null) { %>
                        <span class="alerta-error">
                            <%= request.getAttribute("errorEmail") %>
                        </span>
                    <% } %>

                    <%-- Contraseña --%>
                    <label for="passwordIdL">Contraseña</label>
                    <input type="password" id="passwordIdL" name="password">

                    <% if (request.getAttribute("errorPassword") != null) { %>
                        <span class="alerta-error">
                            <%= request.getAttribute("errorPassword") %>
                        </span>
                    <% } %>


                    <%--btn--%>
                    <button type="submit">Iniciar sesión</button>

                    <p class="inferior">
                        ¿Aún no tienes una cuenta?
                        <a href="register.jsp">Registrate</a>
                    </p>
                </form>
            </div>
        </section>

    </main>
    <footer>
    </footer>

    <script src="js/script.js" defer></script>
</body>

</html>