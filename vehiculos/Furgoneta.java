package vehiculos;

import componentes.TipoMotor;
import componentes.TipoRueda;
import componentes.TipoTapiceria;

/**
 * @author Raul
 */
public class Furgoneta extends Vehiculo {
    public Furgoneta(String color, int numPlazas, double tara, double pesoMaxAutorizado, TipoMotor motor, TipoTapiceria tapiceria, TipoRueda rueda) {
        super(color, numPlazas, tara, pesoMaxAutorizado, motor, tapiceria, rueda);
    }
}