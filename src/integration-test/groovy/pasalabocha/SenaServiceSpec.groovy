package pasalabocha

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class SenaServiceSpec extends Specification {

    SenaService senaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Sena(...).save(flush: true, failOnError: true)
        //new Sena(...).save(flush: true, failOnError: true)
        //Sena sena = new Sena(...).save(flush: true, failOnError: true)
        //new Sena(...).save(flush: true, failOnError: true)
        //new Sena(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //sena.id
    }

    void "test get"() {
        setupData()

        expect:
        senaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Sena> senaList = senaService.list(max: 2, offset: 2)

        then:
        senaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        senaService.count() == 5
    }

    void "test delete"() {
        Long senaId = setupData()

        expect:
        senaService.count() == 5

        when:
        senaService.delete(senaId)
        sessionFactory.currentSession.flush()

        then:
        senaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Sena sena = new Sena()
        senaService.save(sena)

        then:
        sena.id != null
    }
}
