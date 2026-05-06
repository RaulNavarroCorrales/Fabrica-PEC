package trabajadores;

public class Operario extends Trabajador {

    private int montajesRealizados;

    public Operario(String nombre, String apellidos, String dni,
                    String direccion, long nss, int montajesRealizados) {

        super(nombre, apellidos, dni, direccion, nss,
                "Operario", 1200.0, "2026-01-01");

        this.montajesRealizados = montajesRealizados;
    }

    public boolean esEficiente() {
        return montajesRealizados > 10;
    }

    public void incrementarMontajes() {
        montajesRealizados++;
    }

    @Override
    public double getTiempoTrabajo() {
        if (montajesRealizados > 10) {
            return 1;
        } else {
            return 3;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " | Montajes: " + montajesRealizados;
    }
}