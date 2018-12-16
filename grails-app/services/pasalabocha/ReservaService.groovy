package pasalabocha

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

@Service(Reserva)
abstract class ReservaService {

    protected abstract Reserva get(Serializable id)

    protected abstract List<Reserva> list(Map args)

    protected abstract Long count()

    protected abstract void delete(Serializable id)

    protected abstract Reserva save(Reserva reserva)

    @Transactional
    void eliminarReservas(List<Reserva> reservas){
        reservas.each {it.delete(flush:true, failOnError:true)}
    }

}
