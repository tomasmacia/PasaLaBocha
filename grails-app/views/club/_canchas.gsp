<div>
    <h2><%= canchaTagLib.canchasDisonibles(canchas: canchas)%></h2>

    <g:each in="${canchas}" var="cancha">
        <h3>Cancha nro ${cancha.numeroDeCancha}</h3>
        <p>Ancho:${cancha.dimensiones.ancho} Largo: ${cancha.dimensiones.largo}</p>
        <p>Tipo de Suelo: ${cancha.tipoSuelo}</p>

        <g:link controller="cancha" action="show" params="[canchaId: cancha.id.toString()]">Ver más información</g:link>

    </g:each>
</div>