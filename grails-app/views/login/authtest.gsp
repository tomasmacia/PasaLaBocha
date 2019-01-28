<html>
<head>
    <meta name="layout" content="pasalabocha"/>
    <title><g:message code='springSecurity.login.title'/></title>
    <style type="text/css" media="screen">
    #login {
        align-items: center;
        justify-content: center;
    }

    #login .login-content {
        position: absolute;
        padding: 24px;
        flex: none;
    }


    #login #remember_me_holder label {
        float: none;
        margin-left: 0;
        text-align: left;
        width: 200px
    }

    #login .login_message {
        padding: 6px 25px 20px 25px;
        color: #c33;
    }

    #login .inner .text_ {
        width: 120px;
    }

    #login .inner .chk {
        height: 12px;
    }
    </style>
</head>

<body>
<div id="login">
    <div class="login-content">
        <div class="mdl-card mdl-shadow--6dp">
            <div class="mdl-card__title mdl-color--primary mdl-color-text--white">
                <h2 class="mdl-card__title-text">Iniciar sesión</h2>
            </div>

            <g:if test='${flash.message}'>
            <div class="login_message">${flash.message}</div>
            </g:if>

            <div class="mdl-card__supporting-text">
                <form action="${postUrl ?: '/login/authenticate'}" method="POST" id="loginForm" class="cssform" autocomplete="off">
                    <div class="mdl-textfield mdl-js-textfield">
                        <input class="mdl-textfield__input" type="text" name="${usernameParameter ?: 'username'}" id="username" />
                        <label class="mdl-textfield__label" for="username"><g:message code='springSecurity.login.username.label'/></label>
                    </div>
                    <div class="mdl-textfield mdl-js-textfield">
                        <input class="mdl-textfield__input" type="password" name="${passwordParameter ?: 'password'}" id="userpass" />
                        <label class="mdl-textfield__label" for="userpass">Password</label>
                    </div>
                    <div class="mdl-textfield mdl-js-check">
                        <input type="checkbox" class="mdl-checkbox__input" name="${rememberMeParameter ?: 'remember-me'}" id="remember_me" <g:if test='${hasCookie}'>checked="checked"</g:if>/>
                        <label for="remember_me" class="mdl-checkbox__label"><g:message code='springSecurity.login.remember.me.label'/></label>
                    </div>
                </form>
            </div>
            <div class="mdl-card__actions mdl-card--border">
                <button type="submit" form="loginForm" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored  mdl-js-ripple-effect">Log in</button>
            </div>
        </div>
    </div>
</div>
<script>
    (function() {
        document.forms['loginForm'].elements['${usernameParameter ?: 'username'}'].focus();
    })();
</script>
</body>
</html>
