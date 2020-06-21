public class Tarea2T implements Runnable {

    private Monitor monitor;
    private int transicion1;
    private int transicion2;
    private boolean endProgram;

    public Tarea2T(Monitor monitor, int t1, int t2) {
        this.monitor = monitor;
        transicion1 = t1;
        transicion2 = t2;
        endProgram = false;
    }

    @Override
    public void run() {
        while (!endProgram) {
            boolean seDisparo = false;
            while (!seDisparo) {
                seDisparo = monitor.disparar(transicion1);
            }
            seDisparo = false;
            while (!seDisparo) {
                seDisparo = monitor.disparar(transicion2);
            }
        }
    }
}