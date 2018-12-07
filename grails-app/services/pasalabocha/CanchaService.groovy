package pasalabocha

import grails.gorm.services.Service

@Service(Cancha)
interface CanchaService {

    Cancha get(Serializable id)

    List<Cancha> list(Map args)

    Long count()

    void delete(Serializable id)

    Cancha save(Cancha cancha)

}