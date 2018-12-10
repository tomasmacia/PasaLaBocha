package pasalabocha

import java.time.LocalDateTime

class Reserva {
    BigDecimal precioFinal
    LocalDateTime plazoLimiteCancelacion
    boolean concretada = false
    Integer nroReserva
    Turno turno

    static hasOne = [sena: Sena]

    static belongsTo = [turno: Turno]

    static constraints = {
      //nullable porque todavia no esta implementado que se complete
      precioFinal nullable: true
      plazoLimiteCancelacion nullable: true
      nroReserva nullable: true
      sena nullable: true
    }
}
