
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:set var="nombreSistema" value="NOKARA"/>

<c:choose>
    <c:when test="${param.lang == 'en'}">
        <c:set var="idioma" value="en"/>
    </c:when>
    <c:otherwise>
        <c:set var="idioma" value="es"/>
    </c:otherwise>
</c:choose>

<fmt:setLocale value="${idioma}"/>
<fmt:setBundle basename="i18n.messages" var="mensajes"/>

<c:set var="mostrarResumen" value="true"/>

<!DOCTYPE html>
<html lang="${idioma}">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Formatos - <c:out value="${nombreSistema}"/></title>

    <c:url var="rutaCSS" value="/css/styles.css"/>
    <c:url var="rutaIcono" value="/assets/NKR-P.jpg"/>

    <link rel="stylesheet" href="${rutaCSS}">
    <link rel="icon" href="${rutaIcono}">
</head>

<body>

<header class="principal">
    <div class="nkr">

        <h1><c:out value="${nombreSistema}"/></h1>

        <c:url var="rutaVolver" value="/index.jsp"/>

        <a href="${rutaVolver}" class="btn-regresar">
            <fmt:message key="volver" bundle="${mensajes}"/>
        </a>

        <div class="selector-idioma">

            <c:url var="espanol" value="/FormatServlet">
                <c:param name="lang" value="es"/>
            </c:url>

            <c:url var="ingles" value="/FormatServlet">
                <c:param name="lang" value="en"/>
            </c:url>

            <a href="${espanol}">Español</a>
            |
            <a href="${ingles}">English</a>

        </div>

    </div>
</header>

<main class="contenido">

    <h2>
        <fmt:message key="titulo" bundle="${mensajes}"/>
    </h2>

    <p class="subtitulo">
        <fmt:message key="subtitulo" bundle="${mensajes}"/>
    </p>

    <c:if test="${mostrarResumen}">
        <p>
            <fmt:message key="totalFormatos" bundle="${mensajes}"/>:

            <fmt:formatNumber value="${totalFormatos}" type="number"/>
        </p>

        <p>
            <fmt:formatDate value="${fechaConsulta}" pattern="dd/MM/yyyy HH:mm"/>
        </p>
    </c:if>

    <div class="contenedor-formatos">

        <c:choose>

            <c:when test="${not empty formats}">

                <c:forEach var="item" items="${formats}">

                    <div class="tarjeta-formato-fila">

                        <div class="info-formato-texto">

                            <strong>
                                <c:out value="${item.codigo}"/>
                            </strong>

                            -

                            <c:out value="${item.nombre}"/>

                        </div>

                        <div class="grupo-botones-descarga">

                            <c:url var="archivoWord"
                                   value="/assets/formats/${item.codigo}.docx"/>

                            <c:url var="archivoPDF"
                                   value="/assets/formats/${item.codigo}.pdf"/>

                            <a href="${archivoWord}"
                               class="btn-descarga-item"
                               download="${item.codigo}_${item.slug}.docx">

                                <fmt:message key="word" bundle="${mensajes}"/>

                            </a>

                            <a href="${archivoPDF}"
                               class="btn-descarga-item"
                               download="${item.codigo}_${item.slug}.pdf">

                                <fmt:message key="pdf" bundle="${mensajes}"/>

                            </a>

                        </div>

                    </div>

                </c:forEach>

            </c:when>

            <c:otherwise>

                <p>
                    <fmt:message key="sinFormatos" bundle="${mensajes}"/>
                </p>

            </c:otherwise>

        </c:choose>

    </div>

    <c:remove var="mostrarResumen" scope="page"/>

</main>

</body>
</html>
