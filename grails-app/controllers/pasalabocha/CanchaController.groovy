package pasalabocha

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import java.time.Duration
import java.time.LocalTime

@Secured(['permitAll'])
class CanchaController {

    CanchaService canchaService
    ClienteService clienteService
    TurnoService turnoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", generarTurnos: "POST"]

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

    def crearTurnos(Long id){
    }

    def generarTurnos(){
      LocalTime horarioInicio = LocalTime.parse(params.horarioInicio)
      LocalTime horarioFin = LocalTime.parse(params.horarioFin)
      Duration largoTurno = Duration.ofMinutes(Long.valueOf(params.largoTurno))
      canchaService.generarTurnos(Long.valueOf(params.id), horarioInicio, horarioFin, largoTurno, Long.valueOf(params.precio))

      redirect(action: "show", params: [id: params.id])
    }

    @Secured(['ROLE_CLIENTE'])
    def verTurnos(Long id){
      Cancha cancha = canchaService.get(id)
      System.out.println(cancha.turnos)
      respond cancha.turnos
    }

    def reservarTurno(){
        System.out.println(params.turnoId)

        Turno turno = turnoService.get(params.turnoId)
        turno.reservar(authenticatedUser)
        redirect(action:"verTurnos", params: [id: turno.cancha.id])
    }

    private eliminarTurno(Long canchaId, Long turnoId){
      Turno turno = Turno.get(turnoId)
      canchaService.eliminarTurno(canchaId, turno)
      redirect(controller:"club", action:"misReservas")
    }

    def eliminarClienteHabitual(Long id, String username){
      println(username)
      println(id)
      Cliente cliente = Cliente.findByUsername(username)
      clubService.eliminarClienteHabitual(id, cliente)
      redirect(action:"verClientesHabituales", params: [id: id])
    }

    def asistenciaCumplida(Long canchaId, Long turnoId) {
        // decorar con nivel confiabilidad
        eliminarTurno(canchaId, turnoId)
    }

    def asistenciaIncumplida(Long canchaId, Long turnoId) {
        // decorar con nivel confiabilidad
        eliminarTurno(canchaId, turnoId)
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
                flash.message = message(code: 'default.created.message', args: [message(code: 'cancha.label', default: 'Cancha'), cancha.id]) //cancha.numeroDeCancha])
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

    // NO FUNCIONA; AL ELIMINAR SIGUE AHI
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
