package produccion;

import java.util.List;

public class SchedulerImpl implements Scheduler {

    private List<CadenaMontaje> cadenas;

    public SchedulerImpl(List<CadenaMontaje> cadenas) {
        this.cadenas = cadenas;
    }

    @Override
    public void avanzarTiempo(int segundos) {
        for (int i = 0; i < segundos; i++) {
            System.out.println("\n=== SEGUNDO " + (i + 1) + " ===");

            for (CadenaMontaje cadena : cadenas) {
                System.out.println("Cadena " + cadena.getId() + ":");
                cadena.avanzar();
            }
        }
    }
}