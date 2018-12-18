package pasalabocha

import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import java.time.LocalDateTime

class Turno {
    LocalDateTime fechaHorario
    Duration duracion
    BigDecimal precioBase
    Descuento descuento

    static hasOne = [reserva: Reserva]

    static belongsTo = [cancha: Cancha]

    static constraints = {
      reserva nullable: true
      descuento nullable: true
    }

    String toString(){
      "${cancha}: ${fechaHorario}, ${duracion.toMinutes()} minutos"
    }

    def reservar(Cliente cliente){
      //modificar precioBase cuando esten los descuentos
      Duration tiempoLimiteCancelacionReserva = this.cancha.club.tiempoLimiteCancelacionReserva
      LocalDateTime plazoLimiteCancelacion = fechaHorario
      plazoLimiteCancelacion = plazoLimiteCancelacion - tiempoLimiteCancelacionReserva
      Duration tiempoLimitePagoDeSena = this.cancha.club.tiempoLimitePagoDeSena

      reserva = new Reserva(this, cliente, this.precioBase, plazoLimiteCancelacion).save(failOnError: true)
      this.save(failOnError: true)
    }

    boolean estaVencido(LocalDateTime ahora){
        (this.reserva == null && ahora.isAfter(this.fechaHorario))
    }
}
