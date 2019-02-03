package pasalabocha

import grails.validation.ValidationException

import java.time.LocalDateTime

import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import java.time.Duration
import java.time.LocalTime

@Secured(['permitAll'])
class CanchaController {

    CanchaService canchaService
    ClienteService clienteService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", generarTurnos: "POST"]

    @Secured(['ROLE_ADMIN'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond canchaService.list(params), model:[canchaCount: canchaService.count()]
    }

    @Secured(['ROLE_CLUB'])
    def show(Long id) {
        respond canchaService.get(id)
    }

    @Secured(['ROLE_CLUB'])
    def create() {
        respond new Cancha(params)
    }

    @Secured(['ROLE_CLUB'])
    def verMisTurnos(Long canchaId) {
        Club club = authenticatedUser
        Cancha cancha = canchaService.get(canchaId)
        if (!club.canchas.contains(cancha)){
            redirect(action:"verTurnos", params:"[id:canchaId]")
        }
        respond cancha.turnos, model:[id:cancha.id]
    }

    @Secured(['ROLE_CLUB'])
    def crearTurnos(Long id){

    }

    @Secured(['ROLE_CLUB'])
    def generarTurnos() {
        List<LocalDateTime> fechas = params.findAll{ param ->
            param.key.toString().startsWith("horario-")
        }.collect { param ->
            LocalDateTime.parse(param.value.toString())
        }
        Duration duracion = Duration.ofMinutes(Long.valueOf(params.duracion.toString()))
        canchaService.generarTurnos(Long.valueOf(params.id.toString()), fechas, duracion, Long.valueOf(params.precio.toString()))
        redirect(action: "show", params: [id: params.id])
    }

    @Secured(['ROLE_CLUB'])
    private eliminarTurno(Long canchaId, Long turnoId){
      Turno turno = Turno.get(turnoId)
      Cancha cancha = Cancha.get(canchaId)
      cancha.eliminarTurno(turno)
      redirect(controller:"club", action:"misReservas")
    }

    @Secured(['ROLE_CLIENTE'])
    def verTurnos(Long id){
      Cancha cancha = canchaService.get(id)
      respond cancha.turnos
    }

    @Secured(['ROLE_ADMIN'])
    def eliminarTurnosVencidos(){
      List<Turno> turnos = Turno.list()
      canchaService.eliminarVencidos(turnos)
      redirect(controller:"turno", action:"index")
  }

    @Secured(['ROLE_CLUB'])
    def save(Cancha cancha) {
        if (cancha == null) {
            notFound()
            return
        }
        Club club = authenticatedUser
        club.agregarCancha(cancha)
        try {
            canchaService.save(cancha)
        } catch (ValidationException e) {
            respond cancha.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cancha.label', default: 'Cancha'), cancha.id]) //cancha.numeroDeCancha])
                redirect cancha
            }
            '*' { respond cancha, [status: CREATED] }
        }
    }

    @Secured(['ROLE_CLUB'])
    def edit(Long id) {
        respond canchaService.get(id)
    }

    @Secured(['ROLE_CLUB'])
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

    // NO FUNCIONA; AL ELIMINAR SIGUE AHI
    @Secured(['ROLE_CLUB'])
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
