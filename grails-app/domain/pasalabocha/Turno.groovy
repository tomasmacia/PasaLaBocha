package pasalabocha

import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.LocalDateTime

class Turno {
    LocalDate fecha
    LocalTime horario
    Duration duracion
    BigDecimal precioBase


    static hasOne = [reserva: Reserva]

    static belongsTo = [cancha: Cancha]

    static constraints = {
      reserva nullable: true
      //duracion nullable: true
      //horario nullable: true
    }

    String toString(){
      "${cancha}: ${fecha}, ${horario}, ${duracion.toMinutes()} minutos"
    }

    def reservar(){
      System.out.println("reservando")
      //modificar precioBase cuando esten los descuentos
      Duration tiempoLimiteCancelacionReserva = this.cancha.club.tiempoLimiteCancelacionReserva
      LocalDateTime plazoLimiteCancelacion = LocalDateTime.of(this.fecha, this.horario)
      plazoLimiteCancelacion = plazoLimiteCancelacion - tiempoLimiteCancelacionReserva
      reserva = new Reserva([turno:this, precioFinal: this.precioBase, plazoLimiteCancelacion: plazoLimiteCancelacion]).save(failOnError: true)
      System.out.println(reserva)
      this.save()
    }
}
