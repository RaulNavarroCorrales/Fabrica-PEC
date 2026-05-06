package almacen;

import componentes.TipoMotor;
import componentes.TipoRueda;
import componentes.TipoTapiceria;

public interface Almacen {

    void anadirMotor(TipoMotor tipo, int cantidad);

    void anadirTapiceria(TipoTapiceria tipo, int cantidad);

    void anadirRueda(TipoRueda tipo, int cantidad);

    boolean retirarMotor(TipoMotor tipo);

    boolean retirarTapiceria(TipoTapiceria tipo);

    boolean retirarRueda(TipoRueda tipo);

    int getStockMotor(TipoMotor tipo);

    int getStockTapiceria(TipoTapiceria tipo);

    int getStockRueda(TipoRueda tipo);
}