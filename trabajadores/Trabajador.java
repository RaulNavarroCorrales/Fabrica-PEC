package trabajadores;

public abstract class Trabajador {
    private String nombre;
    private String apellidos;
    private String dni;
    private String direccion;
    private long numeroSeguroSocial;
    private String puesto;
    private double salario;
    private String fechaIngreso;

    public Trabajador(String nombre, String apellidos, String dni, String direccion,
                      long numeroSeguroSocial, String puesto, double salario, String fechaIngreso) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.direccion = direccion;
        this.numeroSeguroSocial = numeroSeguroSocial;
        this.puesto = puesto;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
    }

    public abstract double getTiempoTrabajo();

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    @Override
    public String toString() {
        return "Trabajador{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", dni='" + dni + '\'' +
                ", direccion='" + direccion + '\'' +
                ", numeroSeguroSocial=" + numeroSeguroSocial +
                ", puesto='" + puesto + '\'' +
                ", salario=" + salario +
                ", fechaIngreso='" + fechaIngreso + '\'' +
                '}';
    }
}