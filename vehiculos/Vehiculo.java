package vehiculos;

import componentes.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehiculo {

    private String color;
    private int numPlazas;
    private double tara;
    private double pesoMaximoAutorizado;
    private EstadoVehiculo estado;
    private Motor motor;
    private Tapiceria tapiceria;
    private List<Rueda> ruedas;
    private TipoMotor tipoMotor;
    private TipoRueda tipoRueda;
    private TipoTapiceria tipoTapiceria;

    public Vehiculo(String color, int numPlazas, double tara, double pesoMaximoAutorizado, TipoMotor motor, TipoTapiceria tapiceria, TipoRueda rueda) {
        this.color = color;
        this.numPlazas = numPlazas;
        this.tara = tara;
        this.pesoMaximoAutorizado = pesoMaximoAutorizado;
        this.tipoMotor = motor;
        this.tipoTapiceria = tapiceria;
        this.tipoRueda = rueda;

        this.estado = EstadoVehiculo.CHASIS;
        this.ruedas = new ArrayList<>();
    }

    public EstadoVehiculo getEstado() {
        return estado;
    }

    public void setEstado(EstadoVehiculo estado) {
        this.estado = estado;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Tapiceria getTapiceria() {
        return tapiceria;
    }

    public void setTapiceria(Tapiceria tapiceria) {
        this.tapiceria = tapiceria;
    }

    public TipoTapiceria getTipoTapiceria() { return tipoTapiceria; }

    public void setTipoTapiceria(TipoTapiceria tipoTapiceria) {this.tipoTapiceria = tipoTapiceria; }

    public TipoRueda getTipoRueda() {return tipoRueda; }

    public void setTipoRueda(TipoRueda tipoRueda) { this.tipoRueda = tipoRueda; }

    public TipoMotor getTipoMotor() { return tipoMotor; }

    public void setTipoMotor(TipoMotor tipoMotor) { this.tipoMotor = tipoMotor; }

    public String getColor() { return color; }
    public void anadirRueda(Rueda rueda) {
        if (ruedas.size() < 4) {
            ruedas.add(rueda);
        } else {
            System.out.println("Ya tiene 4 ruedas");
        }
    }

    public List<Rueda> getRuedas() {
        return ruedas;
    }
    @Override
    public String toString() {
        return "Vehiculo{" +
                "color='" + color + '\'' +
                ", numPlazas=" + numPlazas +
                ", tara=" + tara +
                ", pesoMaximoAutorizado=" + pesoMaximoAutorizado +
                ", estado=" + estado +
                ", motor=" + motor +
                ", tapiceria=" + tapiceria +
                ", ruedas=" + ruedas +
                '}';
    }
}