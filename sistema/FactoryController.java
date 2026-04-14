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

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    public List<CadenaMontaje> getCadenas() {
        return cadenas;
    }

    public void setCadenas(List<CadenaMontaje> cadenas) {
        this.cadenas = cadenas;
    }

    public List<Trabajador> getTrabajadores() {
        return trabajadores;
    }

    public void setTrabajadores(List<Trabajador> trabajadores) {
        this.trabajadores = trabajadores;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
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
        if (!cadenas.isEmpty()) {
            scheduler = new SchedulerImpl(cadenas);
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