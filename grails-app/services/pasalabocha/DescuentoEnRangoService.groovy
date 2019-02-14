package pasalabocha

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
import org.springframework.beans.factory.annotation.Autowired

@Service(DescuentoEnRango)
abstract class DescuentoEnRangoService {

    abstract DescuentoEnRango get(Serializable id)

    abstract List<DescuentoEnRango> list(Map args)

    abstract Long count()

    abstract void delete(Serializable id)

    abstract DescuentoEnRango save(DescuentoEnRango descuentoEnRango)


}