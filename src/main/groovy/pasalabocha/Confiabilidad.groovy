package pasalabocha

class Confiabilidad implements Comparable {
    private final int UNIDAD = 1

    int nivel

    public Confiabilidad(int nivel) {
        this.nivel = nivel
    }

    public Confiabilidad() {
    }

    Confiabilidad aumentar() {
        return new Confiabilidad(nivel + UNIDAD)
    }

    Confiabilidad disminuir() {
        if (nivel == 0) {
            return this
        }
        return new Confiabilidad(nivel - UNIDAD)
    }

    boolean satisface(int nivel){
        Confiabilidad otro = new Confiabilidad(nivel)
        return this >= otro
    }

//    int compareTo(Confiabilidad otro) {
//        return this.nivel < otro.nivel ? -1 : (this.nivel == otro.nivel ? 0 : 1)
//    }

    @Override
    int compareTo(Object o) {
        return nivel <=> ((Confiabilidad) o).nivel
    }
}
