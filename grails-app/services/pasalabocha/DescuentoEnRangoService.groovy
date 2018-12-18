package pasalabocha

import grails.gorm.services.Service

@Service(DescuentoEnRango)
interface DescuentoEnRangoService {

    DescuentoEnRango get(Serializable id)

    List<DescuentoEnRango> list(Map args)

    Long count()

    void delete(Serializable id)

    DescuentoEnRango save(DescuentoEnRango descuentoEnRango)

}