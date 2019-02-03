package pasalabocha

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import java.time.Duration

@Secured(['permitAll'])
class ClubController {

    ClubService clubService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE", agregarClienteHabitual: "POST"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond clubService.list(params), model:[clubCount: clubService.count()]
    }

    @Secured(['ROLE_CLIENTE'])
    def show(Long id) {
        respond clubService.get(id)
    }

    def create() {
        respond new Club(params)
    }

    def verCanchas(Long id){
        Club club = clubService.get(id)
        respond (club.canchas)
    }

    @Secured(['ROLE_CLUB'])
    def verClientesHabituales(){
        Club club = authenticatedUser
        respond club.clientesHabituales
    }

    @Secured(['ROLE_CLUB'])
    def agregarClienteHabitualForm(){
        respond Cliente.list()
    }

    @Secured(['ROLE_CLUB'])
    def agregarClienteHabitual(String username){
        Club club = authenticatedUser
        Cliente cliente = Cliente.findByUsername(username)
        clubService.agregarClienteHabitual(club, cliente)
        redirect action:"verClientesHabituales"
    }

    @Secured(['ROLE_CLUB'])
    def eliminarClienteHabitual(String username){
        Club club = authenticatedUser
        Cliente cliente = Cliente.findByUsername(username)
        clubService.eliminarClienteHabitual(club, cliente)
        redirect action:"verClientesHabituales"
    }

    @Secured(['ROLE_CLUB'])
    def miClub(){
      Club club = authenticatedUser
      respond club
    }

    @Secured(['ROLE_CLUB'])
    def misReservas(){
      Club club = authenticatedUser
      def reservas = Reserva.withCriteria {
                                eq("asistencia", Asistencia.INDETERMINADA)
                                turno {
                                    cancha {
                                        eq("club", club)
                                    }
                                }
                            }
      respond reservas
    }

    @Secured(['ROLE_CLUB'])
    def misSenas(){
      Club club = authenticatedUser
      def reservas = Reserva.withCriteria {
                                eq("asistencia", Asistencia.INDETERMINADA)
                                turno {
                                    cancha {
                                        eq("club", club)
                                    }
                                }
                            }
      respond reservas
    }

    @Secured(['ROLE_CLUB'])
    def misCanchas(){
      Club club = authenticatedUser
      respond (club.canchas, model:[id:club.id])
    }

    @Secured(['ROLE_CLUB'])
    def generadorDescuentos(){
        redirect(controller: "descuento", action: "generadorDescuentos")
    }

    def save(Club club) {
        club.tiempoLimitePagoDeSena = Duration.ofMinutes(Long.valueOf(params.tiempoSena))
        club.tiempoLimiteCancelacionReserva = Duration.ofMinutes(Long.valueOf(params.tiempoCancelacion))
        if (club == null) {
            notFound()
            return
        }

        try {
            clubService.save(club)
        } catch (ValidationException e) {
            respond club.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'club.label', default: 'Club'), club.id])
                redirect club
            }
            '*' { respond club, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond clubService.get(id)
    }

    def update(Club club) {
        if (club == null) {
            notFound()
            return
        }

        try {
            clubService.save(club)
        } catch (ValidationException e) {
            respond club.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'club.label', default: 'Club'), club.id])
                redirect club
            }
            '*'{ respond club, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        clubService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'club.label', default: 'Club'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'club.label', default: 'Club'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
