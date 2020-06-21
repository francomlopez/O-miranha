public class Tarea1T implements Runnable{
    private Monitor monitor;
    private int transicion;
    private boolean endProgram;

    public Tarea1T(Monitor monitor, int t) {
        this.monitor = monitor;
        transicion = t;
        endProgram = false;
    }

    @Override
    public void run() {
        while(!endProgram){
            boolean seDisparo = false;
            while(!seDisparo){
                seDisparo = monitor.disparar(transicion);
            }
        }
    }

    public void setEnd(){endProgram = true;}
}