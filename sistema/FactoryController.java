package sistema;

import almacen.Almacen;
import almacen.AlmacenImpl;
import produccion.CadenaMontaje;
import produccion.Scheduler;
import produccion.SchedulerImpl;
import trabajadores.Trabajador;
import vehiculos.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class FactoryController {

    private Almacen almacen;
    private List<CadenaMontaje> cadenas;
    private List<Trabajador> trabajadores;
    private Scheduler scheduler;

    public FactoryController() {
        this.almacen = new AlmacenImpl();
        this.cadenas = new ArrayList<>();
        this.trabajadores = new ArrayList<>();
    }

    // =====================
    // CADENAS
    // =====================

    public void añadirCadena(CadenaMontaje c) {
        cadenas.add(c);
    }

    public CadenaMontaje getPrimeraCadena() {
        if (cadenas.isEmpty()) return null;
        return cadenas.get(0);
    }

    // =====================
    // VEHICULOS
    // =====================

    public void añadirVehiculoACadena(Vehiculo v) {
        CadenaMontaje c = getPrimeraCadena();
        if (c != null) {
            c.ponerEnCola(v);
        }
    }

    // =====================
    // TRABAJADORES
    // =====================

    public void añadirTrabajador(Trabajador t) {
        trabajadores.add(t);
    }

    public void mostrarTrabajadores() {
        for (Trabajador t : trabajadores) {
            System.out.println(t);
        }
    }

    // =====================
    // ALMACEN
    // =====================

    public Almacen getAlmacen() {
        return almacen;
    }

    // =====================
    // SCHEDULER
    // =====================

    public void configurarScheduler() {
        CadenaMontaje c = getPrimeraCadena();
        if (c != null) {
            scheduler = new SchedulerImpl(c);
        }
    }

    public void ejecutarSimulacion(int segundos) {
        if (scheduler != null) {
            scheduler.avanzarTiempo(segundos);
        }
    }

    public void mostrarVehiculosCompletados() {
        CadenaMontaje c = getPrimeraCadena();

        if (c == null) {
            System.out.println("No hay cadena");
            return;
        }

        System.out.println("=== VEHICULOS COMPLETADOS ===");

        for (Vehiculo v : c.getCompletados()) {
            System.out.println(v);
        }
    }
}