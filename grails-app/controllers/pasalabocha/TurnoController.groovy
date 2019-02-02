package pasalabocha

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import java.time.LocalDate
import java.time.LocalDateTime

@Secured(['permitAll'])
class TurnoController {

    TurnoService turnoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond turnoService.list(params), model:[turnoCount: turnoService.count()]
    }

    def show(Long id) {
        respond turnoService.get(id)
    }

    @Secured(['ROLE_CLIENTE'])
    def reservar(){
        Turno turno = turnoService.get(params.turnoId)
        turnoService.reservar(turno, authenticatedUser)
        redirect(controller:"cancha", action:"verTurnos", params: [id: turno.cancha.id])
    }

    @Secured(['ROLE_CLUB'])
    def save(Turno turno) {
        System.out.println(params)
        String[] tokens = params.fecha.split("-");
        LocalDate localDate = LocalDate.parse(params.fecha);
        turno.fecha = localDate
        System.out.println(localDate)
        System.out.println(turno)
        if (turno == null) {
            notFound()
            return
        }

        try {
            turnoService.save(turno)
        } catch (ValidationException e) {
            respond turno.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'turno.label', default: 'Turno'), turno.id])
                redirect turno
            }
            '*' { respond turno, [status: CREATED] }
        }
    }

    @Secured(['ROLE_CLUB'])
    def edit(Long id) {
        respond turnoService.get(id)
    }

    @Secured(['ROLE_CLUB'])
    def update(Turno turno) {
        if (turno == null) {
            notFound()
            return
        }

        try {
            turnoService.save(turno)
        } catch (ValidationException e) {
            respond turno.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'turno.label', default: 'Turno'), turno.id])
                redirect turno
            }
            '*'{ respond turno, [status: OK] }
        }
    }

    @Secured(['ROLE_CLUB'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        turnoService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'turno.label', default: 'Turno'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'turno.label', default: 'Turno'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
