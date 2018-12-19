<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="pasalabocha" />
        <g:set var="entityName" value="${message(code: 'cancha.label', default: 'Cancha')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-cancha" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-cancha" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.cancha}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.cancha}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.cancha}" method="PUT">
                <g:hiddenField name="version" value="${this.cancha?.version}" />
                <fieldset class="form">
                  <h2>${this.cancha}</h2>
                  <label>Numero de cancha: <f:display bean="cancha" property="numeroDeCancha"/></label><br>
                  <label>Club: <f:display bean="cancha" property="club" /></label><br>
                  <f:field bean="cancha" property="tipoSuelo"/>
                  <f:field bean="cancha" property="cantidadJugadores"/>
                  <f:field bean="cancha" property="dimensiones"/>
                  <f:field bean="cancha" property="poseeIluminacion"/>
                </fieldset>
                <fieldset class="turnos">
                  <f:field bean="cancha" property="turnos"/>
                  <g:link action="crearTurnos" id="${this.cancha.id}">Crear turnos</g:link>
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
