<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="pasalabocha" />
        <g:set var="entityName" value="${message(code: 'club.label', default: 'Club')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-club" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="create-club" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.club}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.club}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.club}" method="POST">
                <fieldset class="form">
                    <f:field bean="club" property="username"/>
                    <f:field bean="club" property="password"/>
                    <f:field bean="club" property="nombre"/>
                    <f:field bean="club" property="ubicacion"/>
                    <f:field bean="club" property="email"/>
                    <f:field bean="club" property="nivelConfiabilidadNecesario"/>
                    <f:field bean="club" property="porcentajeSena"/>
                    <label>Tiempo limite para cancelacion de reserva: <input type="number" name="tiempoCancelacion" required> minutos</label><br>
                    <label>Tiempo limite para pago de seña: <input type="number" name="tiempoSena" required> minutos</label><br>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
