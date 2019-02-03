package pasalabocha

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
class SenaController {

    SenaService senaService
    ReservaService reservaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond senaService.list(params), model:[senaCount: senaService.count()]
    }

    def show(Long id) {
        respond senaService.get(id)
    }

    def create() {
        respond new Sena(params)
    }

    @Secured(['ROLE_CLUB'])
    def pagar(Long id){
        println(id)
        Sena sena = Sena.get(id)
        println(sena)
        senaService.pagar(sena)
        redirect(controller: "club", action:"misSenas")
    }

    @Secured(['ROLE_ADMIN'])
    def chequearPagos(){
        def senas = senaService.list()
        senas = senas.grep {it.estaVencida()}
        reservaService.eliminarReservas(senas.collect {it.reserva})
        redirect(action:"index")
    }

    def save(Sena sena) {
        if (sena == null) {
            notFound()
            return
        }

        try {
            senaService.save(sena)
        } catch (ValidationException e) {
            respond sena.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'sena.label', default: 'Sena'), sena.id])
                redirect sena
            }
            '*' { respond sena, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond senaService.get(id)
    }

    def update(Sena sena) {
        if (sena == null) {
            notFound()
            return
        }

        try {
            senaService.save(sena)
        } catch (ValidationException e) {
            respond sena.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'sena.label', default: 'Sena'), sena.id])
                redirect sena
            }
            '*'{ respond sena, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        senaService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'sena.label', default: 'Sena'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'sena.label', default: 'Sena'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
