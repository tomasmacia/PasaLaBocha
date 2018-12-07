package pasalabocha

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
class CanchaController {

    CanchaService canchaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond canchaService.list(params), model:[canchaCount: canchaService.count()]
    }

    def show(Long id) {
        respond canchaService.get(id)
    }

    def create() {
        respond new Cancha(params)
    }

    def save(Cancha cancha) {
        if (cancha == null) {
            notFound()
            return
        }

        try {
            canchaService.save(cancha)
        } catch (ValidationException e) {
            respond cancha.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cancha.label', default: 'Cancha'), cancha.id])
                redirect cancha
            }
            '*' { respond cancha, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond canchaService.get(id)
    }

    def update(Cancha cancha) {
        if (cancha == null) {
            notFound()
            return
        }

        try {
            canchaService.save(cancha)
        } catch (ValidationException e) {
            respond cancha.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'cancha.label', default: 'Cancha'), cancha.id])
                redirect cancha
            }
            '*'{ respond cancha, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        canchaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cancha.label', default: 'Cancha'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cancha.label', default: 'Cancha'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
