package pasalabocha

import grails.gorm.transactions.Transactional

@Transactional
class DescuentoService {

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
}
