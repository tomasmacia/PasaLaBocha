package pasalabocha

import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
class DescuentoController {

    DescuentoService descuentoService

    @Secured(['ROLE_CLUB'])
    def generadorDescuentos() {

    }

    @Secured(['ROLE_CLUB'])
    def listar() {
        Club club = authenticatedUser
        Map<String, List<Descuento>> descuentosPorTipo = descuentoService.descuentosPorTipo(club)
        render view: "listar", model: [
                descuentosEnRango: descuentosPorTipo["DescuentoEnRango"],
                descuentosPorHorasRestantes: descuentosPorTipo["DescuentoPorHorasRestantes"],
                descuentosPorTurnoAnterior: descuentosPorTipo["DescuentoPorTurnoAnterior"]
        ]
    }
}
