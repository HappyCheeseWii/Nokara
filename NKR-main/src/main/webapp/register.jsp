<%--
  Created by IntelliJ IDEA.
  User: Dova
  Date: 05/10/2026
  Time: 08:34 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
    String nombreSistema = "NOKARA";
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= nombreSistema%>-Registro</title>
    <link rel="stylesheet" href="css/styles.css">
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
            <div class="form" id="registro">
                <h2>Registrate</h2>
                <form action="RegisterServlet" method="post">
                    <!--correo-->
                    <label>Correo</label>
                    <input type="email" id="emailIdR" name="email">

                    <!--nombre d usuario-->
                    <label>Nombre de usuario</label>
                    <input type="text" id="usernameIdR" name="username">

                    <!--contraseña-->
                    <label>Contraseña</label>
                    <input type="password" id="passwordIdR" name="password">

                    <!--btn-->
                    <button type="submit">Registrar</button>

                    <p id="mRegistro">
                        <% if (request.getAttribute("mensaje_error") != null) { %>
                            <span class="alerta-error"> <%= request.getAttribute("mensaje_error")%> </span>
                        <% } %>
                    </p>

                    <p class="inferior">
                        ¿Ya tienes una cuenta?
                        <a href="login.jsp">Iniciar sesión</a>
                    </p>
                </form>
            </div>
        </section>
    </main>

    <script src="js/script.js" defer></script>
</body>

</html>