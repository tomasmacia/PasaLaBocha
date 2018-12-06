package pasalabocha

class CanchaTagLib {
    static namespace = "canchaTagLib"
    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def canchasDisonibles = { attrs ->
        ArrayList<Cancha> canchas =  (ArrayList<Cancha>) attrs.remove('canchas')
        Integer cantidadCanchas = canchas?.size()

//        if (cantidadCanchas > 0) {
//            String plural = cantidadCanchas == 1 ? "" : "s"
//            out << "Hay ${cantidadCanchas} cancha${plural} disponible${plural}"
//        } else {
//            out << "No hay canchas disponibles"
//        }

        if (cantidadCanchas > 1) {
            out << String.format("Hay %d canchas disponibles", cantidadCanchas)
        } else if(cantidadCanchas == 1) {
            out << "Hay 1 cancha disponible"
        } else {
            out << "No hay canchas disponibles"
        }

    }
}
