package pasalabocha

import pasalabocha.login.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
class HomeController {

    LoginService loginService

    def index() {
    }

    def createUser(){ }

    @Secured(['ROLE_CLUB', 'ROLE_ADMIN', 'ROLE_CLIENTE'])
    def miCuenta(){
      if (loginService.esCliente(loggedIn, authenticatedUser)) {
        redirect(controller: "cliente", action:"miCliente")
      } else if (loginService.esClub(loggedIn, authenticatedUser)){
        redirect(controller: "club", action:"miClub")
      } else{
        redirect(action:"index")
      }
    }
}
