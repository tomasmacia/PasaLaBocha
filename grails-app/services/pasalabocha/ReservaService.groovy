package pasalabocha

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

import java.time.LocalDateTime

@Service(Reserva)
abstract class ReservaService {

    ClienteService clienteService

    protected abstract Reserva get(Serializable id)

    protected abstract List<Reserva> list(Map args)

    protected abstract Long count()

    protected abstract void delete(Serializable id)

    protected abstract Reserva save(Reserva reserva)

    @Transactional
    void eliminarReservas(List<Reserva> reservas){
        reservas.each {it.delete(flush:true, failOnError:true)}
    }

    @Transactional
    void asistenciaCumplida(Reserva reserva){
        if (!reserva.turno.termino(LocalDateTime.now())){
            throw new Exception("El turno todavia no ha terminado")
        } else if (!reserva.tieneSenaPaga()){
            throw new Exception("Aun no se ha pagado la seña de esta reserva")
        }
        reserva.asistencia = Asistencia.ASISTIO
        clienteService.aumentarConfiabilidad(reserva.cliente)
    }

    @Transactional
    void asistenciaIncumplida(Long reservaId){
        Reserva reserva = Reserva.get(reservaId)
        if (!reserva.turno.termino(LocalDateTime.now())){
           throw new Exception("El turno todavia no ha terminado")
        }
        reserva.asistencia = Asistencia.NO_ASISTIO
        clienteService.disminuirConfiabilidad(reserva.cliente)
    }

}
