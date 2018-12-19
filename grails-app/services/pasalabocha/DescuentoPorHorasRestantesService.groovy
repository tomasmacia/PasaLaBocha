package pasalabocha

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDateTime

@Service(DescuentoPorHorasRestantes)
abstract class DescuentoPorHorasRestantesService {

    @Autowired
    DescuentoService descuentoService

    abstract DescuentoPorHorasRestantes get(Serializable id)

    abstract List<DescuentoPorHorasRestantes> list(Map args)

    abstract Long count()

    abstract void delete(Serializable id)

    abstract DescuentoPorHorasRestantes save(DescuentoPorHorasRestantes descuentoPorHorasRestantes)

    @Transactional
    def aplicar(DescuentoPorHorasRestantes descuento) {
        Club club = descuento.club

        LocalDateTime filtroFecha = LocalDateTime.now().plusHours(descuento.horasRestantes.toHours())

        List<Turno> turnos = club.canchas.turnos.flatten().findAll { Turno turno ->
            turno.fechaHorario.isBefore(filtroFecha) && !turno.descuento // si ya tiene descuento no aplico otro
        }

        println "Aplicando descuentos a los siguientes turnos -> " + turnos
        turnos.forEach { turno ->
            descuentoService.aplicar(turno, descuento)
        }
    }

}