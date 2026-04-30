package produccion;

import sistema.FactoryController;

import java.util.List;

public class SchedulerImpl implements Scheduler {

    private List<CadenaMontaje> cadenas;
    private PlanificadorSimple planificador;
    private FactoryController controller;

    public SchedulerImpl(List<CadenaMontaje> cadenas, PlanificadorSimple planificador, FactoryController controller) {
        this.cadenas = cadenas;
        this.planificador = planificador;
        this.controller = controller;
    }

    @Override
    public void avanzarTiempo(int segundos) {

        for (int i = 0; i < segundos; i++) {

            System.out.println("\n=== SEGUNDO " + (i + 1) + " ===");

            // 🔥 1. Primero planifica producción
            planificador.planificar(cadenas, controller.getPendientes());

            // 🔥 2. Luego avanza cadenas
            for (CadenaMontaje cadena : cadenas) {
                System.out.println("Cadena " + cadena.getId() + ":");
                cadena.avanzar();
            }
        }
    }
}