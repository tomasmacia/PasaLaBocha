package pasalabocha

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

import java.time.LocalDateTime;

@Service(Turno)
abstract class TurnoService {

    protected abstract Turno get(Serializable id)

    protected abstract List<Turno> list(Map args)

    protected abstract Long count()

    protected abstract void delete(Serializable id)

    protected abstract Turno save(Turno turno)

    @Transactional
    void reservar(Turno turno, Cliente cliente){
        turno.reservar(cliente, LocalDateTime.now())
    }
}
