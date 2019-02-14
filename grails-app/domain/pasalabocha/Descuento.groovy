package pasalabocha

class Descuento {
    Integer porcentaje
    Integer nivelConfiabilidadNecesario

    static constraints = {
        porcentaje min: 0, max: 100, nullable: false, blank: false
        porcentaje min: 0, nullable: false, blank: false
    }

    static belongsTo = [club: Club]

    Dinero aplicarA(Dinero precioBase){
        return precioBase * (1 - this.porcentaje / 100) // times en Dinero
    }

    boolean turnoAplica(Turno turno) {
        false
    }

}
