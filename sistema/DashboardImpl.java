package sistema;

import produccion.CadenaMontaje;
import vehiculos.Vehiculo;

public class DashboardImpl implements Dashboard {

    private FactoryController controller;

    public DashboardImpl(FactoryController controller) {
        this.controller = controller;
    }

    //TODO: Gestión de un dashboard: el sistema de gestión de fábrica dispone de un
    //cuadro de mandos (en inglés, dashboard) que permite mostrar el balance de
    //los distintos componentes en el almacén y el estado de los vehículos en
    //construcción en las cadenas de montaje. Este cuadro de mandos será la
    //herramienta que utilizará el gestor de planta para consultar el estado en el
    //que se encuentran los vehículos que se están montando en cada una de las
    //cadenas de montaje. Por tanto, cada vez que se produzca un cambio en las
    //3
    //cadenas de montaje y en el almacén porque un componente ha sido
    //ensamblado, el dashboard tendrá que mostrar la modificación, mostrando la
    //situación actual del estado de las cadenas de montaje y del almacén. Todo el
    //proceso de montaje tiene que ser almacenado en la base de datos de
    //manera que pueda ser consultado por fecha a nivel de componente. Cabe
    //destacar que se espera que el diseño del dashboard esté desacoplado del
    //subsistema de visualización de datos para que, en un futuro, el diseño pueda
    //permitir fácilmente un cambio de subsistema de visualización de datos.
    @Override
    public void mostrar() {

        System.out.println("===== DASHBOARD =====");

        // Mostrar estado de la cadena
        CadenaMontaje c = controller.getPrimeraCadena();

        if (c != null) {
            System.out.println("Vehículos en cola:");

            for (Vehiculo v : c.getCola()) {
                System.out.println(v);
            }
        } else {
            System.out.println("No hay cadenas");
        }

        // Mostrar stock
        System.out.println("\nStock de motores gasolina: " +
                controller.getAlmacen().getStockMotor(componentes.TipoMotor.GASOLINA));

        System.out.println("=====================");
    }
}