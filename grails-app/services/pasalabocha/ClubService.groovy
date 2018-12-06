package pasalabocha

import grails.gorm.transactions.Transactional

@Transactional
class ClubService {

    def get(Long clubId) {
        Club.get(clubId)
    }

    def registrarCancha(Long clubId, Cancha cancha) {
        Club club = get(clubId)
        club.addToCanchas(cancha)
    }

    def registrarCanchas(Long clubId, List<Cancha> canchas) {
        Club club = get(clubId)
        canchas.collect{ Cancha cancha ->
            club.addToCanchas(cancha)
        }
    }

    def eliminarCancha(Long clubId, Cancha cancha) {
        Club club = get(clubId)
        club.removeFromCanchas(cancha)
    }

    def eliminarCanchas(Long clubId, List<Cancha> canchas) {
        Club club = get(clubId)
        canchas.collect{ Cancha cancha ->
            club.removeFromCanchas(cancha)
        }
    }
}
