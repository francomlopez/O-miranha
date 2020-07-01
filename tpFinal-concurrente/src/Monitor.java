import java.util.concurrent.Semaphore;

public class Monitor {

    private RdP red;
    private Semaphore mutex;
    private Semaphore[] colas;
    private Politica politica;
    private Estadisticas estadisticas;
    private long tinicial;


    public Monitor() {
        red = new RdP();
        mutex = new Semaphore(1);
        colas = new Semaphore[red.getCantTransiciones()];
        for(int i = 0; i < red.getCantTransiciones(); i++){
            colas[i] = new Semaphore(0);
        }
        politica = new Politica(red);
        estadisticas = new Estadisticas(red.getNombreT());
        tinicial = System.currentTimeMillis();
    }

    public boolean disparar(int t){
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int seDispara = red.dispararTransicion(t);  // en dispararTransicion falta implementar el tiempo

        if(seDispara == 0){
            //System.out.println(System.currentTimeMillis()-tinicial + ":"+"disparo exitoso de t: " + t);
            int[] tHabs = red.tHabilitadas();
            int[] esperando = hayAlguien();

            int[] disponible = new int[red.getCantTransiciones()];
            for(int i = 0; i < red.getCantTransiciones(); i++){
                disponible[i] = tHabs[i]*esperando[i];
            }
            int[] cuales = politica.cualDespierto(disponible);
            for(int i = 0; i < cuales.length; i++){
                if(cuales[i] == 1){
                    colas[i].release();
                }
            }
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