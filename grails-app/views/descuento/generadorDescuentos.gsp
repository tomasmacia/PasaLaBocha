<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="pasalabocha" />
    <g:set var="entityName" value="${message(code: 'descuento.label', default: 'Descuento')}" />
    <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<h1>Generador de descuentos en rango: <g:link controller="descuentoEnRango" action="create">Ir al form</g:link> </h1>
<h1>Generador de descuentos por cercania horaria: <g:link controller="descuentoPorHorasRestantes" action="create">Ir al form</g:link> </h1>
<h1>Generador de descuentos por turnos cercanos: <g:link controller="descuentoPorTurnoAnterior" action="create">Ir al form</g:link> </h1>

<h2><g:link controller="descuento" action="listar">Ver descuentos activos</g:link></h2>
</body>
</html>
