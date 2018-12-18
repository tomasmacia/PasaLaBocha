package pasalabocha.login

class LoginService{

  boolean esCliente(boolean loggedIn, def authenticatedUser){
    if (loggedIn) {
       Set<Role> roles = authenticatedUser.getAuthorities()
       return roles.contains(new Role(authority: 'ROLE_CLIENTE'))
    }
    false
  }

  boolean esClub(boolean loggedIn, def authenticatedUser){
    if (loggedIn) {
       Set<Role> roles = authenticatedUser.getAuthorities()
       return roles.contains(new Role(authority: 'ROLE_CLUB'))
    }
    false
  }

  boolean esAdmin(boolean loggedIn, def authenticatedUser){
    if (loggedIn) {
       Set<Role> roles = authenticatedUser.getAuthorities()
       return roles.contains(new Role(authority: 'ROLE_ADMIN'))
    }
    false
  }
}
