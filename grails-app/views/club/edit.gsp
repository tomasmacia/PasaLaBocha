<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="pasalabocha" />
        <g:set var="entityName" value="${message(code: 'club.label', default: 'Club')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-club" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="edit-club" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
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
            <g:form resource="${this.club}" method="PUT">
                <g:hiddenField name="version" value="${this.club?.version}" />
                <fieldset class="form">
                    <f:field bean="club" property="nombre"/>
                    <f:field bean="club" property="email"/>
                    <f:field bean="club" property="ubicacion"/>
                    <f:field bean="club" property="nivelConfiabilidadNecesario"/>
                    <f:field bean="club" property="porcentajeSena"/>
                    <label>Tiempo limite para cancelacion de reserva: <input type="number" name="tiempoCancelacion" value="${this.club.tiempoLimiteCancelacionReserva.toMinutes()}" required> minutos</label><br>
                    <label>Tiempo limite para pago de seña: <input type="number" name="tiempoSena" value="${this.club.tiempoLimitePagoDeSena.toMinutes()}" required> minutos</label><br>
                    <f:field bean="club" property="canchas"/>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="update" class="save" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
