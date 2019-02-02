package pasalabocha

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

import java.time.LocalDateTime;
import java.time.Duration;

class TurnoSpec extends Specification implements DomainUnitTest<Turno> {
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

    void "Turno recien creado no esta reservado"() {
        expect:
        !(turno.estaReservado())
    }

    void "Luego de reservar el turno el mismo se encuentra reservado"(){
        when:"El turno es reservado"
        turno.reservar(cliente, ahora)

        then:"El turno esta reservado"
        turno.estaReservado()
    }

    void "Reservar un turno dos veces lanza excepcion"(){
        when:"El turno es reservado dos veces"
        turno.reservar(cliente, ahora)
        Cliente cliente2 = new Cliente(
                    nombre: "pepe",
                    apellido: "otro",
                    email: "otro.pepe@gmail.com",
                    apodo: "pepe",
                    confiabilidad: new Confiabilidad(1),
                    username: 'pepe',
                    password: 'password')
        turno.reservar(cliente2)

        then:"Se lanza excepcion"
        turno.estaReservado()
        thrown(Exception)
    }
}
