<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="pasalabocha" />
        <title>${club.nombre} en PasaLaBocha</title>

        <asset:stylesheet src="club.css" />
    </head>
    <body>

        <div class="club-ribbon">
            <h1><%= club.nombre %></h1>
            <h3><%= club.ubicacion %></h3>

            <p>Contacto: <%= club.email %></p>
        </div>

        <g:render template="canchas" model="[canchas: club.canchas]"/>

    </body>
</html>
