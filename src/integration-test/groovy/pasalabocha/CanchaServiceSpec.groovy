package pasalabocha

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CanchaServiceSpec extends Specification {

    CanchaService canchaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Cancha(...).save(flush: true, failOnError: true)
        //new Cancha(...).save(flush: true, failOnError: true)
        //Cancha cancha = new Cancha(...).save(flush: true, failOnError: true)
        //new Cancha(...).save(flush: true, failOnError: true)
        //new Cancha(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //cancha.id
    }

    void "test get"() {
        setupData()

        expect:
        canchaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Cancha> canchaList = canchaService.list(max: 2, offset: 2)

        then:
        canchaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        canchaService.count() == 5
    }

    void "test delete"() {
        Long canchaId = setupData()

        expect:
        canchaService.count() == 5

        when:
        canchaService.delete(canchaId)
        sessionFactory.currentSession.flush()

        then:
        canchaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Cancha cancha = new Cancha()
        canchaService.save(cancha)

        then:
        cancha.id != null
    }
}
