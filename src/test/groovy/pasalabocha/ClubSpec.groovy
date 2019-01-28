package pasalabocha

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification
import java.time.Duration

class ClubSpec extends Specification implements DomainUnitTest<Club> {
    Club club
    def setup() {
        club = new Club([
                nombre: "River Plate",
                email: "contacto@riverplate.com.ar",
                ubicacion: "Av. Pres. Figueroa Alcorta 7597, Nunez",
                nivelConfiabilidadNecesario: 10,
                porcentajeSena: 40,
                tiempoLimiteCancelacionReserva: Duration.ofHours(1),
                tiempoLimitePagoDeSena: Duration.ofSeconds(10),
                username: 'river',
                password: '1234'])
    }

    def cleanup() {
    }

    void "Agregar cancha"() {
        when:"La cancha es creada"
        Cancha cancha = new Cancha([numeroDeCancha: 1,
                        dimensiones: new Dimensiones(ancho: "70", largo: "105"),
                        tipoSuelo: TipoSuelo.CESPED,
                        poseeIluminacion: true,
                        cantidadJugadores: 22,])
        club.agregarCancha(cancha)

        then:"La cancha pertenece al club"
        cancha in club.canchas
    }

    void "Agregar dos canchas con distinto numero de cancha"(){
        when: "Las canchas son agregadas"
        Cancha cancha1 = new Cancha([numeroDeCancha: 1,
                        dimensiones: new Dimensiones(ancho: "70", largo: "105"),
                        tipoSuelo: TipoSuelo.CESPED,
                        poseeIluminacion: true,
                        cantidadJugadores: 22,])
        club.agregarCancha(cancha1)
        Cancha cancha2 = new Cancha([numeroDeCancha: 2,
                        dimensiones: new Dimensiones(ancho: "70", largo: "105"),
                        tipoSuelo: TipoSuelo.CESPED,
                        poseeIluminacion: true,
                        cantidadJugadores: 22,])
        club.agregarCancha(cancha2)

        then: "Ambas canchas pertenecen al club"
        cancha1 in club.canchas
        cancha2 in club.canchas
    }

    void "Agregar dos canchas con mismo numero de cancha falla"(){
        when: "Las canchas son agregadas"
        Cancha cancha1 = new Cancha([numeroDeCancha: 1,
                        dimensiones: new Dimensiones(ancho: "70", largo: "105"),
                        tipoSuelo: TipoSuelo.CESPED,
                        poseeIluminacion: true,
                        cantidadJugadores: 22,])
        club.agregarCancha(cancha1)
        Cancha cancha2 = new Cancha([numeroDeCancha: 1,
                        dimensiones: new Dimensiones(ancho: "70", largo: "105"),
                        tipoSuelo: TipoSuelo.CESPED,
                        poseeIluminacion: true,
                        cantidadJugadores: 22,])
        club.agregarCancha(cancha2)

        then: "Se lanza exception"
        thrown(Exception)
    }

    void "Agregar cliente habitual"() {
        when:"El cliente es creada"
        Cliente cliente = new Cliente(
                    nombre: "jose",
                    apellido: "argento",
                    email: "jose.argento@gmail.com",
                    apodo: "pepe",
                    confiabilidad: new Confiabilidad(0),
                    username: 'pepe',
                    password: '1234')
        club.agregarClienteHabitual(cliente)

        then:"La cliente es habitual en el club"
        cliente in club.clientesHabituales
    }

    void "Agregar dos veces el mismo cliente habitual"() {
        when:"El cliente es creada"
        Cliente cliente = new Cliente(
                    nombre: "jose",
                    apellido: "argento",
                    email: "jose.argento@gmail.com",
                    apodo: "pepe",
                    confiabilidad: new Confiabilidad(0),
                    username: 'pepe',
                    password: '1234')
        club.agregarClienteHabitual(cliente)
        club.agregarClienteHabitual(cliente)

        then:"La cliente es habitual en el club solo una vez"
        thrown(Exception)
        cliente in club.clientesHabituales
        club.clientesHabituales.size() == 1
    }

    void "Agregar dos clientes con el mismo username"() {
        when:"Los clientes son creados"
        Cliente cliente1 = new Cliente(
                    nombre: "jose",
                    apellido: "argento",
                    email: "jose.argento@gmail.com",
                    apodo: "pepe",
                    confiabilidad: new Confiabilidad(0),
                    username: 'pepe',
                    password: '1234')

        Cliente cliente2 = new Cliente(
                    nombre: "pepe",
                    apellido: "otro",
                    email: "otro.pepe@gmail.com",
                    apodo: "pepe",
                    confiabilidad: new Confiabilidad(1),
                    username: 'pepe',
                    password: 'password')
        club.agregarClienteHabitual(cliente1)
        club.agregarClienteHabitual(cliente2)

        then:"Solo el primer cliente con ese username fue agregado"
        thrown(Exception)
        cliente1 in club.clientesHabituales
        club.clientesHabituales.size() == 1
    }

    void "Eliminar cliente habitual"() {
        when:"El cliente es eliminado"
        Cliente cliente = new Cliente(
                    nombre: "jose",
                    apellido: "argento",
                    email: "jose.argento@gmail.com",
                    apodo: "pepe",
                    confiabilidad: new Confiabilidad(0),
                    username: 'pepe',
                    password: '1234')
        club.agregarClienteHabitual(cliente)
        club.eliminarClienteHabitual(cliente)

        then:"La cliente ya no es habitual en el club"
        !(cliente in club.clientesHabituales)
        club.clientesHabituales.size() == 0
    }

    void "Eliminar cliente habitual que no lo era"() {
        when:"El cliente que no era habitual es eliminado"
        Cliente cliente1 = new Cliente(
                    nombre: "jose",
                    apellido: "argento",
                    email: "jose.argento@gmail.com",
                    apodo: "pepe",
                    confiabilidad: new Confiabilidad(0),
                    username: 'pepe',
                    password: '1234')
        Cliente cliente2 = new Cliente(
                    nombre: "otro",
                    apellido: "otro",
                    email: "otro@gmail.com",
                    apodo: "otro",
                    confiabilidad: new Confiabilidad(0),
                    username: 'otro',
                    password: '1234')
        club.agregarClienteHabitual(cliente1)
        club.eliminarClienteHabitual(cliente2)

        then:"Lanza excepcion"
        cliente1 in club.clientesHabituales
        !(cliente2 in club.clientesHabituales)
        club.clientesHabituales.size() == 1
        thrown(Exception)
    }

}
