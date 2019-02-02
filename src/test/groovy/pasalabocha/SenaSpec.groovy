package pasalabocha

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

import java.time.LocalDateTime;
import java.time.Duration;

class SenaSpec extends Specification implements DomainUnitTest<Sena> {
    Club club
    Cancha cancha
    LocalDateTime ahora
    Turno turno
    Cliente cliente
    def setup() {
        club = new Club(nombre: "River Plate",
                email: "contacto@riverplate.com.ar",
                ubicacion: "Av. Pres. Figueroa Alcorta 7597, Nunez",
                nivelConfiabilidadNecesario: 10,
                porcentajeSena: 40,
                tiempoLimiteCancelacionReserva: Duration.ofHours(1),
                tiempoLimitePagoDeSena: Duration.ofHours(1),
                username: 'river',
                password: '1234')
        cancha = new Cancha(numeroDeCancha: 1,
                dimensiones: new Dimensiones(ancho: "70", largo: "105"),
                tipoSuelo: TipoSuelo.CESPED,
                poseeIluminacion: true,
                cantidadJugadores: 22)
        club.agregarCancha(cancha)
        ahora = LocalDateTime.parse("2019-01-01T10:00:00")
        LocalDateTime primeroEnero12hs = LocalDateTime.parse("2019-01-01T12:00:00")
        Duration unaHora = Duration.ofHours(1);
        turno = new Turno(
                fechaHorario: primeroEnero12hs,
                duracion: unaHora,
                precioBase: 500)
        cancha.agregarTurno(turno, ahora)
        cliente = new Cliente(
                    nombre: "jose",
                    apellido: "argento",
                    email: "jose.argento@gmail.com",
                    apodo: "pepe",
                    confiabilidad: new Confiabilidad(0),
                    username: 'pepe',
                    password: '1234')
    }

    def cleanup() {
    }

    void "Seña se encuentra impaga inicialmente"() {
        when: "El turno es reservado por el cliente no confiable ni habitual"
        turno.reservar(cliente, ahora)

        then:"La seña de la reserva se encuentra impaga"
        !turno.reserva.sena.pagada
    }

    void "Seña se puede pagar dentro del plazo de tiempo establecido"() {
        when: "Se quiere pagar una seña dentro del plazo"
        turno.reservar(cliente, ahora)
        turno.reserva.sena.pagar(ahora + Duration.ofMinutes(35))

        then:"La seña se encuentra paga"
        turno.reserva.sena.pagada
    }

    void "Seña NO se puede pagar fuera del plazo de tiempo establecido (lanza excepcion)"() {
        when: "Se quiere pagar una seña fuera del plazo"
        turno.reservar(cliente, ahora)
        turno.reserva.sena.pagar(ahora + Duration.ofMinutes(65))

        then:"Lanza excepcion"
        thrown(Exception)
        !turno.reserva.sena.pagada
    }

    void "Seña no se puede pagar dos veces (lanza excepcion)"() {
        when: "Se quiere pagar una seña dos veces dentro del plazo"
        turno.reservar(cliente, ahora)
        turno.reserva.sena.pagar(ahora + Duration.ofMinutes(15))
        turno.reserva.sena.pagar(ahora + Duration.ofMinutes(45))

        then:"Lanza excepcion"
        thrown(Exception)
        turno.reserva.sena.pagada
    }
}
