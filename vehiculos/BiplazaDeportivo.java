package vehiculos;


import componentes.TipoMotor;
import componentes.TipoRueda;
import componentes.TipoTapiceria;

/**
 * Write a description of class BiplazaDeportivo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BiplazaDeportivo extends Vehiculo {
    public BiplazaDeportivo(String color, double tara, double pesoMaxAutorizado, TipoMotor motor, TipoTapiceria tapiceria, TipoRueda rueda) {
        super(color, 2, tara, pesoMaxAutorizado, motor, tapiceria, rueda);
    }
}