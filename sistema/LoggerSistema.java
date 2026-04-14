package sistema;

import java.util.ArrayList;
import java.util.List;

public class LoggerSistema {

    private List<String> eventos;

    public LoggerSistema() {
        eventos = new ArrayList<>();
    }

    public void log(String mensaje) {
        eventos.add(mensaje);
    }

    public void mostrarLogs() {
        System.out.println("\n=== LOGS DEL SISTEMA ===");
        for (String e : eventos) {
            System.out.println(e);
        }
    }
}