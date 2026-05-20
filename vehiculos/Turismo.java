package vehiculos;


import componentes.TipoMotor;
import componentes.TipoRueda;
import componentes.TipoTapiceria;

/**
 * @author Raul
 */
public class Turismo extends Vehiculo {
    public Turismo(String color, double tara, double pesoMaxAutorizado, TipoMotor motor, TipoTapiceria tapiceria, TipoRueda rueda) {
        super(color, 5, tara, pesoMaxAutorizado, motor, tapiceria, rueda);
    }
}