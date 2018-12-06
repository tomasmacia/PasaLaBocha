package pasalabocha

class ClubController {
    ClubService clubService

    def index() { }

    def show() {
        String clubId = params.clubId

        def club = clubService.get(Long.valueOf(clubId))

        render(view: "show", model: [club: club])
    }
}
