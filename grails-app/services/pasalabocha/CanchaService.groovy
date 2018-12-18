package pasalabocha

import grails.gorm.services.Service
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import grails.gorm.transactions.Transactional

@Service(Cancha)
abstract class CanchaService {

    protected abstract Cancha get(Serializable id)

    protected abstract List<Cancha> list(Map args)

    protected abstract Long count()

    protected abstract void delete(Serializable id)

    protected abstract Cancha save(Cancha cancha)

//    @Transactional
//    void generarTurno(Long id, LocalDateTime fechaHorario, Duration duracion, Long precio) {
//        Cancha cancha = get(id)
//        Turno turno = new Turno([
//                fechaHorario: fechaHorario,
//                duracion: duracion,
//                precioBase: precio,
//                cancha: cancha,
//        ]).save(failOnError: true)
//    }

    @Transactional
    void generarTurnos(Long id, List<LocalDateTime> fechas, Duration duracion, Long precio) {
        Cancha cancha = get(id)
        fechas.each{ fecha ->
            new Turno([
                    fechaHorario: fecha,
                    duracion: duracion,
                    precioBase: precio,
                    cancha: cancha,
            ]).save(failOnError: true)
        }
    }

    @Transactional
    void eliminarTurno(Long cancha_id,Turno turno){
      Cancha cancha = get(cancha_id)
      cancha.removeFromTurnos(turno)
      turno.delete(failOnError: true)
      cancha.save(failOnError: true, flush: true)
    }

    @Transactional
    void eliminarVencidos(List<Turno> turnos){
        def ahora = LocalDateTime.now()
        turnos.each {turno -> if (turno.estaVencido(ahora)){
                                eliminarTurno(turno.cancha.id, turno)
                              }}
    }

}
