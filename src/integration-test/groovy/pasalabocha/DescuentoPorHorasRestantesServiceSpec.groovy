package pasalabocha

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DescuentoPorHorasRestantesServiceSpec extends Specification {

    DescuentoPorHorasRestantesService descuentoPorHorasRestantesService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new DescuentoPorHorasRestantes(...).save(flush: true, failOnError: true)
        //new DescuentoPorHorasRestantes(...).save(flush: true, failOnError: true)
        //DescuentoPorHorasRestantes descuentoPorHorasRestantes = new DescuentoPorHorasRestantes(...).save(flush: true, failOnError: true)
        //new DescuentoPorHorasRestantes(...).save(flush: true, failOnError: true)
        //new DescuentoPorHorasRestantes(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //descuentoPorHorasRestantes.id
    }

    void "test get"() {
        setupData()

        expect:
        descuentoPorHorasRestantesService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<DescuentoPorHorasRestantes> descuentoPorHorasRestantesList = descuentoPorHorasRestantesService.list(max: 2, offset: 2)

        then:
        descuentoPorHorasRestantesList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        descuentoPorHorasRestantesService.count() == 5
    }

    void "test delete"() {
        Long descuentoPorHorasRestantesId = setupData()

        expect:
        descuentoPorHorasRestantesService.count() == 5

        when:
        descuentoPorHorasRestantesService.delete(descuentoPorHorasRestantesId)
        sessionFactory.currentSession.flush()

        then:
        descuentoPorHorasRestantesService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        DescuentoPorHorasRestantes descuentoPorHorasRestantes = new DescuentoPorHorasRestantes()
        descuentoPorHorasRestantesService.save(descuentoPorHorasRestantes)

        then:
        descuentoPorHorasRestantes.id != null
    }
}
