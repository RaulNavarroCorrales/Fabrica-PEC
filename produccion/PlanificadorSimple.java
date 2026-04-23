package produccion;

import almacen.Almacen;
import vehiculos.Vehiculo;

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

    // 🔥 NUEVO: planificación automática
    public void planificar(List<CadenaMontaje> cadenas) {

        for (CadenaMontaje c : cadenas) {

            // si la cadena está “libre” o con poca carga
            if (c.getCola().isEmpty()) {
                continue;
            }
        }
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