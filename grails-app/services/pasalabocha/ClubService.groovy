package pasalabocha

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

@Service(Club)
abstract class ClubService {

    protected abstract Club get(Serializable id)

    protected abstract List<Club> list(Map args)

    protected abstract Long count()

    protected abstract void delete(Serializable id)

    protected abstract Club save(Club club)

    @Transactional
    def agregarClienteHabitual(Club club, Cliente cliente){
      club.addToClientesHabituales(cliente)
    }

    @Transactional
    def eliminarClienteHabitual(Club club, Cliente cliente){
      club.removeFromClientesHabituales(cliente)
    }

}
