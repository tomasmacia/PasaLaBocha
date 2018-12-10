package pasalabocha

import java.time.LocalDateTime

class Reserva {
    BigDecimal precioFinal
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
      sena nullable: true
    }
}
