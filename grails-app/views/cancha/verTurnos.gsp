<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="pasalabocha" />
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
            <td>Reservado</td>
            </g:if>
            <g:else>
            <td>Disponible: <g:link action="reservarTurno" params="[turnoId:turno.id]">Reservar</g:link></td>
            </g:else>
          </tr>
        </g:each>
      </table>
    </body>
</html>
