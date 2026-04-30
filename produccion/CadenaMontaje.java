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

        v.avanzar(almacen);

        if (v.getEstado() == EstadoVehiculo.COMPLETO) {
            completados.add(v);
            cola.poll();
        }
    }

    public void mostrarCola() {
        System.out.println("Cola de vehículos:");

        for (Vehiculo v : cola) {
            System.out.println(v);
        }
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
}