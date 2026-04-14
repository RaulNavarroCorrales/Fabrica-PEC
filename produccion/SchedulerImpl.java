package produccion;

public class SchedulerImpl implements Scheduler {

    private CadenaMontaje cadena;

    public SchedulerImpl(CadenaMontaje cadena) {
        this.cadena = cadena;
    }

    //TODO:  Planificador (en inglés scheduler). Es el componente principal que hace
    //funcionar las cadenas de montaje. Para ello, se comporta como un reloj,
    //donde en cada segundo hace que se ejecute una acción dentro de la cadena
    //de montaje. De esta forma, el avance de los vehículos por la cadena de
    //montaje será simulado por el planificador. Por ejemplo, un caso de uso sería
    //el siguiente: el gestor de planta configura las cadenas de montaje para
    //construir coches. Cuando se inicia el proceso, en el segundo 1, los operarios
    //de las cadenas 1, 2 y 3 que controlan los robots de montaje de chasis en
    //cada una de las cadenas, respectivamente, utilizarán los robots para montar
    //los chasis en cada una de las cadenas de montaje, requiriendo el tiempo
    //necesario y aplicando todas las modificaciones necesarias en el estado del
    //vehículo, cadena de montaje y en el almacén de piezas; en el segundo 2, se
    //producirá un cambio de estado en el vehículo, el planificador avanzará las
    //cadenas de montaje hasta los segundos operarios para montar los motores
    //en cada uno de los chasis montados en el estado anterior. Así,
    //sucesivamente, a cada segundo, el planificador irá avanzando cada una de
    //las cadenas de montaje para ir ensamblando los vehículos, siempre y cuando
    //no se produzca ningún evento externo que lo retrase. Ejemplos de eventos
    //externos serían cualquier rotura que se produzca en la cadena de montaje
    //que requiera de mecánicos de cinta para repararlo o problemas en el sistema
    //que necesiten del administrador para restaurarlos. La Figura 1 muestra un
    //ejemplo visual del caso de uso descrito. Es importante remarcar que en este
    //caso de uso todos los operarios tardan un segundo en hacer su función
    //porque se han considerado sólo operarios eficientes. Si en lugar de eficientes
    //fueran usuarios estándar o hubiera distintos tipos de operarios en las
    //cadenas, habría que considerar los tiempos de dichos operarios para realizar
    //los ensamblajes de los vehículos.

    @Override
    public void avanzarTiempo(int segundos) {
        for (int i = 0; i < segundos; i++) {
            if (cadena.verPrimero() == null) {
                System.out.println("Simulación terminada (no hay mas vehículos en la cola)");
                break;
            }
            System.out.println("Segundo " + (i + 1));
            cadena.avanzar();
        }
    }
}