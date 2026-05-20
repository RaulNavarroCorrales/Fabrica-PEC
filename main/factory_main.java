package main;

import almacen.AlmacenImpl;
import componentes.TipoMotor;
import componentes.TipoRueda;
import componentes.TipoTapiceria;
import produccion.CadenaMontaje;
import produccion.Planificador;
import sistema.Dashboard;
import sistema.DashboardImpl;
import sistema.FactoryController;
import trabajadores.AdministradorSistema;
import trabajadores.GestorPlanta;
import trabajadores.MecanicoCinta;
import trabajadores.Operario;
import trabajadores.Trabajador;
import vehiculos.BiplazaDeportivo;
import vehiculos.Furgoneta;
import vehiculos.Turismo;
import java.util.Scanner;

/**
 * @author Raul
 */
public class factory_main {
    public static final Scanner sc = new Scanner(System.in);
    public static final FactoryController fc = new FactoryController();

    public static void main(String[] args) {
        System.out.println("""
                ====================================
                 SISTEMA DE PRODUCCIÓN
                ====================================""");

        fc.setAlmacen(new AlmacenImpl());

        // Cargar datos iniciales
        cargarStockInicial();
        cargarTrabajadores();
        cargarCadenas();
        configurarSistema();
        cargarVehiculos();

        System.out.println("\n--- DASHBOARD INICIAL ---");
        fc.getDashboard().mostrar();

        System.out.println("\n--- SIMULACIÓN ---");
        fc.ejecutarSimulacion(30);

        // Lamar al menu para mostrar los resultados
        menu();
        sc.close();
    }

    // CARGAR DATOS INICIALES PARA EJECUTAR LA SIMULACION
    private static void cargarStockInicial() {
        // Motores
        factory_main.fc.getAlmacen().anadirMotor(TipoMotor.GASOLINA, 20);
        factory_main.fc.getAlmacen().anadirMotor(TipoMotor.ELECTRICO, 20);
        factory_main.fc.getAlmacen().anadirMotor(TipoMotor.HIBRIDO, 20);

        // Tapicerías
        factory_main.fc.getAlmacen().anadirTapiceria(TipoTapiceria.CUERO, 20);
        factory_main.fc.getAlmacen().anadirTapiceria(TipoTapiceria.TELA, 20);
        factory_main.fc.getAlmacen().anadirTapiceria(TipoTapiceria.ALCANTARA, 20);

        // Ruedas
        factory_main.fc.getAlmacen().anadirRueda(TipoRueda.DEPORTIVO, 60);
        factory_main.fc.getAlmacen().anadirRueda(TipoRueda.NORMAL, 60);
        factory_main.fc.getAlmacen().anadirRueda(TipoRueda.TODOTERRENO, 60);
    }

    private static void cargarTrabajadores() {
        factory_main.fc.anadirTrabajador(new Operario("Ana", "Lopez", "11111111A", "Calle 1", 1001L, 12));
        factory_main.fc.anadirTrabajador(new Operario("Luis", "Perez", "22222222B", "Calle 2", 1002L, 5));
        factory_main.fc.anadirTrabajador(new Operario("Marta", "Sanchez", "33333333C", "Calle 3", 1003L, 15));
        factory_main.fc.anadirTrabajador(new MecanicoCinta("Javier", "Gomez", "44444444D", "Calle 4", 1004L, 25));
        factory_main.fc.anadirTrabajador(new MecanicoCinta("Elena", "Ruiz", "55555555E", "Calle 5", 1005L, 8));
        factory_main.fc.anadirTrabajador(new GestorPlanta("Carlos", "Martin", "66666666F", "Calle 6", 1006L));
        factory_main.fc.anadirTrabajador(new AdministradorSistema("Sara", "Diaz", "77777777G", "Calle 7", 1007L));
    }

    private static void cargarCadenas() {
        CadenaMontaje c1 = new CadenaMontaje("C1", factory_main.fc.getAlmacen());
        CadenaMontaje c2 = new CadenaMontaje("C2", factory_main.fc.getAlmacen());
        CadenaMontaje c3 = new CadenaMontaje("C3", factory_main.fc.getAlmacen());

        factory_main.fc.anadirCadena(c1);
        factory_main.fc.anadirCadena(c2);
        factory_main.fc.anadirCadena(c3);
    }

    private static void configurarSistema() {
        Planificador planificador = new Planificador(factory_main.fc.getAlmacen());
        factory_main.fc.setPlanificador(planificador);

        Dashboard dashboard = new DashboardImpl(factory_main.fc);
        factory_main.fc.setDashboard(dashboard);

        factory_main.fc.configurarScheduler();
    }

