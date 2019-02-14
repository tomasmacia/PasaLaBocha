package pasalabocha

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDateTime

@Service(DescuentoPorHorasRestantes)
abstract class DescuentoPorHorasRestantesService {

    abstract DescuentoPorHorasRestantes get(Serializable id)

    abstract List<DescuentoPorHorasRestantes> list(Map args)

    abstract Long count()

    abstract void delete(Serializable id)

    abstract DescuentoPorHorasRestantes save(DescuentoPorHorasRestantes descuentoPorHorasRestantes)

}