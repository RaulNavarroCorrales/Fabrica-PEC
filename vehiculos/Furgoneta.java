package vehiculos;


import componentes.TipoMotor;
import componentes.TipoRueda;
import componentes.TipoTapiceria;

/**
 * Write a description of class Furgoneta here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Furgoneta extends Vehiculo {
    public Furgoneta(String color, int numPlazas, double tara, double pesoMaxAutorizado, TipoMotor motor, TipoTapiceria tapiceria, TipoRueda rueda) {
        super(color, numPlazas, tara, pesoMaxAutorizado, motor, tapiceria, rueda);
    }

}