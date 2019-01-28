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

    public Sena(Reserva reserva, Duration tiempoLimitePagoDeSena, BigDecimal monto){
      this.reserva = reserva
      this.plazoLimitePago = LocalDateTime.now() + tiempoLimitePagoDeSena
      this.monto = monto
    }

    boolean estaVencida(){
        !this.pagada && this.plazoLimitePago.isBefore(LocalDateTime.now())
    }

    String toString(){
      String pagadaString = "impaga"
      if (pagada){
        pagadaString = "pagada"
      }
      "${monto} ${pagadaString}"
    }
}
