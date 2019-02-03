<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="pasalabocha" />
        <g:set var="entityName" value="${message(code: 'club.label', default: 'Club')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
      <table style="width:100%">
        <tr>
          <th>Turno</th>
          <th>Precio total</th>
          <th>Seña</th>
          <th>Precio restante</th>
          <th>Asistencia</th>
        </tr>
        <g:each in="${reservaList}" var="reserva">
          <tr>
            <td><g:link controller="turno" action="show" params="[id:reserva.turno.id]">${reserva.turno}</g:link></td>
            <td>${reserva.precioFinal}</td>
            <td>
              <g:if test="${reserva.sena != null}">
                <g:link controller="sena" action="show" params="[id:reserva.sena.id]">${reserva.sena}</g:link>
              </g:if>
            </td>
            <td>${reserva.precioFinal - reserva.sena?.monto ?: 0}</td>
            <td>
                <g:link controller="reserva" action="asistenciaCumplida" params="[id:reserva.id]">Cumplida</g:link>
                <g:link controller="reserva" action="asistenciaIncumplida" params="[id: reserva.id]">Incumplida</g:link>
            </td>
          </tr>
        </g:each>
      </table>
    </body>
</html>
