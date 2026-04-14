package produccion;

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

    public CadenaMontaje(String id) {
        this.id = id;
        this.cola = new LinkedList<>();
        this.completados = new ArrayList<>();
    }

    public Queue<Vehiculo> getCola() {
        return cola;
    }

    // Añadir vehículo a la cola
    public void ponerEnCola(Vehiculo v) {
        cola.add(v);
    }

    // Ver siguiente vehículo
    public Vehiculo verPrimero() {
        return cola.peek();
    }

    public List<Vehiculo> getCompletados() {
        return completados;
    }

    // Avanzar un paso en la cadena
    public void avanzar() {

        Vehiculo v = cola.peek();

        if (v == null) {
            System.out.println("No hay vehículos en la cola");
            return;
        }

        switch (v.getEstado()) {

            case CHASIS:
                v.setEstado(EstadoVehiculo.MOTOR);
                System.out.println("Motor montado");
                break;

            case MOTOR:
                v.setEstado(EstadoVehiculo.TAPICERIA);
                System.out.println("Tapicería montada");
                break;

            case TAPICERIA:
                v.setEstado(EstadoVehiculo.RUEDAS);
                System.out.println("Ruedas montadas");
                break;

            case RUEDAS:
                v.setEstado(EstadoVehiculo.COMPLETO);
                System.out.println("Vehículo terminado");

                completados.add(v); // 🔥 GUARDAR
                cola.poll();

                break;

            case COMPLETO:
                cola.poll();
                break;
        }
    }

    // Mostrar cola
    public void mostrarCola() {
        System.out.println("Cola de vehículos:");
        for (Vehiculo v : cola) {
            System.out.println(v);
        }
    }
}