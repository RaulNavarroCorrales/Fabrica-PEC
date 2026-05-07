package sistema;

import almacen.Almacen;
import almacen.AlmacenImpl;
import componentes.TipoMotor;
import componentes.TipoRueda;
import componentes.TipoTapiceria;
import produccion.CadenaMontaje;
import produccion.PlanificadorSimple;
import produccion.Scheduler;
import produccion.SchedulerImpl;
import trabajadores.Operario;
import trabajadores.Trabajador;
import vehiculos.Vehiculo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FactoryController {

    private Almacen almacen;
    private List<CadenaMontaje> cadenas;
    private List<Trabajador> trabajadores;
    private Scheduler scheduler;
    private PlanificadorSimple planificador;
    private List<Vehiculo> pendientes = new ArrayList<>();
    private Dashboard dashboard;

    public FactoryController() {
        this.almacen = new AlmacenImpl();
        this.cadenas = new ArrayList<>();
        this.trabajadores = new ArrayList<>();
    }

    // =====================
    // CADENAS
    // =====================

    public void anadirCadena(CadenaMontaje c) {
        cadenas.add(c);
    }

    public CadenaMontaje getPrimeraCadena() {
        if (cadenas.isEmpty()) return null;
        return cadenas.get(0);
    }

    public List<CadenaMontaje> getCadenas() {
        return cadenas;
    }

    public void setCadenas(List<CadenaMontaje> cadenas) {
        this.cadenas = cadenas;
    }

    // =====================
    // VEHÍCULOS
    // =====================

    public void anadirVehiculoACadena(Vehiculo v) {
        pendientes.add(v);
    }

    public List<Vehiculo> getPendientes() {
        return pendientes;
    }

    public void mostrarVehiculosCompletados() {

        System.out.println("=== VEHICULOS COMPLETADOS ===");

        boolean encontrados = false;

        for (CadenaMontaje c : cadenas) {

            for (Vehiculo v : c.getCompletados()) {
                System.out.println(v);
                encontrados = true;
            }
        }

        if (!encontrados) {
            System.out.println("No hay vehículos completados.");
        }
    }

    // =====================
    // TRABAJADORES
    // =====================

    public void anadirTrabajador(Trabajador t) {
        trabajadores.add(t);
    }

    public List<Trabajador> getTrabajadores() {
        return trabajadores;
    }

    public void setTrabajadores(List<Trabajador> trabajadores) {
        this.trabajadores = trabajadores;
    }

    public void mostrarTrabajadores() {

        System.out.println("\n=== TRABAJADORES ===");

        for (Trabajador t : trabajadores) {
            System.out.println(t);
        }
    }

    // =====================
    // DASHBOARD
    // =====================

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }

    public void notificarDashboard() {

        if (dashboard != null) {
            dashboard.mostrar();
        }
    }

    // =====================
    // ALMACÉN
    // =====================

    public Almacen getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    // =====================
    // PLANIFICADOR
    // =====================

    public void setPlanificador(PlanificadorSimple planificador) {
        this.planificador = planificador;
    }

    // =====================
    // SCHEDULER
    // =====================

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void configurarScheduler() {

        if (!cadenas.isEmpty() && planificador != null) {
            scheduler = new SchedulerImpl(cadenas, planificador, this);
        }
    }

    public void ejecutarSimulacion(int segundos) {

        if (scheduler != null) {
            scheduler.avanzarTiempo(segundos);
        }
    }

    // =========================================================
    // ESTADÍSTICAS Y LISTADOS
    // =========================================================

    public void listarOperariosPorProductividad(boolean soloEficientes) {

        System.out.println("\n=== OPERARIOS ===");

        trabajadores.stream()
                .filter(t -> t instanceof Operario)
                .map(t -> (Operario) t)
                .filter(o -> !soloEficientes || o.esEficiente())
                .sorted((o1, o2) -> o1.getNombre().compareTo(o2.getNombre()))
                .forEach(System.out::println);
    }

    public void listarVehiculosPorMotor(TipoMotor tipo) {

        System.out.println("\n=== VEHÍCULOS CON MOTOR " + tipo + " ===");

        boolean encontrados = false;

        for (CadenaMontaje c : cadenas) {

            for (Vehiculo v : c.getCompletados()) {

                if (v.getTipoMotor() == tipo) {
                    System.out.println(v);
                    encontrados = true;
                }
            }
        }

        if (!encontrados) {
            System.out.println("No hay vehículos con ese tipo de motor.");
        }
    }

    public void listarVehiculosPorTapiceria(TipoTapiceria tipo) {

        System.out.println("\n=== VEHÍCULOS CON TAPICERÍA " + tipo + " ===");

        boolean encontrados = false;

        for (CadenaMontaje c : cadenas) {

            for (Vehiculo v : c.getCompletados()) {

                if (v.getTipoTapiceria() == tipo) {
                    System.out.println(v);
                    encontrados = true;
                }
            }
        }

        if (!encontrados) {
            System.out.println("No hay vehículos con ese tipo de tapicería.");
        }
    }

    public void listarVehiculosPorRueda(TipoRueda tipo) {

        System.out.println("\n=== VEHÍCULOS CON RUEDAS " + tipo + " ===");

        boolean encontrados = false;

        for (CadenaMontaje c : cadenas) {

            for (Vehiculo v : c.getCompletados()) {

                if (v.getTipoRueda() == tipo) {
                    System.out.println(v);
                    encontrados = true;
                }
            }
        }

        if (!encontrados) {
            System.out.println("No hay vehículos con ese tipo de rueda.");
        }
    }

    public void configuracionesMasUsadas() {

        System.out.println("\n=== CONFIGURACIONES MÁS USADAS ===");

        Map<String, Integer> contador = new HashMap<>();

        for (CadenaMontaje c : cadenas) {

            for (Vehiculo v : c.getCompletados()) {

                String clave =
                        v.getTipoMotor() + " - " +
                                v.getTipoTapiceria() + " - " +
                                v.getTipoRueda();

                contador.put(clave, contador.getOrDefault(clave, 0) + 1);
            }
        }

        contador.entrySet().stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .forEach(e ->
                        System.out.println(e.getKey() + " -> " + e.getValue())
                );
    }

    public void listarProduccionPorTiempo(int segundo) {

        System.out.println("\n=== PRODUCCIÓN EN SEGUNDO " + segundo + " ===");

        boolean encontrados = false;

        for (CadenaMontaje c : cadenas) {

            for (Vehiculo v : c.getCompletados()) {

                if (v.getSegundoFinalizacion() == segundo) {
                    System.out.println(v);
                    encontrados = true;
                }
            }
        }

        if (!encontrados) {
            System.out.println("No hubo producción en ese segundo.");
        }
    }

    public void mostrarEstadisticas() {

        listarOperariosPorProductividad(false);

        listarVehiculosPorMotor(TipoMotor.GASOLINA);
        listarVehiculosPorMotor(TipoMotor.ELECTRICO);
        listarVehiculosPorMotor(TipoMotor.HIBRIDO);

        listarVehiculosPorTapiceria(TipoTapiceria.CUERO);
        listarVehiculosPorTapiceria(TipoTapiceria.TELA);
        listarVehiculosPorTapiceria(TipoTapiceria.ALCANTARA);

        listarVehiculosPorRueda(TipoRueda.DEPORTIVO);
        listarVehiculosPorRueda(TipoRueda.NORMAL);
        listarVehiculosPorRueda(TipoRueda.TODOTERRENO);

        configuracionesMasUsadas();
    }
}