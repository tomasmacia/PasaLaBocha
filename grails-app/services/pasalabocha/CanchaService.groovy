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

    @Transactional
    void generarTurnos(Long id, List<LocalDateTime> fechas, Duration duracion, Long precio) {
        Cancha cancha = get(id)
        fechas.each{ fecha ->
            Turno turno = new Turno(
                    fechaHorario: fecha,
                    duracion: duracion,
                    precioBase: new Dinero(precio, Moneda.ARS))
            Reserva reserva = cancha.agregarTurno(turno, LocalDateTime.now())
            turno.save(failOnError: true)
            if (reserva){
                reserva.save(failOnError: true)
            }
        }
    }

    @Transactional
    void eliminarVencidos(List<Turno> turnos){
        def ahora = LocalDateTime.now() // parametro y separar logica
        turnos.each {turno -> if (turno.estaVencido(ahora)){
                                eliminarTurno(turno.cancha.id, turno)
                              }}
    }

    @Transactional
    def agregarReservaPermanente(Cancha cancha, ReservaPermanente reservaPermanente){
        cancha.agregarReservaPermanente(reservaPermanente)
    }

}
