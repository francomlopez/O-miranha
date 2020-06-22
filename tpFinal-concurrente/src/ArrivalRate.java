public class ArrivalRate implements Runnable {
    private Monitor monitor;
    private int transicion;

    public ArrivalRate(Monitor monitor, int t) {
        this.monitor = monitor;
        transicion = t;
    }

    @Override
    public void run() {
        for(int i = 0; i < 100; i++){
            boolean seDisparo = false;
            while(!seDisparo){
                    seDisparo = monitor.disparar(transicion);
            }
        }

    }
}
