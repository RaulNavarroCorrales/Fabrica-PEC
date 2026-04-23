package main;

import componentes.TipoMotor;
import componentes.TipoRueda;
import componentes.TipoTapiceria;
import produccion.CadenaMontaje;
import produccion.PlanificadorSimple;
import sistema.Dashboard;
import sistema.DashboardImpl;
import sistema.FactoryController;
import vehiculos.BiplazaDeportivo;

public class factory_main {

    public static void main(String[] args) {
        System.out.println("INICIO SIMULACIÓN");
        FactoryController fc = new FactoryController();
        PlanificadorSimple planificadorSimple = new PlanificadorSimple(fc.getAlmacen());

        // =====================
        // ALMACÉN
        // =====================
        fc.setAlmacen(new almacen.AlmacenImpl());
        fc.setPlanificador(planificadorSimple);
        fc.configurarScheduler();
        fc.ejecutarSimulacion(10);

        fc.getAlmacen().añadirMotor(TipoMotor.GASOLINA, 10);
        fc.getAlmacen().añadirTapiceria(TipoTapiceria.CUERO, 10);
        fc.getAlmacen().añadirRueda(TipoRueda.DEPORTIVO, 50);

        // =====================
        // CADENA DE MONTAJE
        // =====================
        CadenaMontaje c1 = new CadenaMontaje("C1", fc.getAlmacen());
        fc.añadirCadena(c1);

        // =====================
        // VEHÍCULOS (PLAN DE PRODUCCIÓN)
        // =====================
        fc.añadirVehiculoACadena(
                new BiplazaDeportivo(
                        "rojo",
                        1200,
                        1800,
                        TipoMotor.GASOLINA,
                        TipoTapiceria.CUERO,
                        TipoRueda.DEPORTIVO
                )
        );

        fc.añadirVehiculoACadena(
                new BiplazaDeportivo(
                        "azul",
                        1100,
                        1700,
                        TipoMotor.GASOLINA,
                        TipoTapiceria.CUERO,
                        TipoRueda.DEPORTIVO
                )
        );

        // =====================
        // DASHBOARD INICIAL
        // =====================
        Dashboard d = new DashboardImpl(fc);
        d.mostrar();

        // =====================
        // CONFIGURAR SCHEDULER
        // =====================
        fc.configurarScheduler();

        // =====================
        // SIMULACIÓN
        // =====================
        fc.ejecutarSimulacion(10);

        // =====================
        // RESULTADOS
        // =====================
        System.out.println("\n--- RESULTADOS ---");
        fc.mostrarVehiculosCompletados();

        // =====================
        // DASHBOARD FINAL
        // =====================
        d.mostrar();
    }
}