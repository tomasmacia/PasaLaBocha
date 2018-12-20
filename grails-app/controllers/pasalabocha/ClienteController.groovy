package pasalabocha

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured(['permitAll'])
class ClienteController {

    ClienteService clienteService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond clienteService.list(params), model:[clienteCount: clienteService.count()]
    }

    def show(Long id) {
        respond clienteService.get(id)
    }

    def create() {
        respond new Cliente(params)
    }

    @Secured(['ROLE_CLIENTE'])
    def miCliente(){
        Cliente cliente = authenticatedUser
        respond cliente
    }

    @Secured(['ROLE_CLIENTE'])
    def misReservas(){
      Cliente cliente = authenticatedUser
      respond cliente.reservas
    }

    def save(Cliente cliente) {
        if (cliente == null) {
            notFound()
            return
        }

        try {
            clienteService.save(cliente)
        } catch (ValidationException e) {
            respond cliente.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cliente.label', default: 'Cliente'), cliente.id])
                redirect cliente
            }
            '*' { respond cliente, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond clienteService.get(id)
    }

    def update(Cliente cliente) {
        if (cliente == null) {
            notFound()
            return
        }

        try {
            clienteService.save(cliente)
        } catch (ValidationException e) {
            respond cliente.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'cliente.label', default: 'Cliente'), cliente.id])
                redirect cliente
            }
            '*'{ respond cliente, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        clienteService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cliente.label', default: 'Cliente'), id])
                redirect controller:"home", action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cliente.label', default: 'Cliente'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
