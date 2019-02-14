package pasalabocha

import java.text.DecimalFormat

class Dinero implements Comparable<Dinero>, Serializable {
    BigDecimal monto
    Moneda moneda

    Dinero(BigDecimal monto, Moneda moneda) {
        if (monto < 0) {
            throw new IllegalArgumentException("Monto negativo")
        }
        this.monto = monto
        this.moneda = moneda

        this.monto.setScale(2, BigDecimal.ROUND_DOWN)
    }

    Dinero() {

    }


    @Override
    int compareTo(Dinero that) {
        int iguales = 0

        int comparacion = this.monto.compareTo(that.monto)
        if ( comparacion != iguales ) return comparacion

        comparacion = this.moneda <=> that.moneda
        if ( comparacion != iguales ) return comparacion

        return iguales
    }

    @Override
    boolean equals(Object o) {
        return o != null &&
                o.getClass() == Dinero.class &&
                this.monto == (BigDecimal) o.monto &&
                this.moneda == (Moneda) o.moneda
    }

    @Override
    String toString() {
        DecimalFormat df = new DecimalFormat()

        df.setMaximumFractionDigits(2)

        df.setMinimumFractionDigits(0)

        df.setGroupingUsed(false)

        return df.format(this.monto) + " " + this.moneda.toString()
    }


    Dinero plus(Dinero d){
        if (this.moneda != d.moneda) {
            throw new IllegalArgumentException("Monedas distintas")
        }
        return new Dinero(this.monto + d.monto, this.moneda)
    }

    Dinero minus(Dinero d){
        if (this.moneda != d.moneda) {
            throw new IllegalArgumentException("Monedas distintas")
        }
        return new Dinero(this.monto - d.monto, this.moneda)
    }

    Dinero multiply(def factor){
        BigDecimal factorDecimal = BigDecimal.valueOf(factor)
        BigDecimal nuevoMonto = this.monto * factorDecimal
        return new Dinero(nuevoMonto, this.moneda)
    }


    Dinero div(def factor){
        BigDecimal factorDecimal = BigDecimal.valueOf(factor)
        BigDecimal nuevoMonto = this.monto / factorDecimal
        return new Dinero(nuevoMonto, this.moneda)
    }
}

enum Moneda {
    ARS,
    USD
}
