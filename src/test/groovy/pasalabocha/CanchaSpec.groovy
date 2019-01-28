package pasalabocha

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

import java.time.LocalDateTime;
import java.time.Duration;

class CanchaSpec extends Specification implements DomainUnitTest<Cancha> {
    Cancha cancha
    LocalDateTime ahora
    def setup() {
        cancha = new Cancha(numeroDeCancha: 1,
                        dimensiones: new Dimensiones(ancho: "70", largo: "105"),
                        tipoSuelo: TipoSuelo.CESPED,
                        poseeIluminacion: true,
                        cantidadJugadores: 22)
        ahora = LocalDateTime.parse("2019-01-01T10:00:00")
    }

    def cleanup() {
    }

    void "Agregar turno"() {
        when:"El turno es agregado"
        Duration unaHora = Duration.ofHours(1);
        LocalDateTime primeroEnero12hs = LocalDateTime.parse("2019-01-01T12:00:00")
        Turno turno = new Turno(fechaHorario: primeroEnero12hs,
                        duracion: unaHora,
                        precioBase: 500)
        cancha.agregarTurno(turno, ahora)

        then:"El turno pertenece a la cancha"
        turno in cancha.turnos
        turno.cancha == cancha
    }

    void "Agregar turno dos veces lanza excepcion"() {
        when:"El turno es agregado dos veces"
        Duration unaHora = Duration.ofHours(1);
        LocalDateTime primeroEnero12hs = LocalDateTime.parse("2019-01-01T12:00:00")
        Turno turno = new Turno(fechaHorario: primeroEnero12hs,
                        duracion: unaHora,
                        precioBase: 500)
        cancha.agregarTurno(turno, ahora)
        cancha.agregarTurno(turno, ahora)

        then:"El turno pertenece a la cancha"
        thrown(Exception)
        turno in cancha.turnos
        turno.cancha == cancha
    }

    void "Agregar dos turnos a la misma hora lanza excepcion"() {
        when:"Los dos turnos son agregados a la misma hora"
        Duration unaHora = Duration.ofHours(1);
        Duration dosHoras = Duration.ofHours(2);
        LocalDateTime primeroEnero12hs = LocalDateTime.parse("2019-01-01T12:00:00")
        Turno turno1 = new Turno(fechaHorario: primeroEnero12hs,
                        duracion: unaHora,
                        precioBase: 500)
        Turno turno2 = new Turno(fechaHorario: primeroEnero12hs,
                        duracion: dosHoras,
                        precioBase: 900)
        cancha.agregarTurno(turno1, ahora)
        cancha.agregarTurno(turno2, ahora)

        then:"El turno1 pertenece a la cancha pero el turno2 lanzó excepcion"
        thrown(Exception)
        turno1 in cancha.turnos
        turno1.cancha == cancha
        !(turno2.cancha)
        !(turno2 in cancha.turnos)
    }

    void "Agregar dos turnos que se superponen lanza excepcion"() {
        when:"Se agregan dos turnos que se superponen"
        Duration unaHora = Duration.ofHours(1);
        Duration dosHoras = Duration.ofHours(2);
        LocalDateTime primeroEnero12hs = LocalDateTime.parse("2019-01-01T12:00:00")
        LocalDateTime primeroEnero11hs = LocalDateTime.parse("2019-01-01T11:00:00")
        Turno turno1 = new Turno(fechaHorario: primeroEnero12hs,
                        duracion: unaHora,
                        precioBase: 500)
        Turno turno2 = new Turno(fechaHorario: primeroEnero11hs,
                        duracion: dosHoras,
                        precioBase: 900)
        cancha.agregarTurno(turno1, ahora)
        cancha.agregarTurno(turno2, ahora)

        then:"El turno1 pertenece a la cancha pero el turno2 lanzó excepcion"
        thrown(Exception)
        turno1 in cancha.turnos
        turno1.cancha == cancha
        !(turno2.cancha)
        !(turno2 in cancha.turnos)
    }

    void "Agregar turno en el pasado lanza excepcion"() {
        when:"Se agrega un turno cuya fecha y horario ya pasó"
        Duration unaHora = Duration.ofHours(1);
        LocalDateTime primeroEnero9hs = LocalDateTime.parse("2019-01-01T09:00:00")
        Turno turno = new Turno(fechaHorario: primeroEnero9hs,
                        duracion: unaHora,
                        precioBase: 500)
        cancha.agregarTurno(turno, ahora)

        then:"Se lanza excepcion"
        thrown(Exception)
        !(turno.cancha)
        !(turno in cancha.turnos)
    }

    void "Eliminar turno no reservado"() {
        when:"El turno es agregado y luego eliminado"
        Duration unaHora = Duration.ofHours(1);
        LocalDateTime primeroEnero12hs = LocalDateTime.parse("2019-01-01T12:00:00")
        Turno turno = new Turno(fechaHorario: primeroEnero12hs,
                        duracion: unaHora,
                        precioBase: 500)
        cancha.agregarTurno(turno, ahora)
        cancha.eliminarTurno(turno)

        then:"El turno no existe en la cancha"
        !(turno in cancha.turnos)
        cancha.turnos.size() == 0
        !(turno.cancha)
    }

    void "Eliminar turno que no existia lanza excepción"() {
        when:"Un turno que no estaba agregado es eliminado"
        Duration unaHora = Duration.ofHours(1);
        LocalDateTime primeroEnero12hs = LocalDateTime.parse("2019-01-01T12:00:00")
        LocalDateTime primeroEnero13hs = LocalDateTime.parse("2019-01-01T13:00:00")
        Turno turno1 = new Turno(fechaHorario: primeroEnero13hs,
                        duracion: unaHora,
                        precioBase: 500)
        Turno turno2 = new Turno(fechaHorario: primeroEnero12hs,
                    duracion: unaHora,
                    precioBase: 500)
        cancha.agregarTurno(turno1, ahora)
        cancha.eliminarTurno(turno2)

        then:"Lanza excepcion"
        turno1 in cancha.turnos
        turno1.cancha == cancha
        !(turno2 in cancha.turnos)
        !(turno2.cancha)
        cancha.turnos.size() == 1
        thrown(Exception)
    }

    void "Eliminar turno reservado lanza excepcion"() {
        when:"Un turno reservado es eliminado"
        def club = new Club([
                nombre: "River Plate",
                email: "contacto@riverplate.com.ar",
                ubicacion: "Av. Pres. Figueroa Alcorta 7597, Nunez",
                nivelConfiabilidadNecesario: 10,
                porcentajeSena: 40,
                tiempoLimiteCancelacionReserva: Duration.ofHours(1),
                tiempoLimitePagoDeSena: Duration.ofSeconds(10),
                username: 'river',
                password: '1234'])
        club.agregarCancha(cancha)
        Duration unaHora = Duration.ofHours(1);
        LocalDateTime primeroEnero13hs = LocalDateTime.parse("2019-01-01T13:00:00")
        Turno turno = new Turno(fechaHorario: primeroEnero13hs,
                        duracion: unaHora,
                        precioBase: 500)
        cancha.agregarTurno(turno, ahora)
        turno.reservar()
        cancha.eliminarTurno(turno)

        then:"Lanza excepcion"
        turno in cancha.turnos
        turno.cancha == cancha
        thrown(Exception)
    }
}
