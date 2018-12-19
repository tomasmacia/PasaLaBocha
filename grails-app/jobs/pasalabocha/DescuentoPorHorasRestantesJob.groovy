package pasalabocha

class DescuentoPorHorasRestantesJob {

    DescuentoPorHorasRestantesService descuentoPorHorasRestantesService

    static triggers = {
        cron name: 'descuentoRangoTrigger', cronExpression: "0 0/5 * * * ?"
    }

    def execute() {
        println "Running DescuentoPorHorasRestantesJob"
        List<DescuentoPorHorasRestantes> descuentos = DescuentoPorHorasRestantes.getAll()
        descuentos.forEach { descuento ->
            println descuento
            descuentoPorHorasRestantesService.aplicar(descuento)
        }
        println "Finished DescuentoPorHorasRestantesJob"
    }
}
