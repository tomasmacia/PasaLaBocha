package pasalabocha

import java.time.LocalDateTime
import java.time.Duration

class Sena {
    BigDecimal monto
    boolean pagada
    LocalDateTime plazoLimitePago
    Integer nroSena // diferente al id de Sena

    static belongsTo = [reserva: Reserva]

    static constraints = {
        nroSena nullable: true
    }

    public Sena(Reserva reserva, Duration tiempoLimitePagoDeSena, BigDecimal precioReserva, Integer porcentajeSena, LocalDateTime ahora){
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

    String toString(){
      String pagadaString = "impaga"
      if (pagada){
        pagadaString = "pagada"
      }
      "${monto} ${pagadaString}"
    }
}
