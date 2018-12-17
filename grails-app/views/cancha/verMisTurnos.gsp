<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cancha.label', default: 'Cancha')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
      <table style="width:100%">
        <tr>
          <th>Fecha</th>
          <th>Horario</th>
          <th>Duración[minutos]</th>
          <th>Precio</th>
          <th>Reserva</th>
        </tr>
        <g:each in="${turnoList}" var="turno">
          <tr>
            <td>${turno.fechaHorario.toLocalDate()}</td>
            <td>${turno.fechaHorario.toLocalTime()}</td>
            <td>${turno.duracion.toMinutes()}</td>
            <td>${turno.precioBase}</td>
            <g:if test="${turno.reserva != null}">
            <td>Reservado: <g:link controller="reserva" action="show" params="[id:turno.reserva.id]">${turno.reserva}</g:link></td>
            </g:if>
            <g:else>
            <td>Disponible</td>
            </g:else>
          </tr>
        </g:each>
      </table>
      <g:link action="crearTurnos" id="id">Crear turnos</g:link>
    </body>
</html>
