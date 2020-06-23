import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Estadisticas {
    private int[] disparos;
    private HashMap<Integer, String> nombreT;

    public Estadisticas(HashMap<Integer, String> nombres) {
        disparos = new int[17]; // hay 17 transiciones
        for(int i = 0; i < disparos.length ; i++){
            disparos[i] = 0;
        }
        nombreT = nombres;
    }

    public void seDisparo(int t){
        disparos[t] += 1;
    }

    public void printDatos(){
        System.out.println("================================================================================");
        for(int i = 0; i < disparos.length; i++){
            System.out.println("La transicion " + nombreT.get(i) + " se disparo: " + disparos[i] + " veces.");
        }
        System.out.println("================================================================================");
    }
}
