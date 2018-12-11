<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'club.label', default: 'Club')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
      <table style="width:100%">
        <tr>
          <th>Username</th>
          <th>Nombre</th>
          <th>Apellido</th>
          <th>Email</th>
          <th>Apodo</th>
          <th>Eliminar</th>
        </tr>
        <g:each in="${clienteSet}" var="clienteHabitual">
          <tr>
            <td>${clienteHabitual.username}</td>
            <td>${clienteHabitual.nombre}</td>
            <td>${clienteHabitual.apellido}</td>
            <td>${clienteHabitual.email}</td>
            <td>${clienteHabitual.apodo}</td>
            <td><g:link action="eliminarClienteHabitual" id="${id}" params="[username:clienteHabitual.username]">Eliminar</g:link></td>
          </tr>
        </g:each>
      </table>
      <g:link action="agregarClienteHabitualForm" id="${id}">Agregar cliente habitual</g:link>
    </body>
</html>
