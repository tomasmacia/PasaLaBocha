package pasalabocha

class DescuentoPorHorasRestantesJob {

    DescuentoService descuentoService

    static triggers = {
        cron name: 'descuentoHorasRestantesTrigger', cronExpression: "0 0/1 * * * ?" // cada 5 minutos
    }

    def execute() {
        println "Running DescuentoPorHorasRestantesJob"
        List<DescuentoPorHorasRestantes> descuentos = DescuentoPorHorasRestantes.getAll()
        descuentos.forEach { descuento ->
            println descuento
            descuentoService.aplicar(descuento)
        }
        println "Finished DescuentoPorHorasRestantesJob"
    }
}
