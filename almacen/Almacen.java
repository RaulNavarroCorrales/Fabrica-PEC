package almacen;

import componentes.TipoMotor;
import componentes.TipoRueda;
import componentes.TipoTapiceria;

public interface Almacen {

    void añadirMotor(TipoMotor tipo, int cantidad);

    void añadirTapiceria(TipoTapiceria tipo, int cantidad);

    void añadirRueda(TipoRueda tipo, int cantidad);

    boolean retirarMotor(TipoMotor tipo);

    boolean retirarTapiceria(TipoTapiceria tipo);

    boolean retirarRueda(TipoRueda tipo);

    int getStockMotor(TipoMotor tipo);

    int getStockTapiceria(TipoTapiceria tipo);

    int getStockRueda(TipoRueda tipo);
}