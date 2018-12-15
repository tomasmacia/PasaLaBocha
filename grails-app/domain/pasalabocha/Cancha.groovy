package pasalabocha

import java.util.Hashtable
import java.time.LocalDateTime

class Cancha {

    Dimensiones dimensiones
    TipoSuelo tipoSuelo
    Integer numeroDeCancha
    boolean poseeIluminacion
    Integer cantidadJugadores
    //Hashtable<LocalDateTime, Turno> turnos
    List<Turno> turnos
    // boolean aptoEspectador

    static embedded = ['dimensiones']

    static hasMany = [turnos: Turno]

    static belongsTo = [club: Club] // no existe la cancha si desaparece el Club

    static constraints = {
      numeroDeCancha nullable: false
    }

    static mapping = {
      //version false
      //id column: 'numeroDeCancha'
    }

    String toString(){
      "Cancha ${numeroDeCancha}"
    }

    def concretar(Turno turno){
      turno.concretar()
      this.removeFromTurnos(turno)
      //turno.delete(failOnError: true, flush: true)
      this.save(failOnError: true)
    }
}
