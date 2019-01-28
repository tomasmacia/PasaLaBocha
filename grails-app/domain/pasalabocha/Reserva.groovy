package pasalabocha

import java.time.LocalDateTime
import java.time.Duration

class Reserva {
    BigDecimal precioFinal
    // lo dejo pero recordar que dijeron que no era necesario incluir esta historia de usuario
    LocalDateTime plazoLimiteCancelacion
    // cual seria la diferencia con el id que genera grails?
    // responder a franco
    Integer nroReserva
    Turno turno
    //intente hacer un one-to-many unidirecional pero el addToReservas no actualizaba la join table
    Club club // eliminar


    static hasOne = [sena: Sena]

    static belongsTo = [turno: Turno, cliente: Cliente]

    static constraints = {
      //nullable porque todavia no esta implementado que se complete
      nroReserva nullable: true
      sena nullable: true
    }

    public Reserva(Turno turno, Cliente cliente, BigDecimal precio, LocalDateTime plazoLimiteCancelacion){
      this.turno = turno
      this.club = turno.cancha.club
      this.cliente = cliente
      this.precioFinal = precio
      this.plazoLimiteCancelacion = plazoLimiteCancelacion
      Integer nivelConfiabilidadNecesario = turno.cancha.club.nivelConfiabilidadNecesario
      Set<Cliente> clientesHabituales = turno.cancha.club.clientesHabituales
      if (!cliente.esConfiable(nivelConfiabilidadNecesario) && !clientesHabituales.contains(cliente)){
        Duration tiempoLimitePagoDeSena = turno.cancha.club.tiempoLimitePagoDeSena
        this.sena = new Sena(this, tiempoLimitePagoDeSena, precio * this.turno.cancha.club.porcentajeSena / 100)
        this.sena.save(failOnError: true)
      }
    }

    String toString(){
//        Reserva.withCriteria {
//            turno {
//                cancha {
//                    eq("club", club)
//                }
//            }
//        }
     "${turno} por ${cliente}"
    }
}
