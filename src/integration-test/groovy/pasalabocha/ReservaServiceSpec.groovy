package pasalabocha

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ReservaServiceSpec extends Specification {

    ReservaService reservaService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Reserva(...).save(flush: true, failOnError: true)
        //new Reserva(...).save(flush: true, failOnError: true)
        //Reserva reserva = new Reserva(...).save(flush: true, failOnError: true)
        //new Reserva(...).save(flush: true, failOnError: true)
        //new Reserva(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //reserva.id
    }

    void "test get"() {
        setupData()

        expect:
        reservaService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Reserva> reservaList = reservaService.list(max: 2, offset: 2)

        then:
        reservaList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        reservaService.count() == 5
    }

    void "test delete"() {
        Long reservaId = setupData()

        expect:
        reservaService.count() == 5

        when:
        reservaService.delete(reservaId)
        sessionFactory.currentSession.flush()

        then:
        reservaService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Reserva reserva = new Reserva()
        reservaService.save(reserva)

        then:
        reserva.id != null
    }
}
