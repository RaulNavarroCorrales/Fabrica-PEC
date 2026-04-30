package produccion;

import almacen.Almacen;
import vehiculos.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class PlanificadorSimple {

    private Almacen almacen;

    public PlanificadorSimple(Almacen almacen) {
        this.almacen = almacen;
    }

    public boolean puedeProducir(Vehiculo v) {

        return almacen.getStockMotor(v.getTipoMotor()) > 0
                && almacen.getStockTapiceria(v.getTipoTapiceria()) > 0
                && almacen.getStockRueda(v.getTipoRueda()) >= 4;
    }

    public void planificar(List<CadenaMontaje> cadenas, List<Vehiculo> pendientes) {

        if (cadenas.isEmpty()) return;

        CadenaMontaje cadena = cadenas.get(0); // de momento simple

        List<Vehiculo> procesados = new ArrayList<>();

        for (Vehiculo v : pendientes) {

            if (puedeProducir(v)) {
                cadena.ponerEnCola(v);
                System.out.println("✔ Planificador: " + v.getColor() + " enviado a cadena");
                procesados.add(v);
            } else {
                System.out.println("✖ Sin recursos para: " + v.getColor());
            }
        }

        pendientes.removeAll(procesados);
    }

    // 🔥 MÉTODO IMPORTANTE: meter vehículo si se puede
    public void intentarProducir(Vehiculo v, CadenaMontaje cadena) {

        if (puedeProducir(v)) {
            cadena.ponerEnCola(v);
            System.out.println("✔ Planificador: añadido " + v.getColor() + " a la cola");
        } else {
            System.out.println("✖ Planificador: no hay recursos para " + v.getColor());
        }
    }
}