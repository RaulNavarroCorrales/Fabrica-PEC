package vehiculos;


import componentes.TipoMotor;
import componentes.TipoRueda;
import componentes.TipoTapiceria;

/**
 * Write a description of class Tursimo here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Turismo extends Vehiculo {
    public Turismo(String color, double tara, double pesoMaxAutorizado, TipoMotor motor, TipoTapiceria tapiceria, TipoRueda rueda) {
        super(color, 5, tara, pesoMaxAutorizado, motor, tapiceria, rueda);
    }
}