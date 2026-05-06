package almacen;

import componentes.TipoMotor;
import componentes.TipoRueda;
import componentes.TipoTapiceria;

import java.util.HashMap;
import java.util.Map;

public class AlmacenImpl implements Almacen {

    private Map<TipoMotor, Integer> motores;
    private Map<TipoTapiceria, Integer> tapicerias;
    private Map<TipoRueda, Integer> ruedas;

    public AlmacenImpl() {
        motores = new HashMap<>();
        tapicerias = new HashMap<>();
        ruedas = new HashMap<>();

        // Inicializar todo a 0 (MUY IMPORTANTE)
        for (TipoMotor t : TipoMotor.values()) {
            motores.put(t, 0);
        }

        for (TipoTapiceria t : TipoTapiceria.values()) {
            tapicerias.put(t, 0);
        }

        for (TipoRueda t : TipoRueda.values()) {
            ruedas.put(t, 0);
        }
    }

    // =====================
    // AÑADIR
    // =====================

    @Override
    public void anadirMotor(TipoMotor tipo, int cantidad) {
        motores.put(tipo, motores.getOrDefault(tipo, 0) + cantidad);
    }

    @Override
    public void anadirTapiceria(TipoTapiceria tipo, int cantidad) {
        tapicerias.put(tipo, tapicerias.getOrDefault(tipo, 0) + cantidad);
    }

    @Override
    public void anadirRueda(TipoRueda tipo, int cantidad) {
        ruedas.put(tipo, ruedas.getOrDefault(tipo, 0) + cantidad);
    }

    // =====================
    // RETIRAR
    // =====================

    @Override
    public boolean retirarMotor(TipoMotor tipo) {
        if (motores.get(tipo) != null) {
            if (motores.get(tipo) > 0) {
                motores.put(tipo, motores.get(tipo) - 1);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean retirarTapiceria(TipoTapiceria tipo) {
        if (tapicerias.get(tipo) > 0) {
            tapicerias.put(tipo, tapicerias.get(tipo) - 1);
            return true;
        }
        return false;
    }

    @Override
    public boolean retirarRueda(TipoRueda tipo) {
        if (ruedas.get(tipo) > 0) {
            ruedas.put(tipo, ruedas.get(tipo) - 1);
            return true;
        }
        return false;
    }

    // =====================
    // CONSULTAS
    // =====================

    @Override
    public int getStockMotor(TipoMotor tipo) {
        return motores.get(tipo);
    }

    @Override
    public int getStockTapiceria(TipoTapiceria tipo) {
        return tapicerias.get(tipo);
    }

    @Override
    public int getStockRueda(TipoRueda tipo) {
        return ruedas.get(tipo);
    }
}