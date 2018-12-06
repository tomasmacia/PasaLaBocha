package pasalabocha

import grails.gorm.transactions.Transactional

@Transactional
class CanchaService {

    Cancha get(Long canchaId) {
        Cancha.get(canchaId)
    }
}
