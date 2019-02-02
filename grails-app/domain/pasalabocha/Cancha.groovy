package pasalabocha

import java.util.Hashtable
import java.time.LocalDateTime

class Cancha {

    Dimensiones dimensiones
    TipoSuelo tipoSuelo
    Integer numeroDeCancha
    boolean poseeIluminacion
    Integer cantidadJugadores
    Set<Turno> turnos
    // boolean aptoEspectador

    static embedded = ['dimensiones']

    static hasMany = [turnos: Turno]

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
}
