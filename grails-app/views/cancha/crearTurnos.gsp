<%@ page import="java.time.LocalDateTime" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cancha.label', default: 'Cancha')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <g:set var="now" value="${LocalDateTime.now().withSecond(0).withNano(0)}" />
        <h1> Crear un único turno</h1>
        <g:form name="crearTurno" action="generarTurno" controller="cancha" params="[id: params.id]">
            <fieldset class="form">
                <label>Horario: <input id="horario" type="datetime-local" name="horario" min="${now.toString()}"
                                       max="${now.plusWeeks(3).toString()}" required></label><br>
                <label>Duración del turno: <input type="number" name="duracion" required> minutos</label><br>
                <label>Precio de reserva: <input type="number" name="precio" required></label><br>
                <input type="submit" value="Generar">
            </fieldset>
        </g:form>
    </body>
</html>
