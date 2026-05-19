package produccion;

import sistema.FactoryController;
import trabajadores.AdministradorSistema;
import trabajadores.MecanicoCinta;
import trabajadores.Operario;
import trabajadores.Trabajador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SchedulerImpl implements Scheduler {

    boolean sistemaCaido = false;
    int tiempoRecuperacion = 0;
    private List<CadenaMontaje> cadenas;
    private Planificador planificador;
    private FactoryController controller;

    public SchedulerImpl(List<CadenaMontaje> cadenas, Planificador planificador, FactoryController controller) {
        this.cadenas = cadenas;
        this.planificador = planificador;
        this.controller = controller;
    }

    @Override
    public void avanzarTiempo(int segundos) {
        Random rand = new Random();

        for (int i = 0; i < segundos; i++) {
            System.out.println("\n=== SEGUNDO " + (i + 1) + " ===");

            // Planificador
            planificador.planificar(cadenas, controller.getPendientes());

            // Caída del sistema
            if (!sistemaCaido && rand.nextInt(100) < 10) {
                AdministradorSistema admin = seleccionarAdministrador();

                if (admin != null) {
                    sistemaCaido = true;
                    tiempoRecuperacion = admin.tiempoReinicioGestor() + admin.tiempoReinicioCadenas();
                    System.out.println(" CAÍDA DEL SISTEMA (" + tiempoRecuperacion + "s)");
                }
            }

            // Si el sistema está caído, se detiene durante un tiempo
            if (sistemaCaido) {
                System.out.println(" Sistema detenido");
                tiempoRecuperacion--;

                if (tiempoRecuperacion <= 0) {
                    sistemaCaido = false;
                    System.out.println(" Sistema restaurado");
                }
                continue;
            }

            // Averias en cadenas
            for (CadenaMontaje cadena : cadenas) {
                if (rand.nextInt(100) < 20 && cadena.getNumeroAverias() <= 3) {
                    MecanicoCinta mecanico = seleccionarMecanico();
                    if (mecanico != null) {
                        int tiempo = (int) mecanico.getTiempoTrabajo();
                        cadena.provocarAveria(tiempo);
                        cadena.setNumeroAverias(cadena.getNumeroAverias() + 1);
                        mecanico.incrementarReparaciones();
                    }
                }
            }

            // Avanzar tiempo en cadenas
            for (CadenaMontaje cadena : cadenas) {
                System.out.println("Cadena " + cadena.getId() + ":");
                cadena.avanzar(i + 1, seleccionarOperario());
            }

            // Dashboard
            controller.notificarDashboard();
        }
    }

    // seleccionar trabajadores aleatoriamente para realizar tareas
    private Operario seleccionarOperario() {
        List<Operario> operarios = new ArrayList<>();

        for (Trabajador t : controller.getTrabajadores()) {
            if (t instanceof Operario op) {
                operarios.add(op);
            }
        }

        if (operarios.isEmpty()) {
            return null;
        }

        Random rand = new Random();

        return operarios.get(rand.nextInt(operarios.size()));
    }

    private MecanicoCinta seleccionarMecanico() {

        List<MecanicoCinta> mecanicos = new ArrayList<>();

        for (Trabajador t : controller.getTrabajadores()) {
            if (t instanceof MecanicoCinta mec) {
                mecanicos.add(mec);
            }
        }

        if (mecanicos.isEmpty()) {
            return null;
        }

        Random rand = new Random();

        return mecanicos.get(rand.nextInt(mecanicos.size()));
    }

    private AdministradorSistema seleccionarAdministrador() {

        List<AdministradorSistema> admins = new ArrayList<>();

        for (Trabajador t : controller.getTrabajadores()) {
            if (t instanceof AdministradorSistema admin) {
                admins.add(admin);
            }
        }

        if (admins.isEmpty()) {
            return null;
        }

        Random rand = new Random();

        return admins.get(rand.nextInt(admins.size()));
    }
}