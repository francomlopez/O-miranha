import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {


    public static void main(String[] args)
    {
        /*PrintStream out = null;
        try {
            out = new PrintStream(
                    new FileOutputStream("log.txt", true), false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setOut(out);

         */
        boolean endProgram = false;
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(15);
        Monitor monitor = new Monitor();
        monitor.getRed().setMonitor(monitor); // se le pasa el monitor a la red para que pueda hacer release al mutex
                                              // cuando se dispara fuera de la ventana de tiempo

        long tinicial = System.currentTimeMillis();

        executor.execute(new ArrivalRate(monitor,0));  // arrivalRate
        executor.execute(new Tarea1T(monitor, 1)); // asignarP1
        executor.execute(new Tarea1T(monitor, 2)); // asignaP2
        executor.execute(new Tarea1T(monitor, 3)); // empezarP1
        executor.execute(new Tarea1T(monitor, 4)); // empezarP2
        executor.execute(new Tarea1T(monitor, 5)); // T1P1
        executor.execute(new Tarea1T(monitor, 6)); // T1P2
        executor.execute(new Tarea2T(monitor, 13, 7)); // T2P1
        executor.execute(new Tarea2T(monitor, 14, 8)); // T2P2
        executor.execute(new Tarea1T(monitor,9)); // P1M1
        executor.execute(new Tarea1T(monitor,10)); // P1M2
        executor.execute(new Tarea1T(monitor,11)); // P2M1
        executor.execute(new Tarea1T(monitor,12)); // P2M2
        executor.execute(new Tarea1T(monitor,15)); // VaciadoM1
        executor.execute(new Tarea1T(monitor,16)); // VaciadoM2



        while(executor.getCompletedTaskCount() == 0){} // frena el main  hasta que arrivalRate termine

        endProgram = true;
        executor.shutdown();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long tfinal = System.currentTimeMillis();
        monitor.getEstadisticas().printDatos();
        System.out.println("Tiempo de ejecucion: " + (tfinal-tinicial)/1000 + " segundos.");
        System.exit(0);
    }
}
