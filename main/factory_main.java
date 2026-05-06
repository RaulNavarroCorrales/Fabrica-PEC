package main;

import almacen.AlmacenImpl;
import componentes.TipoMotor;
import componentes.TipoRueda;
import componentes.TipoTapiceria;
import produccion.CadenaMontaje;
import produccion.PlanificadorSimple;
import sistema.Dashboard;
import sistema.DashboardImpl;
import sistema.FactoryController;
import trabajadores.AdministradorSistema;
import trabajadores.GestorPlanta;
import trabajadores.MecanicoCinta;
import trabajadores.Operario;
import vehiculos.BiplazaDeportivo;
import vehiculos.Furgoneta;
import vehiculos.Turismo;

public class factory_main {

    public static void main(String[] args) {

        System.out.println("INICIO SIMULACIÓN DE FÁBRICA\n");

        FactoryController fc = new FactoryController();

        // ALMACÉN
        fc.setAlmacen(new AlmacenImpl());

        // STOCK INICIAL
        fc.getAlmacen().anadirMotor(TipoMotor.GASOLINA, 10);
        fc.getAlmacen().anadirMotor(TipoMotor.ELECTRICO, 8);
        fc.getAlmacen().anadirMotor(TipoMotor.HIBRIDO, 6);

        fc.getAlmacen().anadirTapiceria(TipoTapiceria.CUERO, 12);
        fc.getAlmacen().anadirTapiceria(TipoTapiceria.TELA, 12);
        fc.getAlmacen().anadirTapiceria(TipoTapiceria.ALCANTARA, 12);

        fc.getAlmacen().anadirRueda(TipoRueda.DEPORTIVO, 40);
        fc.getAlmacen().anadirRueda(TipoRueda.NORMAL, 40);
        fc.getAlmacen().anadirRueda(TipoRueda.TODOTERRENO, 40);

        // TRABAJADORES
        fc.anadirTrabajador(new Operario("Ana", "Lopez", "11111111A", "Calle 1", 1001L, 12));
        fc.anadirTrabajador(new Operario("Luis", "Perez", "22222222B", "Calle 2", 1002L, 5));
        fc.anadirTrabajador(new MecanicoCinta("Marta", "Gomez", "33333333C", "Calle 3", 1003L, 25));
        fc.anadirTrabajador(new GestorPlanta("Carlos", "Ruiz", "44444444D", "Calle 4", 1004L));
        fc.anadirTrabajador(new AdministradorSistema("Elena", "Santos", "55555555E", "Calle 5", 1005L));

        // CADENAS DE MONTAJE
        CadenaMontaje c1 = new CadenaMontaje("C1", fc.getAlmacen());
        CadenaMontaje c2 = new CadenaMontaje("C2", fc.getAlmacen());
        CadenaMontaje c3 = new CadenaMontaje("C3", fc.getAlmacen());

        fc.anadirCadena(c1);
        fc.anadirCadena(c2);
        fc.anadirCadena(c3);

        // PLANIFICADOR Y DASHBOARD
        PlanificadorSimple planificadorSimple = new PlanificadorSimple(fc.getAlmacen());
        fc.setPlanificador(planificadorSimple);

        Dashboard dashboard = new DashboardImpl(fc);
        fc.setDashboard(dashboard);

        // VEHÍCULOS A PRODUCIR
        fc.anadirVehiculoACadena(
                new BiplazaDeportivo(
                        "rojo",
                        1200,
                        1800,
                        TipoMotor.GASOLINA,
                        TipoTapiceria.CUERO,
                        TipoRueda.DEPORTIVO
                )
        );

        fc.anadirVehiculoACadena(
                new Turismo(
                        "azul",
                        1400,
                        2000,
                        TipoMotor.ELECTRICO,
                        TipoTapiceria.TELA,
                        TipoRueda.NORMAL
                )
        );

        fc.anadirVehiculoACadena(
                new Furgoneta(
                        "blanco",
                        3,
                        1800,
                        2500,
                        TipoMotor.HIBRIDO,
                        TipoTapiceria.ALCANTARA,
                        TipoRueda.TODOTERRENO
                )
        );

        // CONFIGURAR SCHEDULER
        fc.configurarScheduler();

        // DASHBOARD INICIAL
        System.out.println("\n--- DASHBOARD INICIAL ---");
        dashboard.mostrar();

        // SIMULACIÓN
        System.out.println("\n--- SIMULACIÓN ---");
        fc.ejecutarSimulacion(10);

        // RESULTADOS
        System.out.println("\n--- VEHÍCULOS COMPLETADOS ---");
        fc.mostrarVehiculosCompletados();

        // DASHBOARD FINAL
        System.out.println("\n--- DASHBOARD FINAL ---");
        dashboard.mostrar();

        System.out.println("\nFIN DE LA SIMULACIÓN");
    }
}