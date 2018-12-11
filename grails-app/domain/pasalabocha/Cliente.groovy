package pasalabocha

import pasalabocha.login.User

class Cliente extends User{
    String nombre
    String apellido
    String email
    String apodo
    Integer nivelConfiabilidad

    static constraints = {
    }
}
