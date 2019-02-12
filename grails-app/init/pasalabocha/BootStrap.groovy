package pasalabocha

import pasalabocha.login.*
import java.time.LocalDate
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Duration;

class BootStrap {

    ClubService clubService

    def init = { servletContext ->

        // initializing domain data
        Club river = new Club(
            nombre: "River Plate",
            email: "contacto@riverplate.com.ar",
            ubicacion: "Av. Pres. Figueroa Alcorta 7597, Nunez",
            nivelConfiabilidadNecesario: 10,
            porcentajeSena: 40,
            tiempoLimiteCancelacionReserva: Duration.ofHours(1),
            tiempoLimitePagoDeSena: Duration.ofMinutes(30),
            username: 'river',
            password: '1234').save(failOnError: true)

        Club boca = new Club(
            nombre: "Boca",
            email: "contacto@bocajuniors.com.ar",
            ubicacion:"Brandsen 805, La boca",
            nivelConfiabilidadNecesario: 10,
            porcentajeSena: 40,
            tiempoLimiteCancelacionReserva: Duration.ofHours(1),
            tiempoLimitePagoDeSena: Duration.ofHours(2),
            username: 'boca',
            password: '1234').save(failOnError: true)

        Cancha canchaUnoRiver = new Cancha(
            dimensiones: new Dimensiones(ancho: "70", largo: "105"),
            tipoSuelo: TipoSuelo.CESPED,
            numeroDeCancha: 1,
            poseeIluminacion: true,
            cantidadJugadores: 22)

        river.agregarCancha(canchaUnoRiver)
        canchaUnoRiver.save(failOnError: true)

        Cancha canchaDosRiver = new Cancha(
              dimensiones: new Dimensiones(ancho: "35", largo: "65"),
              tipoSuelo: TipoSuelo.CESPED_SINTETICO,
              numeroDeCancha: 2,
              poseeIluminacion: true,
              cantidadJugadores: 14)
        river.agregarCancha(canchaDosRiver)
        canchaDosRiver.save(failOnError: true)

        Cancha canchaUnoBoca = new Cancha(
              dimensiones: new Dimensiones(ancho: "35", largo: "65"),
              tipoSuelo: TipoSuelo.CESPED_SINTETICO,
              numeroDeCancha: 1,
              poseeIluminacion: true,
              cantidadJugadores: 14)
        boca.agregarCancha(canchaUnoBoca)
        canchaUnoBoca.save(failOnError: true)

        LocalDateTime ahora = LocalDateTime.now()
        Duration unaHora = Duration.ofHours(1);
        LocalDateTime mañanaALas10hs = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(10, 0))
        LocalDateTime mañanaALas12hs = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(12, 0))
        def turno1 = new Turno(fechaHorario: mañanaALas10hs,
              duracion: unaHora,
              precioBase: new Dinero(BigDecimal.valueOf(500), Moneda.ARS))
        canchaUnoRiver.agregarTurno(turno1, ahora)
        turno1.save(failOnError: true)

        LocalTime la1 = LocalTime.of(13,0,0);
        def turno2 = new Turno(fechaHorario: mañanaALas12hs,
              duracion: unaHora,
              precioBase: new Dinero(BigDecimal.valueOf(500), Moneda.ARS))
        canchaUnoRiver.agregarTurno(turno2, ahora)
        turno2.save(failOnError: true)

        def turno3 = new Turno(fechaHorario: mañanaALas12hs,
              duracion: unaHora,
              precioBase: new Dinero(BigDecimal.valueOf(400), Moneda.ARS))
        canchaUnoBoca.agregarTurno(turno3, ahora)
        turno3.save(failOnError: true)

        // initializing login data
        def adminRole = new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
        def clubRole = new Role(authority: 'ROLE_CLUB').save(failOnError: true)
        def clienteRole = new Role(authority: 'ROLE_CLIENTE').save(failOnError: true)

        def testAdmin = new User(username: 'admin', password: 'admin').save(failOnError: true)
        def testCliente = new Cliente(
            nombre: "jose",
            apellido: "argento",
            email: "jose.argento@gmail.com",
            apodo: "pepe",
            confiabilidad: new Confiabilidad(0),
            username: 'pepe',
            password: '1234').save(failOnError: true)

        UserRole.create testAdmin, adminRole
        UserRole.create river, clubRole
        UserRole.create boca, clubRole
        UserRole.create testCliente, clienteRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }
    }
    def destroy = {
    }
}
