<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="pasalabocha" />
        <g:set var="entityName" value="${message(code: 'cancha.label', default: 'Cancha')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-cancha" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" controller="club" action="misCanchas"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="show-cancha" class="content scaffold-show" role="main">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <label>Numero de cancha: <f:display bean="cancha" property="numeroDeCancha"/></label><br/>
            <label>Tipo de suelo: <f:display bean="cancha" property="tipoSuelo"/></label><br/>
            <label>Cantidad de jugadores: <f:display bean="cancha" property="cantidadJugadores"/></label><br/>
            <label>Dimensiones: </label><br/><f:display bean="cancha" property="dimensiones"/><br/>
            <label>Tiene iluminación: <f:display bean="cancha" property="poseeIluminacion"/></label><br/>
            <g:link action="verMisTurnos" params="[canchaId:this.cancha.id]">Ver turnos</g:link>
            <g:form resource="${this.cancha}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.cancha}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
