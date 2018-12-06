package pasalabocha

import java.time.LocalDateTime

class Sena {
    BigDecimal monto
    boolean pagada
    LocalDateTime plazoLimitePago
    Integer nroSena // diferente al id de Sena
    Reserva reserva

    static constraints = {
    }
}
