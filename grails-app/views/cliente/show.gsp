<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="pasalabocha" />
        <g:set var="entityName" value="${message(code: 'cliente.label', default: 'Cliente')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-cliente" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="show-cliente" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <label>Username: <f:display bean="cliente" property="username"/></label><br>
            <label>Nombre: <f:display bean="cliente" property="nombre"/><br>
            <label>Apellido: <f:display bean="cliente" property="apellido"/><br>
            <label>Apodo: <f:display bean="cliente" property="apodo"/><br>
            <label>Email: <f:display bean="cliente" property="email"/><br>
            <label>Nivel de confiabilidad: <f:display bean="cliente" property="confiabilidad"/><br>
            <g:link controller="cliente" action="misReservas">Ver mis reservas</g:link><br>
            <g:form resource="${this.cliente}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.cliente}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
