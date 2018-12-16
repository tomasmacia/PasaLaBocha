package pasalabocha

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

@Service(Sena)
abstract class SenaService {

    protected abstract Sena get(Serializable id)

    protected abstract List<Sena> list(Map args)

    protected abstract Long count()

    protected abstract void delete(Serializable id)

    protected abstract Sena save(Sena sena)

    @Transactional
    void pagar(Sena sena){
      sena.pagada = true
      save(sena)
    }

}
