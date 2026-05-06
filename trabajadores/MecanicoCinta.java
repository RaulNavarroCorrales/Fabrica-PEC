package trabajadores;

public class MecanicoCinta extends Trabajador {

    private int reparacionesRealizadas;

    public MecanicoCinta(String nombre, String apellidos, String dni,
                         String direccion, long nss, int reparaciones) {
        super(nombre, apellidos, dni, direccion, nss,
                "MecanicoCinta", 1300.0, "2026-01-01");
        this.reparacionesRealizadas = reparaciones;
    }

    public boolean esEficiente() {
        return reparacionesRealizadas > 20;
    }

    public void incrementarReparaciones() {
        reparacionesRealizadas++;
    }

    @Override
    public double getTiempoTrabajo() {
        if (esEficiente()) {
            return 1;
        } else {
            return Math.random() * 4 + 2;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Reparaciones: " + reparacionesRealizadas;
    }
}