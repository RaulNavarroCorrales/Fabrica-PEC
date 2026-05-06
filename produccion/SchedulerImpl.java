package produccion;
import java.util.Random;
import sistema.FactoryController;
import trabajadores.AdministradorSistema;
import trabajadores.MecanicoCinta;
import trabajadores.Trabajador;

import java.util.List;

public class SchedulerImpl implements Scheduler {

    private List<CadenaMontaje> cadenas;
    private PlanificadorSimple planificador;
    private FactoryController controller;
    boolean sistemaCaido = false;
    int tiempoRecuperacion = 0;

    public SchedulerImpl(List<CadenaMontaje> cadenas, PlanificadorSimple planificador, FactoryController controller) {
        this.cadenas = cadenas;
        this.planificador = planificador;
        this.controller = controller;
    }

    @Override
    public void avanzarTiempo(int segundos) {

        Random rand = new Random();

        for (int i = 0; i < segundos; i++) {

            System.out.println("\n=== SEGUNDO " + (i + 1) + " ===");

            // 1. PLANIFICADOR
            planificador.planificar(cadenas, controller.getPendientes());

            // 2. CAÍDA DEL SISTEMA (MUY COMPLEJA)
            if (!sistemaCaido && rand.nextInt(100) < 10) {

                for (Trabajador t : controller.getTrabajadores()) {
                    if (t instanceof AdministradorSistema admin) {

                        sistemaCaido = true;
                        tiempoRecuperacion = admin.tiempoReinicioGestor()
                                + admin.tiempoReinicioCadenas();

                        System.out.println("💡 CAÍDA DEL SISTEMA (" + tiempoRecuperacion + "s)");
                        break;
                    }
                }
            }

            // 3. SI EL SISTEMA ESTÁ CAÍDO → NO AVANZA NADA
            if (sistemaCaido) {

                System.out.println("⛔ Sistema detenido");

                tiempoRecuperacion--;

                if (tiempoRecuperacion <= 0) {
                    sistemaCaido = false;
                    System.out.println("✔ Sistema restaurado");
                }

                continue;
            }

            // 4. AVERÍAS (COMPLEJA)
            for (CadenaMontaje cadena : cadenas) {

                if (rand.nextInt(100) < 20 && cadena.getNumeroAverias() <= 3) {

                    for (Trabajador t : controller.getTrabajadores()) {
                        if (t instanceof MecanicoCinta mecanico) {

                            int tiempo = (int) mecanico.getTiempoTrabajo();
                            cadena.provocarAveria(tiempo);
                            cadena.setNumeroAverias(cadena.getNumeroAverias() + 1);
                            break;
                        }
                    }
                }
            }

            // 5. AVANCE DE CADENAS
            for (CadenaMontaje cadena : cadenas) {
                System.out.println("Cadena " + cadena.getId() + ":");
                cadena.avanzar(i + 1);
            }

            // 6. DASHBOARD
            controller.notificarDashboard();
        }
    }
}