package vehiculos;

import almacen.Almacen;
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
    private int segundoFinalizacion;

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

    public void anadirRueda(Rueda rueda) {
        if (ruedas.size() < 4) {
            ruedas.add(rueda);
        } else {
            System.out.println("Ya tiene 4 ruedas");
        }
    }

    public void avanzar(Almacen almacen) {

        switch (estado) {

            case CHASIS:
                if (almacen.retirarMotor(tipoMotor)) {
                    this.motor = new Motor(tipoMotor, 2.0, 150, 4);
                    estado = EstadoVehiculo.MOTOR;
                    System.out.println("  Motor montado");
                } else {
                    System.out.println("  No hay motores disponibles");
                }
                break;

            case MOTOR:
                if (almacen.retirarTapiceria(tipoTapiceria)) {
                    this.tapiceria = new Tapiceria(tipoTapiceria, "negro", 3.5);
                    estado = EstadoVehiculo.TAPICERIA;
                    System.out.println("  Tapicería montada");
                } else {
                    System.out.println("  No hay tapicería disponible");
                }
                break;

            case TAPICERIA:
                boolean ruedasOK = true;

                for (int i = 0; i < 4; i++) {
                    if (!almacen.retirarRueda(tipoRueda)) {
                        ruedasOK = false;
                        break;
                    }
                }

                if (ruedasOK) {
                    for (int i = 0; i < 4; i++) {
                        ruedas.add(new Rueda(tipoRueda, 205, 16, 91, 240));
                    }
                    estado = EstadoVehiculo.RUEDAS;
                    System.out.println("  Ruedas montadas");
                } else {
                    System.out.println("  No hay suficientes ruedas");
                }
                break;

            case RUEDAS:
                estado = EstadoVehiculo.COMPLETO;
                System.out.println("  Vehículo terminado");
                break;

            case COMPLETO:
                break;
        }
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

    public TipoTapiceria getTipoTapiceria() {
        return tipoTapiceria;
    }

    public void setTipoTapiceria(TipoTapiceria tipoTapiceria) {
        this.tipoTapiceria = tipoTapiceria;
    }

    public TipoRueda getTipoRueda() {
        return tipoRueda;
    }

    public void setTipoRueda(TipoRueda tipoRueda) {
        this.tipoRueda = tipoRueda;
    }

    public TipoMotor getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(TipoMotor tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public String getColor() {
        return color;
    }

    public List<Rueda> getRuedas() {
        return ruedas;
    }

    public int getSegundoFinalizacion() {
        return segundoFinalizacion;
    }

    public void setSegundoFinalizacion(int segundo) {
        this.segundoFinalizacion = segundo;
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