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
            <td>Cancha</td>
            <td>Dimensiones</td>
            <td>Tipo de suelo</td>
            <td>Cantidad de jugadores</td>
            <td>Iluminación</td>
            <td>Ver turnos</td>
            <g:if test="${esHabitual}">
            <td>Reserva permanente</td>
            </g:if>
         </tr>
        <g:each in="${canchaSet}" var="cancha">
          <tr>
            <td>${cancha}</td>
            <td>Ancho: ${cancha.dimensiones.ancho}, Largo: ${cancha.dimensiones.largo}</td>
            <td>${cancha.tipoSuelo}</td>
            <td>${cancha.cantidadJugadores}</td>
            <g:if test="${cancha.poseeIluminacion}">
            <td>Posee</td>
            </g:if>
            <g:else>
            <td>No posee</td>
            </g:else>
            <td><g:link controller="cancha" action="verTurnos" params="[id:cancha.id]">Ver turnos</g:link></td>
            <g:if test="${esHabitual}">
            <td><g:link controller="reservaPermanente" action="create" params="[id:cancha.id]">Reservar permanentemente</g:link></td>
            </g:if>
          </tr>
        </g:each>
      </table>
    </body>
</html>
