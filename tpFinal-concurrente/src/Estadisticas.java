import java.util.ArrayList;

public class Estadisticas {
    private int[] disparos;

    public Estadisticas() {
        disparos = new int[17]; // hay 17 transiciones
        for(int i = 0; i < disparos.length ; i++){
            disparos[i] = 0;
        }
    }

    public void seDisparo(int t){
        disparos[t] += 1;
        System.out.println("Se disparo la transicion" + t);
    }

    public void printDatos(){
        for(int i = 0; i < disparos.length; i++){
            System.out.println("La transicion " + i + " se disparo: " + disparos[i] + " veces.");
        }
    }
}
