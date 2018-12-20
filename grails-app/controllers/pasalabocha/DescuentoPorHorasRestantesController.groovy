package pasalabocha

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

import java.time.Duration

import static org.springframework.http.HttpStatus.*

@Secured(['permitAll'])
class DescuentoPorHorasRestantesController {

    DescuentoPorHorasRestantesService descuentoPorHorasRestantesService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", guardar: "POST"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond descuentoPorHorasRestantesService.list(params), model:[descuentoPorHorasRestantesCount: descuentoPorHorasRestantesService.count()]
    }

    def show(Long id) {
        respond descuentoPorHorasRestantesService.get(id)
    }

    @Secured(['ROLE_CLUB'])
    def create() {
    }


    @Secured(['ROLE_CLUB'])
    def guardar(){
        Duration horas = Duration.ofHours(Long.valueOf(params.horasRestantes.toString()))
        Club club = authenticatedUser
        DescuentoPorHorasRestantes descuento = new DescuentoPorHorasRestantes([club:club, porcentaje: params["porcentaje"],
                                                                               nivelConfiabilidadNecesario: params["nivelConfiabilidadNecesario"],
                                                                               horasRestantes: horas])
        save(descuento)
    }

    def save(DescuentoPorHorasRestantes descuentoPorHorasRestantes) {
        if (descuentoPorHorasRestantes == null) {
            notFound()
            return
        }

        try {
            descuentoPorHorasRestantesService.save(descuentoPorHorasRestantes)
        } catch (ValidationException e) {
            respond descuentoPorHorasRestantes.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'descuentoPorHorasRestantes.label', default: 'DescuentoPorHorasRestantes'), descuentoPorHorasRestantes.id])
                redirect descuentoPorHorasRestantes
            }
            '*' { respond descuentoPorHorasRestantes, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond descuentoPorHorasRestantesService.get(id)
    }

    def update(DescuentoPorHorasRestantes descuentoPorHorasRestantes) {
        if (descuentoPorHorasRestantes == null) {
            notFound()
            return
        }

        try {
            descuentoPorHorasRestantesService.save(descuentoPorHorasRestantes)
        } catch (ValidationException e) {
            respond descuentoPorHorasRestantes.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'descuentoPorHorasRestantes.label', default: 'DescuentoPorHorasRestantes'), descuentoPorHorasRestantes.id])
                redirect descuentoPorHorasRestantes
            }
            '*'{ respond descuentoPorHorasRestantes, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        descuentoPorHorasRestantesService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'descuentoPorHorasRestantes.label', default: 'DescuentoPorHorasRestantes'), id])
                redirect controller:"descuento", action:"listar", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'descuentoPorHorasRestantes.label', default: 'DescuentoPorHorasRestantes'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
