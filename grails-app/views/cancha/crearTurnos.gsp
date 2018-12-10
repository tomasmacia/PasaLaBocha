<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cancha.label', default: 'Cancha')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
      <h1> ${params}</h1>
        <g:form name="crearTurnos" action="generarTurnos" controller="cancha" params="[id: params.id]">
                <fieldset class="form">
                  <label>Horario de inicio: <input id="time" type="time" name="horarioInicio"></label><br>
                  <label>Horario de fin: <input id="time" type="time" name="horarioFin"></label><br>
                  <label>Duracion de los turnos: <input type="number" name="largoTurno"> minutos</label><br>
                  <label>Precio de reserva: <input type="number" name="precio"></label><br>
                   <input type="submit" value="Generar">
                </fieldset>
        </g:form>
    </body>
</html>
