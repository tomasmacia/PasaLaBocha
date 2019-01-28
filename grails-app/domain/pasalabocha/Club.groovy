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
    Set<Cancha> canchas
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

    void agregarCancha(Cancha cancha){
        if (canchas){
            def repetidos = canchas.findAll {it.numeroDeCancha == cancha.numeroDeCancha}
            if (repetidos){
                throw new Exception()
            }
        }
        this.addToCanchas(cancha)
    }

    def agregarClienteHabitual(Cliente cliente){
        if (clientesHabituales){
            if (cliente in clientesHabituales){
                throw new Exception()
            }
        }
        this.addToClientesHabituales(cliente)
    }

    def eliminarClienteHabitual(Cliente cliente){
        if (clientesHabituales){
            if (!(cliente in clientesHabituales)){
                throw new Exception()
            }
        }
        this.removeFromClientesHabituales(cliente)
    }
}
