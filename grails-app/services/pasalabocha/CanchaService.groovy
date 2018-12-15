package pasalabocha

import grails.gorm.services.Service
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime
import grails.gorm.transactions.Transactional

@Service(Cancha)
abstract class CanchaService {

    protected abstract Cancha get(Serializable id)

    protected abstract List<Cancha> list(Map args)

    protected abstract Long count()

    protected abstract void delete(Serializable id)

    protected abstract Cancha save(Cancha cancha)

    @Transactional
    void generarTurnos(Long id, LocalTime horarioInicio, LocalTime horarioFin, Duration largoTurno, Long precio){
      horarioInicio = horarioInicio + largoTurno
      Cancha cancha = get(id)
      LocalDate hoy = LocalDate.now();
      while (horarioInicio.isBefore(horarioFin)){
        def turno = new Turno([
                fecha: hoy,
                horario: horarioInicio,
                duracion: largoTurno,
                precioBase: precio,
                cancha: cancha,
        ]).save(failOnError: true)
        horarioInicio = horarioInicio + largoTurno
      }
    }

    @Transactional
    void eliminarTurno(cancha_id, turno){
      Cancha cancha = get(cancha_id)
      cancha.removeFromTurnos(turno)
      turno.delete(failOnError: true)
      cancha.save(failOnError: true, flush: true)
    }

}
