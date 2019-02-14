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
            <th>Día de la semana</th>
            <th>Horario</th>
            <th>Cancha</th>
        </tr>
        <g:each in="${reservaPermanenteSet}" var="reservaPermanente">
          <tr>
              <td>${reservaPermanente.diaDeLaSemana}</td>
              <td>${reservaPermanente.horario}</td>
              <td><g:link controller="cancha" action="verMisTurnos" params="[id:reservaPermanente.cancha.id]">${reservaPermanente.cancha}</g:link></td>
          </tr>
        </g:each>
      </table>
    </body>
</html>
