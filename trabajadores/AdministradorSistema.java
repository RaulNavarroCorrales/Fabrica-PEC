package trabajadores;


public class AdministradorSistema extends Trabajador {

    public AdministradorSistema(String nombre, String apellidos, String dni,
                                String direccion, long nss) {

        super(nombre, apellidos, dni, direccion, nss,
                "AdministradorSistema", 2000, "2026");
    }

    @Override
    public double getTiempoTrabajo() {
        return 0;
    }

    public int tiempoReinicioGestor() {
        return 2;
    }

    public int tiempoReinicioCadenas() {
        return 3;
    }
}