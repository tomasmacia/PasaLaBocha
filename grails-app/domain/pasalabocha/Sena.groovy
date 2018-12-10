package pasalabocha

import java.time.LocalDateTime
import java.time.Duration

class Sena {
    BigDecimal monto
    boolean pagada = false
    LocalDateTime plazoLimitePago
    Integer nroSena // diferente al id de Sena
    Reserva reserva

    //static belongsTo = [reserva: Reserva]

    static constraints = {
      nroSena nullable: true
    }

    public Sena(Duration tiempoLimitePagoDeSena, BigDecimal monto){
      this.plazoLimitePago = LocalDateTime.now() + tiempoLimitePagoDeSena
      this.monto = monto
    }
}
