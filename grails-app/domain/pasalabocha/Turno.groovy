package pasalabocha

import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime

class Turno {
    LocalDate fecha
    LocalTime horario
    Duration duracion
    BigDecimal precioBase

    static hasOne = [reserva: Reserva]

    static belongsTo = [cancha: Cancha]

    static constraints = {
    }
}
