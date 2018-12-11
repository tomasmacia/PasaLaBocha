package pasalabocha

import pasalabocha.login.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
class HomeController {

    LoginService loginService

    def index() {
      if (loginService.esCliente(loggedIn, authenticatedUser)) {
           Cliente cliente = authenticatedUser
           println(cliente.nombre)
           println(authenticatedUser.nombre)
      } else if (loginService.esClub(loggedIn, authenticatedUser)){
           Club club = authenticatedUser
           println(club)
      }
    }
}
