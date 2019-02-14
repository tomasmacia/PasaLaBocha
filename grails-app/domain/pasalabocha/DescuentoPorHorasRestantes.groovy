package pasalabocha

import java.time.Duration
import java.time.LocalDateTime

class DescuentoPorHorasRestantes extends Descuento {
    Duration horasRestantes

    static constraints = {
    }

    boolean turnoAplica(Turno turno) {
        LocalDateTime filtroFecha = LocalDateTime.now().plusHours(this.horasRestantes.toHours())
        turno.fechaHorario.isBefore(filtroFecha) && !turno.descuento // si ya tiene descuento no aplico otro
    }
}
