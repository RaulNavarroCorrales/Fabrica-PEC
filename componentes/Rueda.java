package componentes;


/**
 * Write a description of class Rueda here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rueda {
    private TipoRueda tipo;
    private int anchoMM;
    private int diametro;
    private int indiceCarga;
    private int codigoVelocidad;

    public Rueda(TipoRueda tipo, int anchoMM, int diametro, int indiceCarga, int codigoVelocidad) {
        this.tipo = tipo;
        this.anchoMM = anchoMM;
        this.diametro = diametro;
        this.indiceCarga = indiceCarga;
        this.codigoVelocidad = codigoVelocidad;
    }

    public TipoRueda getTipo() {
        return tipo;
    }

    public void setTipo(TipoRueda tipo) {
        this.tipo = tipo;
    }

    public int getAnchoMM() {
        return anchoMM;
    }

    public void setAnchoMM(int anchoMM) {
        this.anchoMM = anchoMM;
    }

    public int getDiametro() {
        return diametro;
    }

    public void setDiametro(int diametro) {
        this.diametro = diametro;
    }

    public int getIndiceCarga() {
        return indiceCarga;
    }

    public void setIndiceCarga(int indiceCarga) {
        this.indiceCarga = indiceCarga;
    }

    public int getCodigoVelocidad() {
        return codigoVelocidad;
    }

    public void setCodigoVelocidad(int codigoVelocidad) {
        this.codigoVelocidad = codigoVelocidad;
    }
}