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

    @Transactional
    void aumentarConfiabilidad(Long id) { //deberia ser todo un service que confirme la asistencia
        Cliente cliente = get(id)
        cliente.confiabilidad = cliente.confiabilidad.aumentar()
        cliente.save(failOnError: true, flush: true)
    }

    @Transactional
    void disminuirConfiabilidad(Long id) {
        Cliente cliente = get(id)
        cliente.confiabilidad = cliente.confiabilidad.disminuir()
        cliente.save(failOnError: true, flush: true)
    }



}
