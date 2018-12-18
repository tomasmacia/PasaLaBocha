package pasalabocha

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DescuentoPorTurnoAnteriorServiceSpec extends Specification {

    DescuentoPorTurnoAnteriorService descuentoPorTurnoAnteriorService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new DescuentoPorTurnoAnterior(...).save(flush: true, failOnError: true)
        //new DescuentoPorTurnoAnterior(...).save(flush: true, failOnError: true)
        //DescuentoPorTurnoAnterior descuentoPorTurnoAnterior = new DescuentoPorTurnoAnterior(...).save(flush: true, failOnError: true)
        //new DescuentoPorTurnoAnterior(...).save(flush: true, failOnError: true)
        //new DescuentoPorTurnoAnterior(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //descuentoPorTurnoAnterior.id
    }

    void "test get"() {
        setupData()

        expect:
        descuentoPorTurnoAnteriorService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<DescuentoPorTurnoAnterior> descuentoPorTurnoAnteriorList = descuentoPorTurnoAnteriorService.list(max: 2, offset: 2)

        then:
        descuentoPorTurnoAnteriorList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        descuentoPorTurnoAnteriorService.count() == 5
    }

    void "test delete"() {
        Long descuentoPorTurnoAnteriorId = setupData()

        expect:
        descuentoPorTurnoAnteriorService.count() == 5

        when:
        descuentoPorTurnoAnteriorService.delete(descuentoPorTurnoAnteriorId)
        sessionFactory.currentSession.flush()

        then:
        descuentoPorTurnoAnteriorService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        DescuentoPorTurnoAnterior descuentoPorTurnoAnterior = new DescuentoPorTurnoAnterior()
        descuentoPorTurnoAnteriorService.save(descuentoPorTurnoAnterior)

        then:
        descuentoPorTurnoAnterior.id != null
    }
}
