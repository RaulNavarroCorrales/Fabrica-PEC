package componentes;


/**
 * Write a description of class Motor here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Motor {
    private TipoMotor tipo;
    private double cilindrada;
    private int potencia;
    private int numCilindros;

    public Motor(TipoMotor tipo, double cilindrada, int potencia, int numCilindros) {
        this.tipo = tipo;
        this.cilindrada = cilindrada;
        this.potencia = potencia;
        this.numCilindros = numCilindros;
    }

    public TipoMotor getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Motor{" +
                "tipo=" + tipo +
                ", cilindrada=" + cilindrada +
                ", potencia=" + potencia +
                ", numCilindros=" + numCilindros +
                '}';
    }
}