package pasalabocha

import java.time.LocalDateTime
import java.time.Duration

class Sena {
    BigDecimal monto
    boolean pagada = false
    LocalDateTime plazoLimitePago
    Integer nroSena // diferente al id de Sena

    static belongsTo = [reserva: Reserva]

    static constraints = {
      nroSena nullable: true
    }

    public Sena(Duration tiempoLimitePagoDeSena, BigDecimal precioReserva, Integer porcentajeSena, LocalDateTime ahora){
      this.plazoLimitePago = ahora + tiempoLimitePagoDeSena
      this.monto = precioReserva * (porcentajeSena / 100)
    }

    boolean estaVencida(LocalDateTime ahora){
        !this.pagada && this.plazoLimitePago.isBefore(ahora)
    }

    def pagar(LocalDateTime ahora){
        if (this.estaVencida(ahora)){
            throw new Exception("La reserva ya se encuentra vencida")
        } else if (this.pagada){
            throw new Exception("La reserva ya se encuentra pagada")
        } else {
            this.pagada = true
        }
    }

    String toString(){
      String pagadaString = "impaga"
      if (pagada){
        pagadaString = "pagada"
      }
      "${monto} ${pagadaString}"
    }
}
