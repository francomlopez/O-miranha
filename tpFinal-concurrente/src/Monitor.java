import java.util.concurrent.Semaphore;

public class Monitor {

    private RdP red;
    private Semaphore mutex;


    public Monitor() {
        red = new RdP();
        mutex = new Semaphore(1);
    }


}