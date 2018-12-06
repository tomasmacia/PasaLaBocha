package pasalabocha

class CanchaController {
    CanchaService canchaService

    def index() { }

    def show(Long canchaId) {
        Cancha cancha = canchaService.get(canchaId)

        render(view: "show", model: [cancha: cancha])
    }
}
