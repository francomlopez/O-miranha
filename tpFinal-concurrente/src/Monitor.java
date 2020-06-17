import java.util.concurrent.Semaphore;

public class Monitor {

    private RdP red;
    private Semaphore mutex;
    private Semaphore[] colas;
    private Politica politica;


    public Monitor() {
        red = new RdP();
        mutex = new Semaphore(1);
        for(int i = 0; i < red.getCantTransiciones(); i++){
            colas[i] = new Semaphore(0);
        }
        politica = new Politica();
    }

    public boolean disparar(int t){
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean seDispara = red.dispararTransicion(t);  // en dispararTransicion falta implementar el tiempo

        if(seDispara){
            int[] tHabs = red.tHabilitadas();
            int[] esperando = hayAlguien();

            int[] disponible = new int[red.getCantTransiciones()];
            for(int i = 0; i < red.getCantTransiciones(); i++){
                disponible[i] = tHabs[i]*esperando[i];
            }
            politica.cualDespierto(disponible);
        }
        else{
            mutex.release();
            try {
                colas[t].acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return seDispara;
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
    }


}