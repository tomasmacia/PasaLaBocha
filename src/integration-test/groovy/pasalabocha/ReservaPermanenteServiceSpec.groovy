package pasalabocha

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ReservaPermanenteServiceSpec extends Specification {

    ReservaPermanenteService reservaPermanenteService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ReservaPermanente(...).save(flush: true, failOnError: true)
        //new ReservaPermanente(...).save(flush: true, failOnError: true)
        //ReservaPermanente reservaPermanente = new ReservaPermanente(...).save(flush: true, failOnError: true)
        //new ReservaPermanente(...).save(flush: true, failOnError: true)
        //new ReservaPermanente(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //reservaPermanente.id
    }

    void "test get"() {
        setupData()

        expect:
        reservaPermanenteService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ReservaPermanente> reservaPermanenteList = reservaPermanenteService.list(max: 2, offset: 2)

        then:
        reservaPermanenteList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        reservaPermanenteService.count() == 5
    }

    void "test delete"() {
        Long reservaPermanenteId = setupData()

        expect:
        reservaPermanenteService.count() == 5

        when:
        reservaPermanenteService.delete(reservaPermanenteId)
        sessionFactory.currentSession.flush()

        then:
        reservaPermanenteService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ReservaPermanente reservaPermanente = new ReservaPermanente()
        reservaPermanenteService.save(reservaPermanente)

        then:
        reservaPermanente.id != null
    }
}
