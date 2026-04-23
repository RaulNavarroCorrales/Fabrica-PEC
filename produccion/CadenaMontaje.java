package produccion;

import almacen.Almacen;
import componentes.Motor;
import componentes.Rueda;
import componentes.Tapiceria;
import vehiculos.EstadoVehiculo;
import vehiculos.Vehiculo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CadenaMontaje {

    private String id;
    private Queue<Vehiculo> cola;
    private List<Vehiculo> completados;
    private Almacen almacen;

    public CadenaMontaje(String id, Almacen almacen) {
        this.id = id;
        this.cola = new LinkedList<>();
        this.completados = new ArrayList<>();
        this.almacen = almacen;
    }

    public String getId() {
        return id;
    }

    public Queue<Vehiculo> getCola() {
        return cola;
    }

    public List<Vehiculo> getCompletados() {
        return completados;
    }

    public void ponerEnCola(Vehiculo v) {
        cola.add(v);
    }

    public Vehiculo verPrimero() {
        return cola.peek();
    }

    public void avanzar() {

        Vehiculo v = cola.peek();

        if (v == null) {
            System.out.println("  No hay vehículos en la cola");
            return;
        }

        switch (v.getEstado()) {

            // =====================
            // MOTOR
            // =====================
            case CHASIS:

                if (almacen.retirarMotor(v.getTipoMotor())) {
                    v.setEstado(EstadoVehiculo.MOTOR);
                    v.setMotor(new Motor(v.getTipoMotor(), 2.0, 150, 4));
                    System.out.println("  Motor montado");
                } else {
                    System.out.println("  No hay motores disponibles");
                }
                break;

            // =====================
            // TAPICERÍA
            // =====================
            case MOTOR:

                if (almacen.retirarTapiceria(v.getTipoTapiceria())) {
                    v.setEstado(EstadoVehiculo.TAPICERIA);
                    v.setTapiceria(new Tapiceria(v.getTipoTapiceria(), "negro", 3.5));
                    System.out.println("  Tapicería montada");
                } else {
                    System.out.println("  No hay tapicería disponible");
                }
                break;

            // =====================
            // RUEDAS (4 unidades)
            // =====================
            case TAPICERIA:

                boolean ruedasOK = true;

                for (int i = 0; i < 4; i++) {
                    if (!almacen.retirarRueda(v.getTipoRueda())) {
                        ruedasOK = false;
                        break;
                    }
                }

                if (ruedasOK) {
                    v.setEstado(EstadoVehiculo.RUEDAS);
                    for (int i = 0; i < 4; i++) {
                        v.anadirRueda(new Rueda(v.getTipoRueda(), 205, 16, 91, 240));
                    }
                    System.out.println("  Ruedas montadas");
                } else {
                    System.out.println("  No hay suficientes ruedas");
                }
                break;

            // =====================
            // FINALIZACIÓN
            // =====================
            case RUEDAS:

                v.setEstado(EstadoVehiculo.COMPLETO);
                completados.add(v);
                cola.poll();

                System.out.println("  Vehículo terminado");

                break;

            case COMPLETO:
                cola.poll();
                break;
        }
    }

    public void mostrarCola() {
        System.out.println("Cola de vehículos:");

        for (Vehiculo v : cola) {
            System.out.println(v);
        }
    }
}