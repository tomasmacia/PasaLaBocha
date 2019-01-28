package pasalabocha

import java.time.Duration
import java.time.LocalDateTime

class Turno {
    LocalDateTime fechaHorario
    Duration duracion
    BigDecimal precioBase // clase Dinero
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
      BigDecimal precioFinal = this.getPrecioFinal()

      reserva = new Reserva(this, cliente, precioFinal, plazoLimiteCancelacion).save(failOnError: true)
      this.save(failOnError: true) // sin saves
    }

    BigDecimal getPrecioFinal() {
        BigDecimal precioFinal = this.precioBase

        if (this.descuento && this.descuento.porcentaje) {
            precioFinal = precioFinal * (1 - this.descuento.porcentaje / 100)
        }

        precioFinal
    }

    boolean estaVencido(LocalDateTime ahora){
        (this.reserva == null && ahora.isAfter(this.fechaHorario))
    }
}
