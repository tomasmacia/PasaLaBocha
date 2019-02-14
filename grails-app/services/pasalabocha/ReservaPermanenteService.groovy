package pasalabocha

import grails.gorm.services.Service

@Service(ReservaPermanente)
interface ReservaPermanenteService {

    ReservaPermanente get(Serializable id)

    List<ReservaPermanente> list(Map args)

    Long count()

    void delete(Serializable id)

    ReservaPermanente save(ReservaPermanente reservaPermanente)

}