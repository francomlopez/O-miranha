import java.util.ArrayList;
import java.util.HashMap;

public class Estadisticas {
    private int[] disparos;
    private HashMap<Integer, String> nombreT;

    public Estadisticas() {
        disparos = new int[17]; // hay 17 transiciones
        for(int i = 0; i < disparos.length ; i++){
            disparos[i] = 0;
        }
        nombreT = new HashMap<>();
        nombreT.put(0,"ArrivalRate");
        nombreT.put(1,"AsignaP1");
        nombreT.put(2,"AsignaP2");
        nombreT.put(3,"EmpezarP1");
        nombreT.put(4,"EmpezarP2");
        nombreT.put(5,"FinalizarT1P1");
        nombreT.put(6,"FinalizarT1P2");
        nombreT.put(7,"FinalizarT2P1");
        nombreT.put(8,"FinalizarT2P2");
        nombreT.put(9,"P1M1");
        nombreT.put(10,"P1M2");
        nombreT.put(11,"P2M1");
        nombreT.put(12,"P2M2");
        nombreT.put(13,"ProcesarT2P1");
        nombreT.put(14,"ProcesarT2P2");
        nombreT.put(15,"VaciadoM1");
        nombreT.put(16,"VaaciadoM2");
    }

    public void seDisparo(int t){
        disparos[t] += 1;
    }

    public void printDatos(){
        for(int i = 0; i < disparos.length; i++){
            System.out.println("La transicion " + nombreT.get(i) + " se disparo: " + disparos[i] + " veces.");
        }
    }
}
