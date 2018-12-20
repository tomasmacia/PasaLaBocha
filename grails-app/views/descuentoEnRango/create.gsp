<%@ page import="java.time.LocalDateTime" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="pasalabocha" />
        <g:set var="entityName" value="${message(code: 'descuentoEnRango.label', default: 'DescuentoEnRango')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-descuentoEnRango" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" controller="descuento" action="listar">Descuentos activos</g:link></li>
            </ul>
        </div>
        <div id="create-descuentoEnRango" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.descuentoEnRango}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.descuentoEnRango}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:set var="now" value="${LocalDateTime.now().withSecond(0).withNano(0)}" />
            <g:form action="aplicarYGuardar" name="createForm" method="POST">
                <fieldset class="form">
                    <label>Porcentaje de descuento: <input type="number" name="porcentaje" min="1" max="100" required></label><br>
                    <label>Nivel de confiabilidad necesario: <input type="number" name="nivelConfiabilidadNecesario" min="0" required></label><br>
                    <label>Fecha inicial: <input type="datetime-local" name="fechaInicial" min="${now.toString()}"
                                           max="${now.plusWeeks(3).toString()}" required></label><br>
                    <label>Fecha final: <input type="datetime-local" name="fechaFinal" min="${now.toString()}"
                                           max="${now.plusWeeks(3).toString()}" required></label><br>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="aplicarYGuardar" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
