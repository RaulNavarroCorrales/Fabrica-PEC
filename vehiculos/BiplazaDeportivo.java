package vehiculos;


import componentes.TipoMotor;
import componentes.TipoRueda;
import componentes.TipoTapiceria;

/**
 * @author Raul
 */
public class BiplazaDeportivo extends Vehiculo {
    public BiplazaDeportivo(String color, double tara, double pesoMaxAutorizado, TipoMotor motor, TipoTapiceria tapiceria, TipoRueda rueda) {
        super(color, 2, tara, pesoMaxAutorizado, motor, tapiceria, rueda);
    }
}