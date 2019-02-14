package pasalabocha

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

//@Service(Descuento)
class DescuentoService {

    @Transactional
    Map<String, List<Descuento>> descuentosPorTipo(Club club) {
        Map<String, List<Descuento>> descuentos = new HashMap<>()

        descuentos.put("DescuentoEnRango", new ArrayList<Descuento>(club.descuentos.findAll { descuento ->
            descuento instanceof DescuentoEnRango
        }))

        descuentos.put("DescuentoPorHorasRestantes", new ArrayList<Descuento>(club.descuentos.findAll { descuento ->
            descuento instanceof DescuentoPorHorasRestantes
        }))

        descuentos.put("DescuentoPorTurnoAnterior", new ArrayList<Descuento>(club.descuentos.findAll { descuento ->
            descuento instanceof DescuentoPorTurnoAnterior
        }))

        return descuentos
    }

    @Transactional
    def aplicar(Descuento descuento) {
        Club club = descuento.club

        List<Turno> turnos = club.canchas.turnos.flatten().findAll { Turno turno ->
            descuento.turnoAplica(turno)
        }

        println "Aplicando descuentos a los siguientes turnos -> " + turnos
        turnos.forEach { turno ->
            turno.descuento = descuento
        }
    }
}
