package pasalabocha

import java.time.LocalDateTime

class Reserva {
    BigDecimal precioFinal
    LocalDateTime plazoLimiteCancelacion
    boolean concretada
    Integer nroReserva
    Turno turno

    static hasOne = [sena: Sena]

    static belongsTo = [turno: Turno]

    static constraints = {
    }
}
