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
          <th>Duración</th>
          <th>Precio</th>
          <th>Reserva</th>
        </tr>
        <g:each in="${turnoList}" var="turno">
          <tr>
            <td>${turno.fecha}</td>
            <td>${turno.horario}</td>
            <td>${turno.duracion}</td>
            <td>${turno.precioBase}</td>
            <g:if test="${turno.reserva != null}">
            <td>${turno.reserva}</td>
            </g:if>
            <g:else>
            <td>Disponible: <g:link action="reservarTurno" turno="${turno}">Reservar</g:link></td>
            </g:else>
          </tr>
        </g:each>
      </table>
    </body>
</html>
