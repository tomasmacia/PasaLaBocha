package pasalabocha

import java.time.LocalDateTime
import java.time.Duration

class Reserva {
    BigDecimal precioFinal
    // lo dejo pero recordar que dijeron que no era necesario incluir esta historia de usuario
    LocalDateTime plazoLimiteCancelacion
    boolean concretada = false
    // cual seria la diferencia con el id que genera grails?
    // responder a franco
    Integer nroReserva
    Turno turno

    static hasOne = [sena: Sena]

    static belongsTo = [turno: Turno]

    static constraints = {
      //nullable porque todavia no esta implementado que se complete
      nroReserva nullable: true
    }

    public Reserva(Turno turno, BigDecimal precio, LocalDateTime plazoLimiteCancelacion, Duration  tiempoLimitePagoDeSena){
      this.turno = turno
      this.precioFinal = precio
      this.plazoLimiteCancelacion = plazoLimiteCancelacion
      this.sena = new Sena(this, tiempoLimitePagoDeSena, precio * this.turno.cancha.club.porcentajeSena / 100)
      this.sena.save(failOnError: true)
    }
}
