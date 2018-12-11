package pasalabocha

import pasalabocha.login.User

class Cliente extends User{
    String nombre
    String apellido
    String email
    String apodo
    Integer nivelConfiabilidad = 0

    static hasMany = [reservas: Reserva]

    static constraints = {
      nombre nullable: false, blank: false
      apellido nullable: false, blank: false
      email email: true
      apodo nullable: true, blank: true
    }
}
