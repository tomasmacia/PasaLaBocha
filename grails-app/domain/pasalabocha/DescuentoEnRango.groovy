package pasalabocha

import java.time.LocalDateTime

class DescuentoEnRango extends Descuento{
    LocalDateTime fechaInicial
    LocalDateTime fechaFinal


    static constraints = {
    }

    boolean turnoAplica(Turno turno) {
        turno.fechaHorario.isAfter(this.fechaInicial) &&
        turno.fechaHorario.isBefore(this.fechaFinal) &&
        !turno.descuento // si ya tiene descuento no aplico otro
    }
}
