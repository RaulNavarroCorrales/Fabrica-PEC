package produccion;

import java.util.List;

public class SchedulerImpl implements Scheduler {

    private List<CadenaMontaje> cadenas;
    private PlanificadorSimple planificador;

    public SchedulerImpl(List<CadenaMontaje> cadenas, PlanificadorSimple planificador) {
        this.cadenas = cadenas;
        this.planificador = planificador;
    }

    @Override
    public void avanzarTiempo(int segundos) {

        for (int i = 0; i < segundos; i++) {

            System.out.println("\n=== SEGUNDO " + (i + 1) + " ===");

            // 🔥 1. Primero planifica producción
            planificador.planificar(cadenas);

            // 🔥 2. Luego avanza cadenas
            for (CadenaMontaje cadena : cadenas) {
                System.out.println("Cadena " + cadena.getId() + ":");
                cadena.avanzar();
            }
        }
    }
}