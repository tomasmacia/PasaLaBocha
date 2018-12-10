package pasalabocha

import java.time.Duration

class Club {
    String nombre
    String email
    String ubicacion
    Integer nivelConfiabilidadNecesario
    Integer porcentajeSena
    // lo dejo pero recordar que dijeron que no era necesario incluir esta historia de usuario
    Duration tiempoLimiteCancelacionReserva
    Duration tiempoLimitePagoDeSena
    List<Cancha> canchas

    static hasMany = [canchas: Cancha]

    static constraints = {
      nombre nullable: false, blank: false
      canchas minSize: 0
      email email: true
    }

    String toString(){
      nombre
    }
}
