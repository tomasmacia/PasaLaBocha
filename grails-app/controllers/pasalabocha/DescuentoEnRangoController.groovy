package pasalabocha

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import java.time.LocalDateTime

@Secured(['permitAll'])
class DescuentoEnRangoController {

    DescuentoEnRangoService descuentoEnRangoService

    static allowedMethods = [aplicarYGuardar: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond descuentoEnRangoService.list(params), model:[descuentoEnRangoCount: descuentoEnRangoService.count()]
    }

    def show(Long id) {
        respond descuentoEnRangoService.get(id)
    }

    @Secured(['ROLE_CLUB'])
    def create() {
    }

    @Secured(['ROLE_CLUB'])
    def aplicarYGuardar() {
        LocalDateTime fechaInicial = LocalDateTime.parse(params["fechaInicial"].toString())
        LocalDateTime fechaFinal = LocalDateTime.parse(params["fechaFinal"].toString())
        Club club = authenticatedUser
        DescuentoEnRango descuento = new DescuentoEnRango([club:club, porcentaje: params["porcentaje"],
                                            nivelConfiabilidadNecesario: params["nivelConfiabilidadNecesario"],
                                                fechaInicial: fechaInicial, fechaFinal: fechaFinal])
        //clubService.aplicarDescuentos(club, descuento)
        save(descuento)
    }

    private save(DescuentoEnRango descuentoEnRango) {
        if (descuentoEnRango == null) {
            notFound()
            return
        }

        try {
            descuentoEnRangoService.save(descuentoEnRango)
        } catch (ValidationException e) {
            respond descuentoEnRango.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'descuentoEnRango.label', default: 'DescuentoEnRango'), descuentoEnRango.id])
                redirect descuentoEnRango
            }
            '*' { respond descuentoEnRango, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond descuentoEnRangoService.get(id)
    }

    def update(DescuentoEnRango descuentoEnRango) {
        if (descuentoEnRango == null) {
            notFound()
            return
        }

        try {
            descuentoEnRangoService.save(descuentoEnRango)
        } catch (ValidationException e) {
            respond descuentoEnRango.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'descuentoEnRango.label', default: 'DescuentoEnRango'), descuentoEnRango.id])
                redirect descuentoEnRango
            }
            '*'{ respond descuentoEnRango, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        descuentoEnRangoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'descuentoEnRango.label', default: 'DescuentoEnRango'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'descuentoEnRango.label', default: 'DescuentoEnRango'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
