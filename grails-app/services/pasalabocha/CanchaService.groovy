package pasalabocha

import grails.gorm.services.Service
import java.time.Duration
import java.time.LocalDate
import java.time.LocalTime

@Service(Cancha)
abstract class CanchaService {

    protected abstract Cancha get(Serializable id)

    protected abstract List<Cancha> list(Map args)

    protected abstract Long count()

    protected abstract void delete(Serializable id)

    protected abstract Cancha save(Cancha cancha)

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

}
