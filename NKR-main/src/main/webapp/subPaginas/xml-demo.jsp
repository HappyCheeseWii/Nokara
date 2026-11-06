<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="x" uri="jakarta.tags.xml" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Demostración XML - NOKARA</title>
</head>
<body>

<h1>NOKARA - Lectura de formatos XML</h1>

<c:import url="/xml/formatos-demo.xml" var="archivoXML"/>

<x:parse xml="${archivoXML}" var="documento"/>

<h2>Formatos de prueba</h2>

<table border="1">
    <tr>
        <th>Código</th>
        <th>Nombre</th>
        <th>Categoría</th>
    </tr>

    <x:forEach select="$documento/formatos/formato" var="formato">
        <tr>
            <td>
                <x:out select="$formato/codigo"/>
            </td>
            <td>
                <x:out select="$formato/nombre"/>
            </td>
            <td>
                <x:out select="$formato/categoria"/>
            </td>
        </tr>
    </x:forEach>
</table>

</body>
</html>