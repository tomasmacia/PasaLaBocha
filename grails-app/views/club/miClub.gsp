<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'club.label', default: 'Club')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-club" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="show-club" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <div>
            <label>Username: <f:display bean="club" property="username"/></label><br>
            <label>Nombre: <f:display bean="club" property="nombre"/><br>
            <label>Email: <f:display bean="club" property="email"/><br>
            <label>Ubicacion: <f:display bean="club" property="ubicacion"/><br>
            <label>Nivel de confiabilidad necesario: <f:display bean="club" property="nivelConfiabilidadNecesario"/><br>
            <label>Porcentaje de seña: <f:display bean="club" property="porcentajeSena"/><br>
            <label>Tiempo limite de cancelación de reserva: <f:display bean="club" property="tiempoLimiteCancelacionReserva"/><br>
            <label>Tiempo limite para el pago de la seña: <f:display bean="club" property="tiempoLimitePagoDeSena"/><br>
            <g:link action="verClientesHabituales" id="${this.club.id}">Ver mis clientes habituales</g:link><br>
            <g:link action="misCanchas">Ver mis canchas</g:link><br>
            <g:link action="misReservas">Ver mis reservas</g:link><br>
            <g:link action="misSenas">Ver mis señas</g:link><br>
            </div>
            <g:form resource="${this.club}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.club}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
