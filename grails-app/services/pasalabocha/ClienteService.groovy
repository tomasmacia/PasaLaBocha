package pasalabocha

import grails.gorm.services.Service
import grails.gorm.transactions.Transactional

@Service(Cliente)
abstract class ClienteService {

    protected abstract Cliente get(Serializable id)

    protected abstract List<Cliente> list(Map args)

    protected abstract Long count()

    protected abstract void delete(Serializable id)

    protected abstract Cliente save(Cliente cliente)

    void aumentarConfiabilidad(Cliente cliente) {
        cliente.confiabilidad = cliente.confiabilidad.aumentar()
    }

    void disminuirConfiabilidad(Cliente cliente) {
        cliente.confiabilidad = cliente.confiabilidad.disminuir()
    }
}
