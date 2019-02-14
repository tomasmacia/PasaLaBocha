package pasalabocha

class DescuentoEnRangoJob {

    DescuentoService descuentoService

    static triggers = {
        cron name: 'descuentoRangoTrigger', cronExpression: "0 0/1 * * * ?" // cada 20 minutos
    }

    // corre cada 20 minutos para actualizar nuevos turnos que no se les aplico descuento al momento de creacion del mismo
    def execute() {
        println "Running DescuentoEnRangoJob"
        List<DescuentoEnRango> descuentos = DescuentoEnRango.getAll()
        descuentos.forEach { descuento ->
            println descuento
            descuentoService.aplicar(descuento)
        }
        println "Finished DescuentoEnRangoJob"
    }
}
