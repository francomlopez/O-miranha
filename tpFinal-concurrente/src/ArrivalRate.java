public class ArrivalRate implements Runnable{
    private Monitor monitor;

    public ArrivalRate(Monitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        while(true){
            boolean seDisparo = false;
            while(!seDisparo){
                seDisparo = monitor.disparar(0);
            }
        }

    }
}
