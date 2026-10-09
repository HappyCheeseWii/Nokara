<%--
  Created by IntelliJ IDEA.
  User: Dova
  Date: 01/10/2026
  Time: 11:36 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
    String nombreSistema = "NOKARA";

    public String obtenerMensaje() {
        return "Bienvenido al Gestor documental NOKARA";
    }
%>

<!doctype html>
<html lang="es">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title><%= nombreSistema%></title>
    <link rel="stylesheet" href="css/styles.css" />
    <link rel="icon" href="assets/NKR-P.jpg" />
</head>

<body>
    <header class="principal">
        <div class="nkr">
            <h1><%= nombreSistema%></h1>
        </div>

    </header>
    <main>
        <aside>
            <nav>
                <ul>
                    <li><a href="#prededetminado">Inicio</a></li>
                    <li><a href="subPaginas/casos.jsp">Casos</a></li>
                    <li><a href="subPaginas/formats.jsp">Formatos</a></li>
                    <li><a href="subPaginas/tools.jsp">Herramientas</a></li>

                    <li>
                        <div class="bandeja">
                            <a href="subPaginas/bandeja.html"><span>Bandeja</span></a>
                            <ul>
                                <li><a href="subPaginas/bandeja.html#enviados">Enviados</a></li>
                                <li><a href="subPaginas/bandeja.html#recividos">Recibidos</a></li>
                            </ul>
                        </div>
                        <div class="cerrar">
                            <a href="login.jsp"> Cerrar sesión</a>
                        </div>
                    </li>
                </ul>
            </nav>
        </aside>
        <div class="contenido">

            <p><%= obtenerMensaje() %></p>

            <h2>
                Bienvenido, <%= application.getAttribute("usuario")%>
            </h2>

        </div>
    </main>
    <footer>
        <p>
            <%= nombreSistema%>
        </p>
    </footer>

    <script src="js/script.js" defer></script>
</body>

</html>