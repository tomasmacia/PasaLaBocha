<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="pasalabocha" />
        <g:set var="entityName" value="${message(code: 'cancha.label', default: 'Cancha')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-cancha" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-cancha" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
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
            <h1> ${params}<h1>
            <g:form resource="${this.cancha}" method="POST" params="[club: params.club.id]">
                <fieldset class="form">
                    <f:field bean="cancha" property="tipoSuelo"/>
                    <f:field bean="cancha" property="numeroDeCancha"/>
                    <f:field bean="cancha" property="cantidadJugadores"/>
                    <f:field bean="cancha" property="dimensiones"/>
                    <f:field bean="cancha" property="poseeIluminacion"/>
                    <f:display bean="cancha" property="club"/>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
