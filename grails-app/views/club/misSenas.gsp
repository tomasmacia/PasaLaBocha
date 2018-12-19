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
          <th>Cliente</th>
          <th>Precio total</th>
          <th>Precio seña</th>
          <th>Precio restante</th>
          <th>Pagar seña</th>
        </tr>
        <g:each in="${reservaSet}" var="reserva">
          <g:if test="${reserva.sena != null}">
            <tr>
              <td><g:link controller="turno" action="show" params="[id:reserva.turno.id]">${reserva.turno}</g:link></td>
              <td><g:link controller="cliente" action="show" params="[id:reserva.cliente.id]">${reserva.cliente}</g:link></td>
              <td>${reserva.precioFinal}</td>
              <td>${reserva.sena.monto}</td>
              <td>${reserva.precioFinal - reserva.sena.monto}</td>
              <td>
                <g:if test="${reserva.sena.pagada == true}">
                  Pagada
                </g:if>
                <g:else>
                  <g:link controller="reserva" action="pagarSena" params="[reservaId:reserva.id]">Pagar seña</g:link>
                </g:else>
              </td>

            </tr>
          </g:if>
        </g:each>
      </table>
    </body>
</html>
