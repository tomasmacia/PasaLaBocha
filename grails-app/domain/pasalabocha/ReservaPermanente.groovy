package pasalabocha

import java.time.LocalTime
import java.time.DayOfWeek
import java.time.LocalDateTime

class ReservaPermanente {

    LocalTime horario
    DayOfWeek diaDeLaSemana

    static belongsTo = [cancha: Cancha, cliente: Cliente]

    static constraints = {
    }

    boolean aplicaA(Turno turno) {
        turno.fechaHorario.getDayOfWeek() == this.diaDeLaSemana && turno.fechaHorario.toLocalTime() == this.horario
    }

    boolean seSuperpone(ReservaPermanente other){
        this.diaDeLaSemana == other.diaDeLaSemana && this.horario == other.horario
    }

    Set<Reserva> aplicarA(Set<Turno> turnos){
        Set<Turno> aReservar = turnos.findAll {this.aplicaA(it)}
        Set<Reserva> reservas = aReservar.collect { it.reservar(this.cliente, LocalDateTime.now())}
        return reservas
    }
}