    // Cargar vehículos iniciales para la simulación
    private static void cargarVehiculos() {
        factory_main.fc.anadirVehiculoACadena(new BiplazaDeportivo("rojo", 1200, 1800, TipoMotor.GASOLINA, TipoTapiceria.CUERO, TipoRueda.DEPORTIVO));
        factory_main.fc.anadirVehiculoACadena(new Turismo("azul", 1400, 2000, TipoMotor.ELECTRICO, TipoTapiceria.TELA, TipoRueda.NORMAL));
        factory_main.fc.anadirVehiculoACadena(new Furgoneta("blanco", 3, 1800, 2500, TipoMotor.HIBRIDO, TipoTapiceria.ALCANTARA, TipoRueda.TODOTERRENO));
        factory_main.fc.anadirVehiculoACadena(new BiplazaDeportivo("negro", 1250, 1850, TipoMotor.GASOLINA, TipoTapiceria.CUERO, TipoRueda.DEPORTIVO));
        factory_main.fc.anadirVehiculoACadena(new Turismo("gris", 1450, 2100, TipoMotor.ELECTRICO, TipoTapiceria.TELA, TipoRueda.NORMAL));
        factory_main.fc.anadirVehiculoACadena(new Furgoneta("verde", 4, 1850, 2550, TipoMotor.HIBRIDO, TipoTapiceria.ALCANTARA, TipoRueda.TODOTERRENO));
        factory_main.fc.anadirVehiculoACadena(new BiplazaDeportivo("amarillo", 1180, 1750, TipoMotor.GASOLINA, TipoTapiceria.CUERO, TipoRueda.DEPORTIVO));
        factory_main.fc.anadirVehiculoACadena(new Turismo("blanco", 1380, 1950, TipoMotor.ELECTRICO, TipoTapiceria.TELA, TipoRueda.NORMAL));
        factory_main.fc.anadirVehiculoACadena(new Furgoneta("azul oscuro", 3, 1900, 2600, TipoMotor.HIBRIDO, TipoTapiceria.ALCANTARA, TipoRueda.TODOTERRENO));
    }

