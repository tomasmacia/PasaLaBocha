package pasalabocha

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
import org.springframework.beans.factory.annotation.Autowired

@Service(DescuentoEnRango)
abstract class DescuentoEnRangoService {

    @Autowired
    DescuentoService descuentoService

    abstract DescuentoEnRango get(Serializable id)

    abstract List<DescuentoEnRango> list(Map args)

    abstract Long count()

    abstract void delete(Serializable id)

    abstract DescuentoEnRango save(DescuentoEnRango descuentoEnRango)

    @Transactional
    def aplicar(DescuentoEnRango descuento) {
        Club club = descuento.club

        List<Turno> turnos = club.canchas.turnos.flatten().findAll { Turno turno ->
            turno.fechaHorario.isAfter(descuento.fechaInicial) &&
            turno.fechaHorario.isBefore(descuento.fechaFinal) &&
            !turno.descuento // si ya tiene descuento no aplico otro
        }

        println "Aplicando descuentos a los siguientes turnos -> " + turnos
        turnos.forEach { turno ->
            descuentoService.aplicar(turno, descuento)
        }
    }

}