package pasalabocha

class BootStrap {

    ClubService clubService

    def init = { servletContext ->
        // initializing data
        Club clubUno = new Club([
                nombre: "River Plate",
                email: "contacto@riverplate.com.ar",
                password: "elmasgrande",
                ubicacion: "Nunez",
                nivelConfiabilidadNecesario: 10,
                porcentajeSena: 40,
                tiempoLimiteCancelacionReserva: 1,
        ]).save(failOnError: true)

        Club clubDos = new Club([
                nombre: "Boca",
                email: "contacto@bocajuniors.com.ar",
                password: "lamitadmasuno",
                ubicacion:"Boca",
                nivelConfiabilidadNecesario: 10,
                porcentajeSena: 40,
                tiempoLimiteCancelacionReserva: 1,
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

//        clubService.registrarCanchas(clubUno.id, Arrays.asList(canchaUno, canchaDos))
        System.out.println(clubUno.canchas)


    }
    def destroy = {
    }
}
