package sistema;

import componentes.TipoMotor;
import componentes.TipoRueda;
import componentes.TipoTapiceria;
import produccion.CadenaMontaje;
import vehiculos.Vehiculo;

public class DashboardImpl implements Dashboard {

    private FactoryController controller;

    public DashboardImpl(FactoryController controller) {
        this.controller = controller;
    }

    @Override
    public void mostrar() {

        System.out.println("\n===== DASHBOARD =====");

        // =====================
        // CADENAS DE MONTAJE
        // =====================
        if (controller.getCadenas().isEmpty()) {
            System.out.println("No hay cadenas de montaje");
        } else {
            int i = 1;

            for (CadenaMontaje c : controller.getCadenas()) {
                System.out.println("\nCadena " + i + ":");

                if (c.getCola().isEmpty()) {
                    System.out.println("  Sin vehículos en cola");
                } else {
                    for (Vehiculo v : c.getCola()) {
                        System.out.println("  " + v);
                    }
                }

                i++;
            }
        }

        // =====================
        // STOCK
        // =====================
        System.out.println("\n--- STOCK ---");
        System.out.println("\n--- MOTORES ---");
        System.out.println("Motores gasolina: " +
                controller.getAlmacen().getStockMotor(TipoMotor.GASOLINA));
        System.out.println("Motores eléctricos: " +
                controller.getAlmacen().getStockMotor(TipoMotor.ELECTRICO));
        System.out.println("Motores híbridos: " +
                controller.getAlmacen().getStockMotor(TipoMotor.HIBRIDO));

        System.out.println("\n--- TAPICERÍAS ---");
        System.out.println("Tapicería tipo TELA: " +
                controller.getAlmacen().getStockTapiceria(TipoTapiceria.TELA));
        System.out.println("Tapicería tipo CUERO: " +
                controller.getAlmacen().getStockTapiceria(TipoTapiceria.CUERO));
        System.out.println("Tapicería tipo ALCÁNTARA: " +
                controller.getAlmacen().getStockTapiceria(TipoTapiceria.ALCANTARA));

        System.out.println("\n--- RUEDAS ---");
        System.out.println("Ruedas tipo NORMAL: " +
                controller.getAlmacen().getStockRueda(TipoRueda.NORMAL));
        System.out.println("Ruedas tipo DEPORTIVO: " +
                controller.getAlmacen().getStockRueda(TipoRueda.DEPORTIVO));
        System.out.println("Ruedas tipo TODOTERRENO: " +
                controller.getAlmacen().getStockRueda(TipoRueda.TODOTERRENO));

        System.out.println("=====================\n");
    }
}