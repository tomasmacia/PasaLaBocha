package pasalabocha

import pasalabocha.login.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
class HomeController {

    def index() {
      if (isLoggedIn()) {
         Set<Role> roles = authenticatedUser.getAuthorities()
         if (roles.contains(new Role(authority: 'ROLE_USUARIO'))){
           Cliente cliente = authenticatedUser
           println(cliente.nombre)
         } else if (roles.contains(new Role(authority: 'ROLE_CLUB'))){
           Club club = authenticatedUser
           println(club)
         }
      }
    }
}
