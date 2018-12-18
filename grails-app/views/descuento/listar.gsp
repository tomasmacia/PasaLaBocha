<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'descuento.label', default: 'Descuento')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<h1>Descuentos en rango</h1>
<g:each in="${descuentosEnRango}" var="descuentoEnRango">
    <table>
        <tr>
            <td>Porcentaje</td>
            <td>Nivel confiabilidad</td>
            <td>Desde</td>
            <td>Hasta</td>
        </tr>

        <tr>
            <td>${descuentoEnRango.porcentaje}</td>
            <td>${descuentoEnRango.nivelConfiabilidadNecesario}</td>
            <td>${descuentoEnRango.fechaInicial}</td>
            <td>${descuentoEnRango.fechaFinal}</td>
        </tr>
    </table>
</g:each>

<h1>Descuentos por horas restantes</h1>
<g:each in="${descuentosPorHorasRestantes}" var="descuentoPorHorasRestantes">
    <table>
        <tr>
            <td>Porcentaje</td>
            <td>Nivel confiabilidad</td>
            <td>A partir de</td>
        </tr>

        <tr>
            <td>${descuentoPorHorasRestantes.porcentaje}</td>
            <td>${descuentoPorHorasRestantes.nivelConfiabilidadNecesario}</td>
            <td>${descuentoPorHorasRestantes.horasRestantes}</td>
        </tr>
    </table>
</g:each>

<h1>Descuentos por turno anterior</h1>
<g:each in="${descuentosPorTurnoAnterior}" var="descuentoPorTurnoAnterior">
    <table>
        <tr>
            <td>Porcentaje</td>
            <td>Nivel confiabilidad</td>
            <td>Se tienen en cuenta hasta</td>
        </tr>

        <tr>
            <td>${descuentoPorTurnoAnterior.porcentaje}</td>
            <td>${descuentoPorTurnoAnterior.nivelConfiabilidadNecesario}</td>
            <td>${descuentoPorTurnoAnterior.horasRestantes}</td>
        </tr>
    </table>
    ${descuentoPorTurnoAnterior}
</g:each>
</body>
</html>