    public static void menu(){
        int opcion;
        do {
            System.out.println("\n==============================");
            System.out.println(" MENÚ FÁBRICA");
            System.out.println("==============================");
            System.out.println("1. Mostrar vehículos completados");
            System.out.println("2. Mostrar dashboard final");
            System.out.println("3. Mostrar trabajadores");            
            System.out.println("4. Listar operarios eficientes");
            System.out.println("5. Vehículos por motor");
            System.out.println("6. Vehículos por tapicería");
            System.out.println("7. Vehículos por rueda");
            System.out.println("8. Configuraciones más usadas");
            System.out.println("9. Producción por segundo");
            System.out.println("10. Mostrar todo");
            System.out.println("11. Añadir piezas al almacén");
            System.out.println("12. Dar de alta trabajador");
            System.out.println("0. Salir");
            System.out.print("Seleccione opción: ");
            // Repetir el menu hasta pulsar 0
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
                    menuTrabajadores();
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

                case 10:
                    fc.mostrarEstadisticas();
                    break;
                
                case 11:
                    anadirStock();
                    break;
                    
                case 12:
                    darAltaTrabajador();
                    break;

                case 0:
                    System.out.println("\nFIN DE LA SIMULACIÓN");
                    break;

                default:
                    System.out.println("Opción inválida");
            }
        } while (opcion != 0);
    }

    // Añadir stock al almacen desde el menú principal
    private static void anadirStock() {
        System.out.println("""
                
                Tipo de pieza:
                1. Motor
                2. Tapicería
                3. Rueda
                """);
    
        int tipo = sc.nextInt();
    
        System.out.print("Cantidad a añadir: ");
        int cantidad = sc.nextInt();
    
        switch (tipo) {
    
            case 1:
    
                System.out.println("""
                        
                        Tipo motor:
                        1. GASOLINA
                        2. ELECTRICO
                        3. HIBRIDO
                        """);
    
                int motor = sc.nextInt();
    
                switch (motor) {
                    case 1 -> fc.getAlmacen().anadirMotor(TipoMotor.GASOLINA, cantidad);
                    case 2 -> fc.getAlmacen().anadirMotor(TipoMotor.ELECTRICO, cantidad);
                    case 3 -> fc.getAlmacen().anadirMotor(TipoMotor.HIBRIDO, cantidad);
                    default -> System.out.println("Tipo inválido");
                }
    
                break;
    
            case 2:
    
                System.out.println("""
                        
                        Tipo tapicería:
                        1. CUERO
                        2. TELA
                        3. ALCANTARA
                        """);
    
                int tap = sc.nextInt();
    
                switch (tap) {
                    case 1 -> fc.getAlmacen().anadirTapiceria(TipoTapiceria.CUERO, cantidad);
                    case 2 -> fc.getAlmacen().anadirTapiceria(TipoTapiceria.TELA, cantidad);
                    case 3 -> fc.getAlmacen().anadirTapiceria(TipoTapiceria.ALCANTARA, cantidad);
                    default -> System.out.println("Tipo inválido");
                }
    
                break;
    
            case 3:
    
                System.out.println("""
                        
                        Tipo rueda:
                        1. NORMAL
                        2. DEPORTIVO
                        3. TODOTERRENO
                        """);
    
                int rueda = sc.nextInt();
    
                switch (rueda) {
                    case 1 -> fc.getAlmacen().anadirRueda(TipoRueda.NORMAL, cantidad);
                    case 2 -> fc.getAlmacen().anadirRueda(TipoRueda.DEPORTIVO, cantidad);
                    case 3 -> fc.getAlmacen().anadirRueda(TipoRueda.TODOTERRENO, cantidad);
                    default -> System.out.println("Tipo inválido");
                }
    
                break;
    
            default:
                System.out.println("Opción inválida");
        }
    
        System.out.println("Stock añadido correctamente");
    }

    // Añadir un trabajador a la fabrica desde el menú principal
    private static void darAltaTrabajador() {
        System.out.println("""
                
                Tipo de trabajador:
                1. Operario
                2. Mecánico
                3. Gestor
                4. Administrador
                """);
    
        int tipo = sc.nextInt();
        sc.nextLine();
    
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
    
        System.out.print("Apellidos: ");
        String apellidos = sc.nextLine();
    
        System.out.print("DNI: ");
        String dni = sc.nextLine();
    
        System.out.print("Dirección: ");
        String direccion = sc.nextLine();
    
        System.out.print("NSS: ");
        long nss = sc.nextLong();
    
        switch (tipo) {
            case 1:
                System.out.print("Montajes realizados: ");
                int montajes = sc.nextInt();
                fc.anadirTrabajador(new Operario(nombre,apellidos,dni,direccion,nss,montajes));
    
                break;
    
            case 2:
                System.out.print("Reparaciones realizadas: ");
                int reparaciones = sc.nextInt();
                fc.anadirTrabajador(new MecanicoCinta(nombre,apellidos,dni,direccion,nss,reparaciones));
    
                break;
    
            case 3:
                fc.anadirTrabajador(new GestorPlanta(nombre,apellidos,dni,direccion,nss));
    
                break;
    
            case 4:
                fc.anadirTrabajador(new AdministradorSistema(nombre,apellidos,dni,direccion,nss));
    
                break;
    
            default:
                System.out.println("Tipo inválido");
                return;
        }
    
        System.out.println(" Trabajador añadido correctamente");
    }

    private static void menuTrabajadores() {
        System.out.println("""
                ===== TRABAJADORES =====
                1. Mostrar todos
                2. Mostrar operarios
                3. Mostrar mecánicos
                4. Mostrar gestores
                5. Mostrar administradores
                6. Mostrar operarios eficientes
                7. Mostrar mecánicos eficientes
                """);
    
        int opcion = sc.nextInt();
    
        switch (opcion) {
            case 1:
                for (Trabajador t : fc.getTrabajadores()) {
                    System.out.println(t);
                }
    
                break;
    
            case 2:
                for (Trabajador t : fc.getTrabajadores()) {
                    if (t instanceof Operario) {
                        System.out.println(t);
                    }
                }
    
                break;
    
            case 3:
                for (Trabajador t : fc.getTrabajadores()) {
                    if (t instanceof MecanicoCinta) {
                        System.out.println(t);
                    }
                }
    
                break;
    
            case 4:
                for (Trabajador t : fc.getTrabajadores()) {
                    if (t instanceof GestorPlanta) {
                        System.out.println(t);
                    }
                }
    
                break;
    
            case 5:
                for (Trabajador t : fc.getTrabajadores()) {
                    if (t instanceof AdministradorSistema) {
                        System.out.println(t);
                    }
                }
    
                break;
    
            case 6:
                for (Trabajador t : fc.getTrabajadores()) {
                    if (t instanceof Operario op) {
    
                        if (op.esEficiente()) {
                            System.out.println(op);
                        }
                    }
                }
    
                break;
    
            case 7:
                for (Trabajador t : fc.getTrabajadores()) {
                    if (t instanceof MecanicoCinta mec) {
    
                        if (mec.esEficiente()) {
                            System.out.println(mec);
                        }
                    }
                }
    
                break;
    
            default:
                System.out.println("Opción inválida");
        }
    }
}