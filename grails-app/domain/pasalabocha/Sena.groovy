package pasalabocha

import java.time.LocalDateTime
import java.time.Duration

class Sena {
    Dinero monto
    boolean pagada
    LocalDateTime plazoLimitePago
    Integer nroSena // diferente al id de Sena

    static belongsTo = [reserva: Reserva]

    static embedded = ['monto']

    static constraints = {
        nroSena nullable: true
    }

    public Sena(Reserva reserva, Duration tiempoLimitePagoDeSena, Dinero precioReserva, Integer porcentajeSena, LocalDateTime ahora){
        this.reserva = reserva
        this.pagada = false
        this.plazoLimitePago = ahora + tiempoLimitePagoDeSena
        this.monto = precioReserva * (porcentajeSena / 100)
    }

    boolean estaVencida(LocalDateTime ahora){
        !this.pagada && this.plazoLimitePago.isBefore(ahora)
    }

    boolean estaPaga(){
        this.pagada
    }

    Dinero getPrecioRestante(){
        if (estaPaga()){
            return new Dinero(0)
        }
        return this.monto
    }

    String toString(){
      String pagadaString = "impaga"
      if (pagada){
        pagadaString = "pagada"
      }
      "${monto} ${pagadaString}"
    }
}
