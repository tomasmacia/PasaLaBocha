package pasalabocha

import pasalabocha.login.*
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;

class BootStrap {

    ClubService clubService

    def init = { servletContext ->

      // initializing domain data
      Club clubUno = new Club([
              nombre: "River Plate",
              email: "contacto@riverplate.com.ar",
              ubicacion: "Av. Pres. Figueroa Alcorta 7597, Nunez",
              nivelConfiabilidadNecesario: 10,
              porcentajeSena: 40,
              tiempoLimiteCancelacionReserva: Duration.ofHours(1),
              tiempoLimitePagoDeSena: Duration.ofHours(2),
              username: 'river',
              password: '1234'
      ]).save(failOnError: true)

      Club clubDos = new Club([
              nombre: "Boca",
              email: "contacto@bocajuniors.com.ar",
              ubicacion:"Brandsen 805, La boca",
              nivelConfiabilidadNecesario: 10,
              porcentajeSena: 40,
              tiempoLimiteCancelacionReserva: Duration.ofHours(1),
              tiempoLimitePagoDeSena: Duration.ofHours(2),
              username: 'boca',
              password: '1234'
      ]).save(failOnError: true)

      Cancha canchaUno = new Cancha([
              dimensiones: new Dimensiones(ancho: "70", largo: "105"),
              tipoSuelo: TipoSuelo.CESPED,
              numeroDeCancha: 1,
              poseeIluminacion: true,
              cantidadJugadores: 22,
              club: clubUno,
      ]).save(failOnError: true)

      Cancha canchaDos = new Cancha([
              dimensiones: new Dimensiones(ancho: "35", largo: "65"),
              tipoSuelo: TipoSuelo.CESPED_SINTETICO,
              numeroDeCancha: 2,
              poseeIluminacion: true,
              cantidadJugadores: 14,
              club: clubUno,
      ]).save(failOnError: true)

      LocalDate hoy = LocalDate.now();
      LocalTime las12 = LocalTime.of(12,0,0);
      Duration unaHora = Duration.ofHours(1);
      def turno1 = new Turno([
              fecha: hoy,
              horario: las12,
              duracion: unaHora,
              precioBase: 500,
              cancha: canchaUno,
      ]).save(failOnError: true)

      LocalTime la1 = LocalTime.of(13,0,0);
      def turno2 = new Turno([
              fecha: hoy,
              horario: la1,
              duracion: unaHora,
              precioBase: 500,
              cancha: canchaUno,
      ]).save(failOnError: true)

//        clubService.registrarCanchas(clubUno.id, Arrays.asList(canchaUno, canchaDos))
      System.out.println(clubUno.canchas)

        // initializing login data
        def adminRole = new Role(authority: 'ROLE_ADMIN').save()
        def clubRole = new Role(authority: 'ROLE_CLUB').save()
        def clienteRole = new Role(authority: 'ROLE_CLIENTE').save()

        def testAdmin = new User(username: 'admin', password: 'admin').save()
        //def testClub = new User(username: 'river', password: '1234', club: clubUno).save()
        def testCliente = new Cliente(
          nombre: "jose",
          apellido: "argento",
          email: "jose.argento@gmail.com",
          apodo: "pepe",
          nivelConfiabilidad: 0,
          username: 'pepe',
          password: '1234').save()

        UserRole.create testAdmin, adminRole
        UserRole.create clubUno, clubRole
        UserRole.create clubDos, clubRole
        UserRole.create testCliente, clienteRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

    }
    def destroy = {
    }
}
