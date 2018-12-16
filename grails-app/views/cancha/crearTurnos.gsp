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
        <h1> Crear turnos</h1>
        <input type="hidden" value="1" id="cantTurnos" name="cantTurnos" />
        <g:form name="crearTurnos" action="generarTurnos" controller="cancha" params="[id: params.id]">
            <fieldset class="form">
                <div id="turnos-container">
                    <label>Horario: <input type="datetime-local" name="horario-1" min="${now.toString()}"
                                           max="${now.plusWeeks(3).toString()}" required></label>
                    <input type="button" id="agregar-turno" onclick="agregarTurno();" value="Agregar otro turno" /><br>
                </div>
                <label id="duracion">Duración del turno: <input type="number" name="duracion" required> minutos</label><br>
                <label>Precio de reserva: <input type="number" name="precio" required></label><br>
                <input type="submit" value="Generar">
            </fieldset>
        </g:form>

    <g:javascript>
        function agregarTurno() {
            var elem = document.getElementById("cantTurnos");
            elem.value = 1 + parseInt(elem.value);
            var cantTurnos = elem.value;
            document.getElementById('turnos-container').innerHTML += '<label>Horario: <input type="datetime-local" name="horario-'+ cantTurnos + '" min="${now.toString()}" max="${now.plusWeeks(3).toString()}" required></label><br>';
            document.getElementById('duracion').innerHTML = 'Duración de los turnos: <input type="number" name="duracion" required> minutos';
        }
    </g:javascript>
    </body>
</html>
