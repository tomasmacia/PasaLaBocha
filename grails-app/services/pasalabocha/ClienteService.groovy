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
    void aumentarConfiabilidad(Long id) {
        Cliente cliente = get(id)
        println cliente
        cliente.confiabilidad.nivel
        cliente.confiabilidad.aumentar()
        cliente.confiabilidad.nivel
        cliente.save(failOnError: true)
    }

    @Transactional
    void disminuirConfiabilidad(Long id) {
        Cliente cliente = get(id)
        println cliente
        cliente.confiabilidad.nivel
        cliente.confiabilidad.disminuir()
        cliente.confiabilidad.nivel
        cliente.save(failOnError: true)
    }



}