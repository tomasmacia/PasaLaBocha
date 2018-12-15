package pasalabocha

import grails.gorm.services.Service

@Service(Sena)
interface SenaService {

    Sena get(Serializable id)

    List<Sena> list(Map args)

    Long count()

    void delete(Serializable id)

    Sena save(Sena sena)

}