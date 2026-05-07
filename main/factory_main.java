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

import java.util.Scanner;

public class factory_main {

    public static void main(String[] args) {

        System.out.println("INICIO SIMULACIÓN DE FÁBRICA\n");

        FactoryController fc = new FactoryController();

        // =====================
        // ALMACÉN
        // =====================
        fc.setAlmacen(new AlmacenImpl());

        cargarStockInicial(fc);
        cargarTrabajadores(fc);
        cargarCadenas(fc);
        configurarSistema(fc);
        cargarVehiculos(fc);

        // =====================
        // DASHBOARD INICIAL
        // =====================
        System.out.println("\n--- DASHBOARD INICIAL ---");
        fc.getDashboard().mostrar();

        // =====================
        // SIMULACIÓN
        // =====================
        System.out.println("\n--- SIMULACIÓN ---");
        fc.ejecutarSimulacion(30);

        // =====================
        // MENÚ
        // =====================
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n==============================");
            System.out.println(" MENÚ FÁBRICA");
            System.out.println("==============================");
            System.out.println("1. Mostrar vehículos completados");
            System.out.println("2. Mostrar dashboard final");
            System.out.println("3. Listar operarios");
            System.out.println("4. Listar operarios eficientes");
            System.out.println("5. Vehículos por motor");
            System.out.println("6. Vehículos por tapicería");
            System.out.println("7. Vehículos por rueda");
            System.out.println("8. Configuraciones más usadas");
            System.out.println("9. Producción por segundo");
            System.out.println("0. Salir");
            System.out.print("Seleccione opción: ");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    System.out.println("\n--- VEHÍCULOS COMPLETADOS ---");
                    fc.mostrarVehiculosCompletados();
                    break;

                case 2:
                    System.out.println("\n--- DASHBOARD FINAL ---");
                    fc.getDashboard().mostrar();
                    break;

                case 3:
                    fc.listarOperariosPorProductividad(false);
                    break;

                case 4:
                    fc.listarOperariosPorProductividad(true);
                    break;

                case 5:
                    System.out.println("""
                            
                            Tipo motor:
                            1. GASOLINA
                            2. ELECTRICO
                            3. HIBRIDO
                            """);

                    int motor = sc.nextInt();

                    switch (motor) {
                        case 1 -> fc.listarVehiculosPorMotor(TipoMotor.GASOLINA);
                        case 2 -> fc.listarVehiculosPorMotor(TipoMotor.ELECTRICO);
                        case 3 -> fc.listarVehiculosPorMotor(TipoMotor.HIBRIDO);
                        default -> System.out.println("Opción inválida");
                    }
                    break;

                case 6:
                    System.out.println("""
                            
                            Tipo tapicería:
                            1. CUERO
                            2. TELA
                            3. ALCANTARA
                            """);

                    int tapiceria = sc.nextInt();

                    switch (tapiceria) {
                        case 1 -> fc.listarVehiculosPorTapiceria(TipoTapiceria.CUERO);
                        case 2 -> fc.listarVehiculosPorTapiceria(TipoTapiceria.TELA);
                        case 3 -> fc.listarVehiculosPorTapiceria(TipoTapiceria.ALCANTARA);
                        default -> System.out.println("Opción inválida");
                    }
                    break;

                case 7:
                    System.out.println("""
                            
                            Tipo rueda:
                            1. NORMAL
                            2. DEPORTIVO
                            3. TODOTERRENO
                            """);

                    int rueda = sc.nextInt();

                    switch (rueda) {
                        case 1 -> fc.listarVehiculosPorRueda(TipoRueda.NORMAL);
                        case 2 -> fc.listarVehiculosPorRueda(TipoRueda.DEPORTIVO);
                        case 3 -> fc.listarVehiculosPorRueda(TipoRueda.TODOTERRENO);
                        default -> System.out.println("Opción inválida");
                    }
                    break;

                case 8:
                    fc.configuracionesMasUsadas();
                    break;

                case 9:
                    System.out.print("Introduce segundo: ");
                    int segundo = sc.nextInt();

                    fc.listarProduccionPorTiempo(segundo);
                    break;

                case 0:
                    System.out.println("\nFIN DE LA SIMULACIÓN");
                    break;

                default:
                    System.out.println("Opción inválida");
            }

        } while (opcion != 0);

        sc.close();
    }

    private static void cargarStockInicial(FactoryController fc) {
        // Motores
        fc.getAlmacen().anadirMotor(TipoMotor.GASOLINA, 20);
        fc.getAlmacen().anadirMotor(TipoMotor.ELECTRICO, 20);
        fc.getAlmacen().anadirMotor(TipoMotor.HIBRIDO, 20);

        // Tapicerías
        fc.getAlmacen().anadirTapiceria(TipoTapiceria.CUERO, 20);
        fc.getAlmacen().anadirTapiceria(TipoTapiceria.TELA, 20);
        fc.getAlmacen().anadirTapiceria(TipoTapiceria.ALCANTARA, 20);

        // Ruedas
        fc.getAlmacen().anadirRueda(TipoRueda.DEPORTIVO, 60);
        fc.getAlmacen().anadirRueda(TipoRueda.NORMAL, 60);
        fc.getAlmacen().anadirRueda(TipoRueda.TODOTERRENO, 60);
    }

    private static void cargarTrabajadores(FactoryController fc) {
        fc.anadirTrabajador(new Operario("Ana", "Lopez", "11111111A", "Calle 1", 1001L, 12));
        fc.anadirTrabajador(new Operario("Luis", "Perez", "22222222B", "Calle 2", 1002L, 5));
        fc.anadirTrabajador(new Operario("Marta", "Sanchez", "33333333C", "Calle 3", 1003L, 15));
        fc.anadirTrabajador(new MecanicoCinta("Javier", "Gomez", "44444444D", "Calle 4", 1004L, 25));
        fc.anadirTrabajador(new MecanicoCinta("Elena", "Ruiz", "55555555E", "Calle 5", 1005L, 8));
        fc.anadirTrabajador(new GestorPlanta("Carlos", "Martin", "66666666F", "Calle 6", 1006L));
        fc.anadirTrabajador(new AdministradorSistema("Sara", "Diaz", "77777777G", "Calle 7", 1007L));
    }

    private static void cargarCadenas(FactoryController fc) {
        CadenaMontaje c1 = new CadenaMontaje("C1", fc.getAlmacen());
        CadenaMontaje c2 = new CadenaMontaje("C2", fc.getAlmacen());
        CadenaMontaje c3 = new CadenaMontaje("C3", fc.getAlmacen());

        fc.anadirCadena(c1);
        fc.anadirCadena(c2);
        fc.anadirCadena(c3);
    }

    private static void configurarSistema(FactoryController fc) {
        PlanificadorSimple planificadorSimple = new PlanificadorSimple(fc.getAlmacen());
        fc.setPlanificador(planificadorSimple);

        Dashboard dashboard = new DashboardImpl(fc);
        fc.setDashboard(dashboard);

        fc.configurarScheduler();
    }

    private static void cargarVehiculos(FactoryController fc) {

        fc.anadirVehiculoACadena(new BiplazaDeportivo(
                "rojo", 1200, 1800,
                TipoMotor.GASOLINA,
                TipoTapiceria.CUERO,
                TipoRueda.DEPORTIVO
        ));

        fc.anadirVehiculoACadena(new Turismo(
                "azul", 1400, 2000,
                TipoMotor.ELECTRICO,
                TipoTapiceria.TELA,
                TipoRueda.NORMAL
        ));

        fc.anadirVehiculoACadena(new Furgoneta(
                "blanco", 3, 1800, 2500,
                TipoMotor.HIBRIDO,
                TipoTapiceria.ALCANTARA,
                TipoRueda.TODOTERRENO
        ));

        fc.anadirVehiculoACadena(new BiplazaDeportivo(
                "negro", 1250, 1850,
                TipoMotor.GASOLINA,
                TipoTapiceria.CUERO,
                TipoRueda.DEPORTIVO
        ));

        fc.anadirVehiculoACadena(new Turismo(
                "gris", 1450, 2100,
                TipoMotor.ELECTRICO,
                TipoTapiceria.TELA,
                TipoRueda.NORMAL
        ));

        fc.anadirVehiculoACadena(new Furgoneta(
                "verde", 4, 1850, 2550,
                TipoMotor.HIBRIDO,
                TipoTapiceria.ALCANTARA,
                TipoRueda.TODOTERRENO
        ));

        fc.anadirVehiculoACadena(new BiplazaDeportivo(
                "amarillo", 1180, 1750,
                TipoMotor.GASOLINA,
                TipoTapiceria.CUERO,
                TipoRueda.DEPORTIVO
        ));

        fc.anadirVehiculoACadena(new Turismo(
                "blanco", 1380, 1950,
                TipoMotor.ELECTRICO,
                TipoTapiceria.TELA,
                TipoRueda.NORMAL
        ));

        fc.anadirVehiculoACadena(new Furgoneta(
                "azul oscuro", 3, 1900, 2600,
                TipoMotor.HIBRIDO,
                TipoTapiceria.ALCANTARA,
                TipoRueda.TODOTERRENO
        ));
    }
}