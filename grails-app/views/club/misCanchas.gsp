<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'club.label', default: 'Club')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
      <table style="width:100%">
        <g:each in="${canchaList}" var="cancha">
          <tr>
            <td><g:link controller="cancha" action="show" params="[id:cancha.id]">${cancha}</g:link></td>
            <td><g:link controller="cancha" action="verMisTurnos" params="[canchaId:cancha.id]">Ver turnos</g:link></td>
            <td><g:link controller="cancha" action="edit" params="[id:cancha.id]">editar</g:link></td>
            <td><g:link controller="cancha" action="delete" params="[id:cancha.id]">eliminar</g:link></td>
          </tr>
        </g:each>
      </table>
      <g:link controller="cancha" action="create">Agregar cancha</g:link>
    </body>
</html>
