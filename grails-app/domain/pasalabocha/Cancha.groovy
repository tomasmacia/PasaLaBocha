package pasalabocha

import java.time.LocalDateTime

class Cancha implements Comparable<Cancha> {

    Dimensiones dimensiones
    TipoSuelo tipoSuelo
    Integer numeroDeCancha
    boolean poseeIluminacion
    Integer cantidadJugadores
    SortedSet<Turno> turnos
    Set<ReservaPermanente> reservasPermanentes
    // boolean aptoEspectador

    static embedded = ['dimensiones']

    static hasMany = [turnos: Turno, reservasPermanentes: ReservaPermanente]

    static belongsTo = [club: Club] // no existe la cancha si desaparece el Club

    static constraints = {
      numeroDeCancha nullable: false
    }

    String toString(){
      "Cancha ${numeroDeCancha}"
    }

    def agregarTurno(Turno turno, LocalDateTime ahora) {
        if (turno.esAntesDe(ahora)){
            throw new Exception("El turno que intentas agregar ya pasó")
        }
        if (turnos){
            def superpuestos = turnos.findAll {it.seSuperpone(turno)}
            if (superpuestos){
                throw new Exception("El turno que intentas agregar se superpone con otro")
            }
        }
        this.addToTurnos(turno)
        if (reservasPermanentes){
            ReservaPermanente reservaPermanente = reservasPermanentes.find {it.aplicaA(turno)}
            if (reservaPermanente){
                Reserva reserva = turno.reservar(reservaPermanente.cliente, LocalDateTime.now())
                return reserva
            }
        }
    }

    def eliminarTurno(Turno turno){
        if (turno.cancha != this){
            throw new Exception("El turno que intenta eliminar no pertenece a esta cancha")
        }
        if (!(turno.estaReservado())){
            this.removeFromTurnos(turno)
        } else {
           throw new Exception("El turno que intenta eliminar ya se encuentra reservado")
        }
    }

    def agregarReservaPermanente(ReservaPermanente reservaPermanente){
        if (reservasPermanentes){
            def superpuestas = reservasPermanentes.findAll {it.seSuperpone(reservaPermanente)}
            if (superpuestas){
                throw new Exception("Ya existe una reserva permanente que se superpone con esta")
            }
        } else if (!this.club.esHabitual(reservaPermanente.cliente)){
            throw new Exception("El cliente no es habitual en este club")
        }
        this.addToReservasPermanentes(reservaPermanente)
        if (turnos){
            def reservas = reservaPermanente.aplicarA(turnos)
            return reservas
        }

    }

    def eliminarReservaPermante(ReservaPermanente reservaPermanente){
        /* TODO*/
    }

    @Override
    int compareTo(Cancha o) {
        return this.numeroDeCancha <=> o.numeroDeCancha
    }
}
