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
    def agregarClienteHabitual(Long id, Cliente cliente){
      Club club = get(id)
      club.addToClientesHabituales(cliente)
    }

    @Transactional
    def eliminarClienteHabitual(Long id, Cliente cliente){
      Club club = get(id)
      club.removeFromClientesHabituales(cliente)
    }

}
