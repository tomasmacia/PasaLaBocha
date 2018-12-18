package pasalabocha

import grails.gorm.services.Service

@Service(DescuentoPorHorasRestantes)
interface DescuentoPorHorasRestantesService {

    DescuentoPorHorasRestantes get(Serializable id)

    List<DescuentoPorHorasRestantes> list(Map args)

    Long count()

    void delete(Serializable id)

    DescuentoPorHorasRestantes save(DescuentoPorHorasRestantes descuentoPorHorasRestantes)

}