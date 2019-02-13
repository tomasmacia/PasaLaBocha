package pasalabocha

import java.time.Duration
import java.time.LocalDateTime

class Turno {
    LocalDateTime fechaHorario
    Duration duracion
    Dinero precioBase // clase Dinero
    Descuento descuento

    static hasOne = [reserva: Reserva]

    static embedded = ['precioBase']

    static belongsTo = [cancha: Cancha]

    static constraints = {
        reserva nullable: true
        descuento nullable: true
    }

    String toString(){
        "${cancha}: ${fechaHorario}, ${duracion.toMinutes()} minutos"
    }

    Reserva reservar(Cliente cliente, LocalDateTime ahora){
        if (this.reserva){
            throw new Exception("El turno seleccionado ya se encuentra reservado")
        }
        Duration tiempoLimiteCancelacionReserva = this.cancha.club.tiempoLimiteCancelacionReserva
        LocalDateTime plazoLimiteCancelacion = fechaHorario
        plazoLimiteCancelacion = plazoLimiteCancelacion - tiempoLimiteCancelacionReserva
        Duration tiempoLimitePagoDeSena = this.cancha.club.tiempoLimitePagoDeSena
        Dinero precioFinal = this.calcularPrecioFinal()

        this.reserva = new Reserva(this, cliente, precioFinal, plazoLimiteCancelacion, ahora)
        this.reserva
    }

    Dinero calcularPrecioFinal(){
        if (this.descuento){
            return this.descuento.aplicarA(this.precioBase)
        } else {
            return precioBase
        }
    }

    boolean estaVencido(LocalDateTime ahora){
        (this.reserva == null && ahora.isAfter(this.fechaHorario + this.duracion))
    }

    boolean seSuperpone(Turno other){
        if (this.fechaHorario > other.fechaHorario){
            return (other.fechaHorario + other.duracion > this.fechaHorario)
        } else if (other.fechaHorario > this.fechaHorario){
            return (this.fechaHorario + this.duracion > other.fechaHorario)
        } else {
            return true
        }
    }

    boolean esAntesDe(LocalDateTime fechaHorario){
        this.fechaHorario < fechaHorario
    }

    boolean estaReservado(){
        this.reserva != null && !this.reserva.estaCumplida()
    }

    boolean termino(LocalDateTime ahora){
        ahora > this.fechaHorario + this.duracion
    }
}
