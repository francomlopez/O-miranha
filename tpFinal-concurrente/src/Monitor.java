import java.util.concurrent.Semaphore;

public class Monitor {

    private RdP red;
    private Semaphore mutex;
    private Semaphore[] colas;
    private Politica politica;
    private Estadisticas estadisticas;


    public Monitor() {
        red = new RdP();
        mutex = new Semaphore(1);
        colas = new Semaphore[red.getCantTransiciones()];
        for(int i = 0; i < red.getCantTransiciones(); i++){
            colas[i] = new Semaphore(0);
        }
        politica = new Politica(red);
        estadisticas = new Estadisticas();
    }

    public boolean disparar(int t){
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int seDispara = red.dispararTransicion(t);  // en dispararTransicion falta implementar el tiempo

        if(seDispara == 0){
            int[] tHabs = red.tHabilitadas();
            int[] esperando = hayAlguien();

            int[] disponible = new int[red.getCantTransiciones()];
            for(int i = 0; i < red.getCantTransiciones(); i++){
                disponible[i] = tHabs[i]*esperando[i];
            }
            int cual = politica.cualDespierto(disponible);
            if(cual != -1){colas[cual].release();} // Despertamos al hilo que estaba en la cola para que intente nuevamente disparar
            estadisticas.seDisparo(t);
            mutex.release();
        }
        else if(seDispara == 1){
            mutex.release();
            try {
                colas[t].acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (seDispara == 0) {return true;}
        else {return false;}
    }

    public int[] hayAlguien(){
        int[] c = new int[red.getCantTransiciones()];

        for(int i = 0; i < red.getCantTransiciones(); i++){
            if(colas[i].hasQueuedThreads()){
                c[i] = 1;
            }
            else{
                c[i] = 0;
            }
        }
        return c;
    }

    public void releaseMutex() {mutex.release();}

    public Politica getPolitica() {
        return politica;
    }
    public RdP getRed(){ return red;}
    public Estadisticas getEstadisticas(){return estadisticas;}
}