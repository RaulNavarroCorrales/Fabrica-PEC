package componentes;

/**
 * Write a description of class Tapiceria here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tapiceria {
    private TipoTapiceria tipo;
    private String color;
    private double metrosCuadrados;

    public Tapiceria(TipoTapiceria tipo, String color, double metrosCuadrados) {
        this.tipo = tipo;
        this.color = color;
        this.metrosCuadrados = metrosCuadrados;
    }

    public TipoTapiceria getTipo() {
        return tipo;
    }

    public void setTipo(TipoTapiceria tipo) {
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getMetrosCuadrados() {
        return metrosCuadrados;
    }

    public void setMetrosCuadrados(double metrosCuadrados) {
        this.metrosCuadrados = metrosCuadrados;
    }
}