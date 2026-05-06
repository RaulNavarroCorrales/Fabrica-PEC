package produccion;

import almacen.Almacen;
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
    private boolean averiada = false;
    private int tiempoReparacionRestante = 0;
    private int numeroAverias = 0;

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

    public void avanzar(int segundoActual) {

        if (averiada) {
            System.out.println("  Cadena averiada...");
            reparar();
            return;
        }

        Vehiculo v = cola.peek();

        if (v == null) {
            System.out.println("  No hay vehículos en la cola");
            return;
        }

        v.avanzar(almacen);

        if (v.getEstado() == EstadoVehiculo.COMPLETO) {
            completados.add(v);
            v.setSegundoFinalizacion(segundoActual);
            cola.poll();
        }
    }

    public void mostrarCola() {

        for (Vehiculo v : cola) {
            System.out.println(v);
        }
    }

    public void provocarAveria(int tiempoReparacion) {
        averiada = true;
        tiempoReparacionRestante = tiempoReparacion;
        System.out.println("⚠ Cadena " + id + " averiada durante " + tiempoReparacion + " segundos");
    }

    public void reparar() {
        if (averiada) {
            tiempoReparacionRestante--;

            if (tiempoReparacionRestante <= 0) {
                averiada = false;
                System.out.println("✔ Cadena " + id + " reparada");
            }
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

    public int getNumeroAverias() {
        return numeroAverias;
    }

    public void setNumeroAverias(int numeroAverias) {
        this.numeroAverias = numeroAverias;
    }
}