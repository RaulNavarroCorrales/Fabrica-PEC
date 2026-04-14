package trabajadores;


public class GestorPlanta extends Trabajador {

    public GestorPlanta(String nombre, String apellidos, String dni,
                        String direccion, long nss) {

        super(nombre, apellidos, dni, direccion, nss,
                "GestorPlanta", 1800, "2026");
    }

    @Override
    public double getTiempoTrabajo() {
        //No trabajan en cadena
        return 0;
    }
}