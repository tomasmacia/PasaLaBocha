package pasalabocha

import java.time.Duration
import pasalabocha.login.User

class Club extends User{
    String nombre
    String email
    String ubicacion
    Integer nivelConfiabilidadNecesario
    Integer porcentajeSena
    // lo dejo pero recordar que dijeron que no era necesario incluir esta historia de usuario
    Duration tiempoLimiteCancelacionReserva
    Duration tiempoLimitePagoDeSena
    List<Cancha> canchas // Set
    Set<Cliente> clientesHabituales // intermediario
    Set<Descuento> descuentos

    static hasMany = [canchas: Cancha, clientesHabituales: Cliente, reservas: Reserva, descuentos: Descuento]

    static constraints = {
      nombre nullable: false, blank: false
      canchas minSize: 0
      email email: true
      // esto es porque en la vista aun no se pueden captar estos dos
      tiempoLimiteCancelacionReserva nullable: true
      tiempoLimitePagoDeSena nullable: true
    }

    static mapping = {
   }

    String toString(){
      nombre
    }
}
