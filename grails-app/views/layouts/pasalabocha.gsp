<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>

    <meta name="description" content="Encontra tu cancha en PasaLaBocha.">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />



    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.teal-amber.min.css">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>


    <g:layoutHead/>
</head>
<body>

<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header">

    <div class="mdl-layout__header mdl-layout__header--waterfall">
        <div class="mdl-layout__header-row">
            <span class="mdl-layout-title">
                <asset:image src="pasalabocha.png" alt="PasaLaBocha" style="width:56px; height:56px;"/>
                PasaLaBocha
            </span>
            <!-- Add spacer, to align navigation to the right in desktop -->
            <div class="mdl-layout-spacer"></div>
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--expandable mdl-textfield--floating-label mdl-textfield--align-right mdl-textfield--full-width">
                <label class="mdl-button mdl-js-button mdl-button--icon" for="search-field">
                    <i class="material-icons">search</i>
                </label>
                <div class="mdl-textfield__expandable-holder">
                    <input class="mdl-textfield__input" type="text" id="search-field">
                </div>
            </div>
            <!-- Navigation -->
            %{--<div class="android-navigation-container">--}%
                <nav class="mdl-navigation">
                    <sec:ifLoggedIn>
                      <a class="mdl-navigation__link mdl-typography--text-uppercase" href="http://localhost:8080/home/miCuenta"><sec:username/></a>
                      <a class="mdl-navigation__link mdl-typography--text-uppercase" href="http://localhost:8080/logout">Cerrar sesión</a>
                    </sec:ifLoggedIn>
                    <sec:ifNotLoggedIn>
                    <a class="mdl-navigation__link mdl-typography--text-uppercase" href="http://localhost:8080/login/auth">Iniciar sesión</a>
                    <a class="mdl-navigation__link mdl-typography--text-uppercase" href="http://localhost:8080/home/createUser">Crear cuenta</a>
                    </sec:ifNotLoggedIn>
                </nav>
            %{--</div>--}%
        </div>
    </div>

    <div class="mdl-layout__drawer">
        <span class="mdl-layout-title">
            <asset:image src="pasalabocha.png" alt="PasaLaBocha" style="width:128px; height:128px;"/>
        </span>
        <nav class="mdl-navigation">
            <a class="mdl-navigation__link" href="">Phones</a>
            <a class="mdl-navigation__link" href="">Tablets</a>
            <a class="mdl-navigation__link" href="">Wear</a>

        </nav>
    </div>

    <div class="mdl-layout__content">
        <g:layoutBody/>
    </div>

    <footer class = "mdl-mini-footer">
        <div class = "mdl-mini-footer__left-section">
            <div class = "mdl-logo">
                © 2018 PasaLaBocha
            </div>
            <ul class = "mdl-mini-footer__link-list">
                <li><a href = "#">Contacto</a></li>
            </ul>
        </div>

        <div class = "mdl-mini-footer__right-section">
            <button class = "mdl-mini-footer__social-btn">1</button>
            <button class = "mdl-mini-footer__social-btn">2</button>
            <button class = "mdl-mini-footer__social-btn">3</button>
        </div>

    </footer>
</div>





</body>
</html>
