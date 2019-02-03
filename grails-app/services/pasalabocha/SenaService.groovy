package pasalabocha

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

import java.time.LocalDateTime

@Service(Sena)
abstract class SenaService {

    protected abstract Sena get(Serializable id)

    protected abstract List<Sena> list(Map args)

    protected abstract Long count()

    protected abstract void delete(Serializable id)

    protected abstract Sena save(Sena sena)

    @Transactional
    void pagar(Sena sena){
        if (sena.estaVencida(LocalDateTime.now())){
            throw new Exception("La reserva ya se encuentra vencida")
        } else if (sena.pagada){
            throw new Exception("La reserva ya se encuentra pagada")
        } else {
            sena.pagada = true // entidad pagar
        }
    }

}
