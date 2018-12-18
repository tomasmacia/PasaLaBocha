package pasalabocha

import grails.gorm.services.Service

@Service(DescuentoPorTurnoAnterior)
interface DescuentoPorTurnoAnteriorService {

    DescuentoPorTurnoAnterior get(Serializable id)

    List<DescuentoPorTurnoAnterior> list(Map args)

    Long count()

    void delete(Serializable id)

    DescuentoPorTurnoAnterior save(DescuentoPorTurnoAnterior descuentoPorTurnoAnterior)

}