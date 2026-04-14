package main;

import componentes.TipoMotor;
import componentes.TipoRueda;
import produccion.CadenaMontaje;
import sistema.Dashboard;
import sistema.DashboardImpl;
import sistema.FactoryController;
import vehiculos.BiplazaDeportivo;

public class FabricaMain {

    public static void main(String[] args) {

        FactoryController fc = new FactoryController();

        // =====================
        // CREAR CADENA
        // =====================
        CadenaMontaje c = new CadenaMontaje("C1");
        fc.añadirCadena(c);

        // =====================
        // AÑADIR VEHICULOS
        // =====================
        fc.añadirVehiculoACadena(new BiplazaDeportivo("rojo", 1200, 1800));
        fc.añadirVehiculoACadena(new BiplazaDeportivo("azul", 1100, 1700));

        // =====================
        // PROBAR ALMACEN
        // =====================
        fc.getAlmacen().añadirMotor(TipoMotor.GASOLINA, 5);
        fc.getAlmacen().añadirRueda(TipoRueda.NORMAL, 8);

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
        // EJECUTAR SIMULACION
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