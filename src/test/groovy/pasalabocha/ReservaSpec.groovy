package pasalabocha

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

import java.time.LocalDateTime;
import java.time.Duration;

class ReservaSpec extends Specification implements DomainUnitTest<Reserva> {
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
                precioBase: new Dinero(BigDecimal.valueOf(500), Moneda.ARS))
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

    void "Al reservar el turno su reserva asocia a ese turno con el cliente"() {
        when: "El turno es reservado por el cliente"
        turno.reservar(cliente, ahora)

        then:"La reserva asocia al cliete y al turno"
        turno.reserva
        turno.reserva.cliente == cliente
        turno.reserva.turno == turno
    }

    void "Si el cliente que reserva no es confiable ni habitual se genera una seña"() {
        when: "El turno es reservado por el cliente no confiable ni habitual"
        turno.reservar(cliente, ahora)

        then:"La reserva tiene una seña"
        turno.reserva.sena
    }

    void "Si el cliente que reserva es confiable NO se genera una seña"() {
        when: "El turno es reservado por el cliente confiable"
        Cliente cliente2 = new Cliente(
                    nombre: "pepe",
                    apellido: "otro",
                    email: "otro.pepe@gmail.com",
                    apodo: "pepe",
                    confiabilidad: new Confiabilidad(15),
                    username: 'pepe',
                    password: 'password')
        turno.reservar(cliente2, ahora)

        then:"La reserva tno iene una seña"
        !(turno.reserva.sena)
    }

    void "Si el cliente que reserva es habitual NO se genera una seña"() {
        when: "El turno es reservado por el cliente habitual"
        club.agregarClienteHabitual(cliente)
        turno.reservar(cliente, ahora)

        then:"La reserva no tiene una seña"
        !(turno.reserva.sena)
    }
}
