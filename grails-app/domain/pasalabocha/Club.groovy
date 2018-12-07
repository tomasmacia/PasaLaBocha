package pasalabocha

class Club {
    String nombre
    String email
    String ubicacion
    Integer nivelConfiabilidadNecesario
    Integer porcentajeSena
    Integer tiempoLimiteCancelacionReserva
    List<Cancha> canchas

    static hasMany = [canchas: Cancha]

    static constraints = {
      nombre nullable: false, blank: false
      canchas minSize: 0
    }
}
