package pasalabocha

class Club {
    String nombre
    String email
    String password
    String ubicacion
    Integer nivelConfiabilidadNecesario
    Integer porcentajeSena
    Integer tiempoLimiteCancelacionReserva
    List<Cancha> canchas

    static hasMany = [canchas: Cancha]

    static constraints = {
    }
}
