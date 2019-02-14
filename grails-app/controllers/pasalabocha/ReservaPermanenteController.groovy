package pasalabocha

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import java.time.LocalTime
import java.time.DayOfWeek

class ReservaPermanenteController {

    ReservaPermanenteService reservaPermanenteService
    CanchaService canchaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond reservaPermanenteService.list(params), model:[reservaPermanenteCount: reservaPermanenteService.count()]
    }

    @Secured(['ROLE_ADMIN'])
    def show(Long id) {
        respond reservaPermanenteService.get(id)
    }

    @Secured(['ROLE_CLIENTE'])
    def create(Long id) {
        respond new ReservaPermanente(params), model:[canchaId: id]
    }

    @Secured(['ROLE_CLIENTE'])
    def save(ReservaPermanente reservaPermanente) {
        if (reservaPermanente == null) {
            notFound()
            return
        }
        reservaPermanente.horario = LocalTime.parse(params.horario1)
        reservaPermanente.diaDeLaSemana = params.diaDeLaSemana
        reservaPermanente.cliente = authenticatedUser
        Cancha cancha = Cancha.get(params.canchaId)
        canchaService.agregarReservaPermanente(cancha, reservaPermanente)
        try {
            reservaPermanenteService.save(reservaPermanente)
        } catch (ValidationException e) {
            respond reservaPermanente.errors, view:'create'
            return
        }

        redirect(controller: "cancha", action: "verTurnos", id: cancha.id)
    }

    @Secured(['ROLE_CLIENTE'])
    def edit(Long id) {
        respond reservaPermanenteService.get(id)
    }

    @Secured(['ROLE_CLIENTE'])
    def update(ReservaPermanente reservaPermanente) {
        if (reservaPermanente == null) {
            notFound()
            return
        }

        try {
            reservaPermanenteService.save(reservaPermanente)
        } catch (ValidationException e) {
            respond reservaPermanente.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'reservaPermanente.label', default: 'ReservaPermanente'), reservaPermanente.id])
                redirect reservaPermanente
            }
            '*'{ respond reservaPermanente, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        reservaPermanenteService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'reservaPermanente.label', default: 'ReservaPermanente'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservaPermanente.label', default: 'ReservaPermanente'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
