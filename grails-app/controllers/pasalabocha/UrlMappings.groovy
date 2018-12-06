package pasalabocha

class UrlMappings {

    static mappings = {
//        "/$controller/$action?/$id?(.$format)?"{
//            constraints {
//                // apply constraints here
//            }
//        }
        "/club/$clubId"(controller: "club", action: "show")
        "/cancha/$canchaId"(controller: "cancha", action: "show")

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
