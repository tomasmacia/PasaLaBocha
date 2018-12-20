package pasalabocha

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

import java.time.Duration

import static org.springframework.http.HttpStatus.*

@Secured(['permitAll'])
class DescuentoPorTurnoAnteriorController {

    DescuentoPorTurnoAnteriorService descuentoPorTurnoAnteriorService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond descuentoPorTurnoAnteriorService.list(params), model:[descuentoPorTurnoAnteriorCount: descuentoPorTurnoAnteriorService.count()]
    }

    def show(Long id) {
        respond descuentoPorTurnoAnteriorService.get(id)
    }

    @Secured(['ROLE_CLUB'])
    def create() {
    }

    @Secured(['ROLE_CLUB'])
    def guardar(){
        Duration horas = Duration.ofHours(Long.valueOf(params.horasRestantes.toString()))
        Club club = authenticatedUser
        DescuentoPorTurnoAnterior descuento = new DescuentoPorTurnoAnterior([club:club, porcentaje: params["porcentaje"],
                                                                               nivelConfiabilidadNecesario: params["nivelConfiabilidadNecesario"],
                                                                               horasRestantes: horas])
        save(descuento)
    }

    def save(DescuentoPorTurnoAnterior descuentoPorTurnoAnterior) {
        if (descuentoPorTurnoAnterior == null) {
            notFound()
            return
        }

        try {
            descuentoPorTurnoAnteriorService.save(descuentoPorTurnoAnterior)
        } catch (ValidationException e) {
            respond descuentoPorTurnoAnterior.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'descuentoPorTurnoAnterior.label', default: 'DescuentoPorTurnoAnterior'), descuentoPorTurnoAnterior.id])
                redirect descuentoPorTurnoAnterior
            }
            '*' { respond descuentoPorTurnoAnterior, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond descuentoPorTurnoAnteriorService.get(id)
    }

    def update(DescuentoPorTurnoAnterior descuentoPorTurnoAnterior) {
        if (descuentoPorTurnoAnterior == null) {
            notFound()
            return
        }

        try {
            descuentoPorTurnoAnteriorService.save(descuentoPorTurnoAnterior)
        } catch (ValidationException e) {
            respond descuentoPorTurnoAnterior.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'descuentoPorTurnoAnterior.label', default: 'DescuentoPorTurnoAnterior'), descuentoPorTurnoAnterior.id])
                redirect descuentoPorTurnoAnterior
            }
            '*'{ respond descuentoPorTurnoAnterior, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        descuentoPorTurnoAnteriorService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'descuentoPorTurnoAnterior.label', default: 'DescuentoPorTurnoAnterior'), id])
                redirect controller:"descuento", action:"listar", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'descuentoPorTurnoAnterior.label', default: 'DescuentoPorTurnoAnterior'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
