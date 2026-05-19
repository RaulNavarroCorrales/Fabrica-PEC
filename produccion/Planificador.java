package produccion;

import almacen.Almacen;
import vehiculos.Vehiculo;

import java.util.ArrayList;
import java.util.List;

public class Planificador {
    private Almacen almacen;

    public Planificador(Almacen almacen) {
        this.almacen = almacen;
    }

    public boolean puedeProducir(Vehiculo v) {
        return almacen.getStockMotor(v.getTipoMotor()) > 0 && almacen.getStockTapiceria(v.getTipoTapiceria()) > 0 && almacen.getStockRueda(v.getTipoRueda()) >= 4;
    }

    public void planificar(List<CadenaMontaje> cadenas, List<Vehiculo> pendientes) {
        if (cadenas.isEmpty()) return;
        List<Vehiculo> procesados = new ArrayList<>();
        int indiceCadena = 0;

        for (Vehiculo v : pendientes) {
            if (puedeProducir(v)) {
                CadenaMontaje cadena = cadenas.get(indiceCadena);
                cadena.ponerEnCola(v);
                System.out.println(" Planificador: " + v.getColor() + " enviado a " + cadena.getId());
                procesados.add(v);

                // siguiente cadena
                indiceCadena++;

                if (indiceCadena >= cadenas.size()) {
                    indiceCadena = 0;
                }

            } else {
                System.out.println(" Sin recursos para: " + v.getColor());
            }
        }
        pendientes.removeAll(procesados);
    }
}