package pasalabocha

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class DescuentoEnRangoServiceSpec extends Specification {

    DescuentoEnRangoService descuentoEnRangoService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new DescuentoEnRango(...).save(flush: true, failOnError: true)
        //new DescuentoEnRango(...).save(flush: true, failOnError: true)
        //DescuentoEnRango descuentoEnRango = new DescuentoEnRango(...).save(flush: true, failOnError: true)
        //new DescuentoEnRango(...).save(flush: true, failOnError: true)
        //new DescuentoEnRango(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //descuentoEnRango.id
    }

    void "test get"() {
        setupData()

        expect:
        descuentoEnRangoService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<DescuentoEnRango> descuentoEnRangoList = descuentoEnRangoService.list(max: 2, offset: 2)

        then:
        descuentoEnRangoList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        descuentoEnRangoService.count() == 5
    }

    void "test delete"() {
        Long descuentoEnRangoId = setupData()

        expect:
        descuentoEnRangoService.count() == 5

        when:
        descuentoEnRangoService.delete(descuentoEnRangoId)
        sessionFactory.currentSession.flush()

        then:
        descuentoEnRangoService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        DescuentoEnRango descuentoEnRango = new DescuentoEnRango()
        descuentoEnRangoService.save(descuentoEnRango)

        then:
        descuentoEnRango.id != null
    }
}
