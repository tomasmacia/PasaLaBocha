<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="pasalabocha" />
        <g:set var="entityName" value="${message(code: 'club.label', default: 'Club')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
      <g:form controller="club" method="POST" params="[id:id]">
        <input list="clientes" name="username">
        <datalist id="clientes">
          <g:each in="${clienteList}" var="cliente">
            <option value="${cliente.username}">
          </g:each>
        </datalist>
        <g:actionSubmit value="Agregar" action="agregarClienteHabitual"/>
      </g:form>
    </body>
</html>
